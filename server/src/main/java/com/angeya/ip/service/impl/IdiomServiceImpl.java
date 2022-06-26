package com.angeya.ip.service.impl;

import com.angeya.ip.pojo.Idiom;
import com.angeya.ip.pojo.IdiomParam;
import com.angeya.ip.service.IdiomService;
import com.angeya.ip.util.PinYinTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:
 * Author: Angeya
 * DateTime: 2022-02-19 15:54
 */

@Service
public class IdiomServiceImpl implements IdiomService {
    private static final Logger logger = LoggerFactory.getLogger(IdiomServiceImpl.class);
    private static final String IDIOM_FILE_PATH = "data.txt";
    private final List<Idiom> idiomLibrary = new ArrayList<>(23000);
    private static final String PATTERN_STR = "^[\\u4E00-\\u9FA5]+$";
    private final Pattern pattern;


    public IdiomServiceImpl() {
        pattern = Pattern.compile(PATTERN_STR);
    }

    @Override
    @PostConstruct
    public void loadIdiomLibrary() {
        List<String> idiomStrList = this.getIdiomStrListFromFile();
        idiomStrList.forEach(idiomStr -> {
            // 成语 拼音 释义，3个空格以上间隔
            String[] idiomStrs = idiomStr.split("[ ]{3,}");
            String idiomContent = idiomStrs[0];
            String tonePinTinStr = PinYinTool.changeToTonePinYin(idiomContent);
            String simplePinTinStr = PinYinTool.changeToSimplePinYin(idiomContent);
            System.out.println(idiomContent + "--" + tonePinTinStr + "--" + simplePinTinStr);
            if (tonePinTinStr != null && simplePinTinStr != null) {
                Idiom idiom = new Idiom(idiomContent, Arrays.asList(tonePinTinStr.split(" ")),
                        Arrays.asList(simplePinTinStr.split(" ")), idiomStrs[2]);
                this.idiomLibrary.add(idiom);
            }
        });
    }

    @Override
    public List<Idiom> getNextIdiom(IdiomParam idiomParam) {
        List<Idiom> idiomList = new ArrayList<>();
        if (!this.isContentChinese(idiomParam.getContent())) {
            return idiomList;
        }
        String firstChar = idiomParam.getContent().substring(idiomParam.getContent().length() - 1);
        // 仅同音模式
        if (idiomParam.getSameSimplePinYin()) {
            String[] simplePinYins = PinYinTool.changeToSimplePinYin(idiomParam.getContent()).split(" ");
            String lastSimplePinYin = simplePinYins[simplePinYins.length - 1];
            this.idiomLibrary.forEach(idiom -> {
                if (lastSimplePinYin.equals(idiom.getSimplePinYinFirst())) {
                    idiomList.add(idiom);
                }
            });
        } else if (idiomParam.getSameTonePinYin()) {
            // 同音同调模式，拼音声调一致
            String[] tonePinYins = PinYinTool.changeToTonePinYin(idiomParam.getContent()).split(" ");
            String lastTonePinYin = tonePinYins[tonePinYins.length - 1];
            this.idiomLibrary.forEach(idiom -> {
                if (lastTonePinYin.equals(idiom.getTonePinYinFirst())) {
                    idiomList.add(idiom);
                }
            });
        } else {
            // 严格模式，同音同字
            String[] tonePinYins = PinYinTool.changeToTonePinYin(idiomParam.getContent()).split(" ");
            String lastTonePinYin = tonePinYins[tonePinYins.length - 1];
            this.idiomLibrary.forEach(idiom -> {
                if (firstChar.equals(idiom.getContentFirst()) && lastTonePinYin.equals(idiom.getTonePinYinFirst())) {
                    idiomList.add(idiom);
                }
            });
        }
        return idiomList;
    }

    /**
     * 从文件中读取成语资源，翻入list中
     *
     * @return 成语列表
     */
    private List<String> getIdiomStrListFromFile() {
        List<String> idiomStrList = new ArrayList<>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(IDIOM_FILE_PATH);
        Assert.notNull(inputStream, "Can not get file");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        try {
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                idiomStrList.add(line);
            }
        } catch (IOException e) {
            logger.error("Read file error", e);
        } finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                logger.error("Close resource failed", e);
            }
        }
        return idiomStrList;
    }

    /**
     * 判断内容是否只包含中文
     *
     * @param content 内容
     * @return 结果
     */
    private boolean isContentChinese(String content) {
        Matcher matcher = this.pattern.matcher(content);
        return matcher.matches();
    }
}

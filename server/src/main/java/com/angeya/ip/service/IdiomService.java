package com.angeya.ip.service;

import com.angeya.ip.pojo.Idiom;
import com.angeya.ip.pojo.IdiomParam;

import java.util.List;

/**
 * Desc: 成语服务接口
 * Author: Angeya
 * DateTime: 2022-02-19 15:52
 */
public interface IdiomService {

    /**
     * 加载成语库
     *
     * @return 成语 Map
     */
    void loadIdiomLibrary();

    /**
     * 获取下一个成语
     *
     * @return 所有匹配的成语
     */
    List<Idiom> getNextIdiom(IdiomParam idiomParam);
}

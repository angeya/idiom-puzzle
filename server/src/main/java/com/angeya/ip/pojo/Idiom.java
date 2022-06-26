package com.angeya.ip.pojo;

import java.util.List;

/**
 * Desc: 成语实体类
 * Author: Angeya
 * DateTime: 2022-02-17 22:11
 */
public class Idiom {
    /**
     * 成语
     */
    private String content;

    /**
     * 声调拼音
     */
    private List<String> tonePinYin;

    /**
     * 简易拼音
     */
    private List<String> simplePinYin;

    /**
     * 释义
     */
    private String explanation;

    public Idiom(String content, List<String> tonePinYin, List<String> simplePinYin, String explanation) {
        this.content = content;
        this.tonePinYin = tonePinYin;
        this.simplePinYin = simplePinYin;
        this.explanation = explanation;
    }

    public String getContentFirst() {
        return this.content.substring(0, 1);
    }

    public String getContentLast() {
        return this.content.substring(this.content.length() - 1);
    }

    public String getTonePinYinFirst() {
        return this.tonePinYin.get(0);
    }

    public String getTonePinYinLast() {
        return this.tonePinYin.get(this.tonePinYin.size() - 1);
    }

    public String getSimplePinYinFirst() {
        return this.simplePinYin.get(0);
    }

    public String getSimplePinYinLast() {
        return this.simplePinYin.get(this.simplePinYin.size() - 1);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTonePinYin() {
        return tonePinYin;
    }

    public void setTonePinYin(List<String> tonePinYin) {
        this.tonePinYin = tonePinYin;
    }

    public List<String> getSimplePinYin() {
        return simplePinYin;
    }

    public void setSimplePinYin(List<String> simplePinYin) {
        this.simplePinYin = simplePinYin;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}

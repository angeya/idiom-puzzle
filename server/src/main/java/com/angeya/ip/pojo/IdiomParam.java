package com.angeya.ip.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Desc:
 * Author: Angeya
 * DateTime: 2022-02-20 18:02
 */
public class IdiomParam {
    private String content;

    @JsonProperty("isSameSimplePinYin")
        private Boolean isSameSimplePinYin;

    @JsonProperty("isSameTonePinYin")
    private Boolean isSameTonePinYin;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSameSimplePinYin() {
        return isSameSimplePinYin;
    }

    public void setSameSimplePinYin(Boolean sameSimplePinYin) {
        isSameSimplePinYin = sameSimplePinYin;
    }

    public Boolean getSameTonePinYin() {
        return isSameTonePinYin;
    }

    public void setSameTonePinYin(Boolean sameTonePinYin) {
        isSameTonePinYin = sameTonePinYin;
    }

    @Override
    public String toString() {
        return "IdiomParam{" +
                "content='" + content + '\'' +
                ", isSameSimplePinYin=" + isSameSimplePinYin +
                ", isSameTonePinYin=" + isSameTonePinYin +
                '}';
    }
}

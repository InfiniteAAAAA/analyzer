package com.example.analyzer.pojo;

import java.io.Serializable;

public class MergeWordDTO implements Serializable {
    private static final long serialVersionUID = -1577266502266354694L;
    private Integer index;
    private String word;
    private String mergeWord;

    public MergeWordDTO() {
    }

    public MergeWordDTO(Integer index, String word, String mergeWord) {
        this.index = index;
        this.word = word;
        this.mergeWord = mergeWord;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMergeWord() {
        return mergeWord;
    }

    public void setMergeWord(String mergeWord) {
        this.mergeWord = mergeWord;
    }
}

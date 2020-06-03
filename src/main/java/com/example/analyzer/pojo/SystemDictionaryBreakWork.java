package com.example.analyzer.pojo;

import java.util.List;
import java.util.Set;

public class SystemDictionaryBreakWork implements Strategy {
    @Override
    public List<List<String>> breakWord(Dictionary dictionary, String sentence) {
        Set<String> dic = Dictionary.getSystemDic();
        List<List<String>> wordList = dictionary.breakSentence(sentence, dic);
        return wordList;
    }
}

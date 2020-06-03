package com.example.analyzer.pojo;

import java.util.List;
import java.util.Set;

public class BothDictionaryBreakWork implements Strategy {
    @Override
    public List<List<String>> breakWord(Dictionary dictionary, String sentence) {
        Set<String> systemDic = Dictionary.getSystemDic();
        Set<String> userDic = Dictionary.getUserDic();
        systemDic.removeAll(userDic);
        systemDic.addAll(userDic);
        Set<String> dic = systemDic;
        List<List<String>> wordList = dictionary.breakSentence(sentence, dic);
        return wordList;
    }
}

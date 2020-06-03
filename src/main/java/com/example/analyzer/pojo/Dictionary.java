package com.example.analyzer.pojo;

import java.util.*;

public class Dictionary {
    /**
     * System dictionary set
     */
    private static Set<String> systemDic = new HashSet<>();

    /**
     * User dictionary set
     */
    private static Set<String> userDic = new HashSet<>();
    /**
     * Add words to the dictionary
     */
    static{
        systemDic.add("i");
        systemDic.add("like");
        systemDic.add("sam");
        systemDic.add("sung");
        systemDic.add("samsung");
        systemDic.add("mobile");
        systemDic.add("ice");
        systemDic.add("cream");
        systemDic.add("man");
        systemDic.add("go");
    }
    /**
     * use enum Singleton create the class
     */
    private Dictionary() {
    }

    static enum SingletonEnum {
        INSTANCE;
        private Dictionary dictionary;

        private SingletonEnum() {
            dictionary = new Dictionary();
        }

        private Dictionary getInstance() {
            return dictionary;
        }
    }
    public static Dictionary getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    public static Set<String> getSystemDic() {
        return systemDic;
    }

    public static void setSystemDic(Set<String> systemDic) {
        Dictionary.systemDic = systemDic;
    }

    public static Set<String> getUserDic() {
        return userDic;
    }

    public static void setUserDic(Set<String> userDic) {
        Dictionary.userDic = userDic;
    }

    /**
     * add some words to user dictionary
     * @param words
     */
    public void addWordsToUserDictionary(String words) {
        String[] wordArray = words.split(",");
        List<String> wordList = Arrays.asList(wordArray);
        wordList.stream().forEach(word -> {
            userDic.add(word);
        });
    }

    /**
     * use system dictionary to break the sentence
     * @param sentence
     * @return
     */
    public List<List<String>> breakSentence(String sentence,Set<String> dic) {
        List<List<String>> finalList = new ArrayList<>();
        List<String> wordList = new ArrayList<>();
        int lastIndex = 1;
        for (int startIndex = 0; startIndex < sentence.length()-1; startIndex++) {
            if (startIndex!=0
                    &&startIndex<lastIndex) {
                continue;
            }
            else {
                for (int endIndex = startIndex + 1; endIndex <= sentence.length(); endIndex++) {
                    String substring = sentence.substring(startIndex, endIndex);
                    if (dic.contains(substring)) {
                        wordList.add(substring);
                        lastIndex = endIndex;
                        break;
                    }
                }
            }
        }
        finalList.add(wordList);

        //判断连词情况
        for (int i = 0; i < wordList.size()-1; i++) {
            //a,b,c,d
            String firstWord = wordList.get(i);
            String secondWord = wordList.get(i+1);
            StringBuffer stringBuffer = new StringBuffer();
            String word = stringBuffer.append(firstWord).append(secondWord).toString();
            //存在连词情况
            if (dic.contains(word)) {
                List<String> otherWordList = new ArrayList<>();
                otherWordList.addAll(wordList);
                otherWordList.remove(i);
                otherWordList.add(i,word);
                otherWordList.remove(i+1);
                finalList.add(otherWordList);
            }
        }
        return finalList;
    }
}

package com.example.analyzer.pojo;

import java.util.*;
import java.util.stream.Collectors;

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
     * @param flag system: use system dictionary, user: use user dictionary, both: use both dictionary
     * @return
     */
    public List<String> breakSentence(String sentence,String flag) {
        List<String> wordList = new ArrayList<>();
        Set<String> dic = null;
        //use system dictionary
        if ("system".equals(flag)) {
            dic = systemDic;
        }
        //use user dictionary
        else if ("user".equals(flag)) {
            dic = userDic;
        }
        //use both dictionary
        else if ("both".equals(flag)) {
            systemDic.removeAll(userDic);
            systemDic.addAll(userDic);
            dic = systemDic;
        }
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
                    }
                }
            }
        }
        List<String> finalList = new ArrayList<>();
        while(wordList.stream().collect(Collectors.joining("")).length()>sentence.length()) {
            for (int i = 0; i < wordList.size(); i++) {
                if (wordList.get(i+1).length()>wordList.get(i).length()
                        && wordList.get(i+1).startsWith(wordList.get(i))) {
                    String replaceWord = wordList.get(i+1).substring(wordList.get(i).length());
                    List<String> otherGroupWord = new ArrayList<>();
                    otherGroupWord.addAll(wordList);
                    wordList.remove(wordList.get(i));
                    Collections.replaceAll(otherGroupWord,otherGroupWord.get(i+1),replaceWord);
                    finalList.add("\n");
                    finalList.addAll(otherGroupWord);
                    break;
                }
            }
        }
        return finalList;
    }

    public static void main(String[] args) {
        String a = "sam";
        String b = "samsung";
        boolean contains = b.contains(a);
        System.out.println(contains);
        String substring = b.substring(a.length());
        System.out.println(substring);
    }

}

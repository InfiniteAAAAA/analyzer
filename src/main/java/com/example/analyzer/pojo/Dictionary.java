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
                        break;
                    }
                }
            }
        }
        List<List<String>> finalList = new ArrayList<>();
        finalList.add(wordList);
        //连词的位置和值
        List<MergeWordDTO> mergeWordList = new ArrayList<>();
        //判断连词情况
        for (int i = 0; i < wordList.size()-1; i++) {
            //a,b,c,d
            String firstWord = wordList.get(i);
            String secondWord = wordList.get(i+1);
            StringBuffer stringBuffer = new StringBuffer();
            String mergeWord = stringBuffer.append(firstWord).append(secondWord).toString();
            //存在连词情况，记录起来
            if (dic.contains(mergeWord)) {
                mergeWordList.add(new MergeWordDTO(i,firstWord,mergeWord));
            }
        }
        //
        for (int i = 0; i < mergeWordList.size(); i++) {
            MergeWordDTO mergeWordDTO = mergeWordList.get(i);
            for (int j = 0; j < 2; j++) {
                List<String> otherWordList = new ArrayList<>();
                otherWordList.addAll(wordList);
                //偶数
                if (j % 2 == 0) {
                    continue;
                }
                else {
                    otherWordList.set(mergeWordDTO.getIndex(),mergeWordDTO.getMergeWord());
                    otherWordList.remove(mergeWordDTO.getIndex()+1);
                }
                finalList.add(otherWordList);
            }
        }
        finalList.stream().forEach(x -> {

            String collect = x.stream().collect(Collectors.joining(" "));
            System.out.println(collect);

        });
        return wordList;
    }

    public static void main(String[] args) {
        System.out.println(0 % 2 == 0);
    }

}

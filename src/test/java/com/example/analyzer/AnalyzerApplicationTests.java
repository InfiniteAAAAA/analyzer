package com.example.analyzer;

import com.example.analyzer.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class AnalyzerApplicationTests {

    /**
     * use system dictionary to break sentence
     */
    @Test
    void useSystemDictionaryBreakSentence() {
        String sentence = "ilikesamsungmobile";
        System.out.println("Input:");
        System.out.println(sentence);
        System.out.println("Output:");
        Context context = new Context(new SystemDictionaryBreakWork());
        List<List<String>> wordList = context.executeStrategy(Dictionary.getInstance(), sentence);
        wordList.stream().forEach(x->{
            System.out.println(x.stream().collect(Collectors.joining(" ")));
        });
    }

    @Test
    void useUserDictionaryBreakSentence() {
        String sentence = "whatcanisaymambaout";
        System.out.println("Input:");
        System.out.println(sentence);
        System.out.println("Output:");
        Dictionary dictionary = Dictionary.getInstance();
        dictionary.addWordsToUserDictionary("what,can,i,say,mamba,out");
        Context context = new Context(new UserDictionaryBreakWork());
        List<List<String>> wordList = context.executeStrategy(dictionary, sentence);
        wordList.stream().forEach(x->{
            System.out.println(x.stream().collect(Collectors.joining(" ")));
        });
    }

    @Test
    void useBothDictionaryBreakSentence() {
        String sentence = "ilikesamsungmobilewhatcanisaymambaout";
        System.out.println("Input:");
        System.out.println(sentence);
        System.out.println("Output:");
        Dictionary dictionary = Dictionary.getInstance();
        dictionary.addWordsToUserDictionary("what,can,i,say,mamba,out");
        Context context = new Context(new BothDictionaryBreakWork());
        List<List<String>> wordList = context.executeStrategy(dictionary, sentence);
        wordList.stream().forEach(x->{
            System.out.println(x.stream().collect(Collectors.joining(" ")));
        });
    }
}

package com.example.analyzer;

import com.example.analyzer.pojo.Dictionary;
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
        String line = "ilikesamsungmobilesamsung";
        Dictionary dictionary = Dictionary.getInstance();
        System.out.println("Input:");
        System.out.println(line);
        List<String> wordList = dictionary.breakSentence(line, "system");
        String sentence = wordList.stream().collect(Collectors.joining(" "));
        System.out.println("Output:");
        System.out.println(sentence);
    }
}

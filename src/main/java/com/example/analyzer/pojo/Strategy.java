package com.example.analyzer.pojo;

import java.util.List;

public interface Strategy {
    public List<List<String>> breakWord(Dictionary dictionary, String sentence);
}

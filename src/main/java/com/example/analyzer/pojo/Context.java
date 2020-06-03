package com.example.analyzer.pojo;

import java.util.List;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public List<List<String>> executeStrategy(Dictionary dictionary, String sentence){
        return strategy.breakWord(dictionary,sentence);
    }
}

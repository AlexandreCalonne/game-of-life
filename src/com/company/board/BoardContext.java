package com.company.board;

import java.util.List;

public class BoardContext {

    private BoardStrategy strategy;

    public void setStrategy(BoardStrategy strategy) {
        this.strategy = strategy;
    }

    public List<List<String>> generate(int size) {
        return strategy.generate(size);
    }

}

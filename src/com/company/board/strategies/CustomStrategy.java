package com.company.board.strategies;

import com.company.board.BoardStrategy;

import java.util.List;

import static java.util.Arrays.asList;

public class CustomStrategy implements BoardStrategy {

    @Override
    public List<List<String>> generate(int size) {
        return asList(
            asList("◉", "◉", "·", "·", "·", "·", "·", "·", "·", "◉"),
            asList("◉", "·", "·", "·", "·", "·", "·", "·", "·", "·"),
            asList("·", "·", "·", "·", "·", "·", "·", "·", "·", "·"),
            asList("·", "·", "·", "·", "◉", "◉", "◉", "·", "·", "·"),
            asList("·", "·", "·", "·", "·", "◉", "·", "·", "·", "·"),
            asList("·", "·", "·", "·", "·", "·", "·", "·", "·", "·"),
            asList("·", "·", "·", "·", "·", "·", "·", "·", "·", "·"),
            asList("·", "·", "·", "·", "·", "·", "·", "·", "·", "·"),
            asList("·", "·", "·", "·", "·", "·", "·", "·", "·", "·"),
            asList("◉", "·", "·", "·", "·", "·", "·", "·", "·", "◉")
        );
    }

}

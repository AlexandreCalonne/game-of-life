package com.company.board.strategies;

import com.company.board.BoardStrategy;

import java.util.List;
import java.util.Random;

import static com.company.board.BoardUtils.*;

public class RandomStrategy implements BoardStrategy {

    private static final float ALIVE_CHANCE = .1f;

    @Override
    public List<List<String>> generate(int size) {
        return generateEmptyBoard(size).stream()
            .map(row -> row.stream()
                .map(cell -> getRandomCell())
                .toList())
            .toList();
    }

    private String getRandomCell() {
        return new Random().nextFloat() < ALIVE_CHANCE ? ALIVE : DEAD;
    }

}

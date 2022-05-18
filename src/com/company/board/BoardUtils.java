package com.company.board;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {

    public static final String DEAD = "·";
    public static final String ALIVE = "◉";
    public static final String DEAD_PLACEHOLDER = "_";
    public static final String ALIVE_PLACEHOLDER = "o";

    public static List<List<String>> generateEmptyBoard(int size) {
        List<List<String>> board = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());

            for (int j = 0; j < size; j++) {
                board.get(i).add("");
            }
        }

        return board;
    }

}

package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int SIZE = 10;
    private static final int GENERATIONS_COUNT = 10;

    private static final String DEAD = "·";
    private static final String ALIVE = "◉";
    private static final String DEAD_PLACEHOLDER = "_";
    private static final String ALIVE_PLACEHOLDER = "o";

    public static void main(String[] args) {
        List<List<String>> board = generateEmptyBoard();

        printBoard(board);

        for (int i = 0; i < GENERATIONS_COUNT; i++) {
            board = nextGeneration(board);
        }
    }

    private static List<List<String>> nextGeneration(List<List<String>> board) {
        List<List<String>> newBoard = generateEmptyBoard();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newBoard.get(i).set(j, board.get(i).get(j));
                int aliveNeighborsCount = getAliveNeighborsCount(board, i, j);

                if (DEAD.equals(board.get(i).get(j)) && aliveNeighborsCount == 3) {
                    newBoard.get(i).set(j, ALIVE_PLACEHOLDER);
                } else if (ALIVE.equals(board.get(i).get(j)) && (aliveNeighborsCount < 2 || aliveNeighborsCount > 3)) {
                    newBoard.get(i).set(j, DEAD_PLACEHOLDER);
                }
            }
        }

        replacePlaceholders(newBoard);
        printBoard(newBoard);

        return newBoard;
    }

    private static List<List<String>> generateEmptyBoard() {
        List<List<String>> board = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {
            board.add(new ArrayList<>());

            for (int j = 0; j < SIZE; j++) {
                board.get(i).add(DEAD);
            }
        }

        board.get(0).set(0, ALIVE);
        board.get(0).set(1, ALIVE);
        board.get(1).set(0, ALIVE);

        board.get(3).set(4, ALIVE);
        board.get(3).set(5, ALIVE);
        board.get(3).set(6, ALIVE);
        board.get(4).set(5, ALIVE);

        board.get(SIZE - 1).set(6, ALIVE);

        return board;
    }

    private static int getAliveNeighborsCount(List<List<String>> board, int i, int j) {
        int count = 0;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (areCoordinatesInsideBoard(i - 1 + x, j - 1 + y) && ALIVE.equals(board.get(i - 1 + x).get(j - 1 + y)) && !(x == 1 && y == 1)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean areCoordinatesInsideBoard(int i, int j) {
        return i >= 0
            && j >= 0
            && i < SIZE
            && j < SIZE;
    }

    private static void printBoard(List<List<String>> board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board.get(i).get(j) + "  ");
            }
            System.out.println();
        }
        System.out.println("______________________________");
    }

    private static void replacePlaceholders(List<List<String>> board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (DEAD_PLACEHOLDER.equals(board.get(i).get(j))) {
                    board.get(i).set(j, DEAD);
                } else if (ALIVE_PLACEHOLDER.equals(board.get(i).get(j))) {
                    board.get(i).set(j, ALIVE);
                }
            }
        }
    }

}

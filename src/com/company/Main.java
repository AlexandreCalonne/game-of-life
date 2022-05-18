package com.company;

import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Main {

    private static final int BOARD_SIZE = 10;
    private static final String DEAD = "·";
    private static final String ALIVE = "◉";
    private static final String DEAD_PLACEHOLDER = "_";
    private static final String ALIVE_PLACEHOLDER = "o";

    public static void main(String[] args) {
        List<List<String>> board = generateInitialBoard();

        printBoard(board);

        Scanner userInput = new Scanner(System.in);
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("Next generation? (y/n)");

            String input = userInput.nextLine();

            if ("y".equals(input)) {
                board = nextGeneration(board);
            } else if ("n".equals(input)) {
                isContinue = false;
            } else {
                System.out.println("Command not found.");
            }
        }
    }

    private static List<List<String>> nextGeneration(List<List<String>> board) {
        List<List<String>> newBoard = generateInitialBoard();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                newBoard.get(i).set(j, board.get(i).get(j));
                int aliveNeighborsCount = getAliveNeighborsCount(board, i, j);

                if (isDead(board, i, j) && aliveNeighborsCount == 3) {
                    newBoard.get(i).set(j, ALIVE_PLACEHOLDER);
                } else if (isAlive(board, i, j) && (aliveNeighborsCount < 2 || aliveNeighborsCount > 3)) {
                    newBoard.get(i).set(j, DEAD_PLACEHOLDER);
                }
            }
        }

        replacePlaceholders(newBoard);
        printBoard(newBoard);

        return newBoard;
    }

    private static List<List<String>> generateInitialBoard() {
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

    private static int getAliveNeighborsCount(List<List<String>> board, int i, int j) {
        int count = 0;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int neighborX = i - 1 + x;
                int neighborY = j - 1 + y;

                if (areCoordinatesInsideBoard(neighborX, neighborY) && isAlive(board, neighborX, neighborY) && isNotInitialCell(x, y)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isNotInitialCell(int x, int y) {
        return !(x == 1 && y == 1);
    }

    private static boolean isAlive(List<List<String>> board, int newX, int newY) {
        return ALIVE.equals(board.get(newX).get(newY));
    }

    private static boolean isDead(List<List<String>> board, int i, int j) {
        return DEAD.equals(board.get(i).get(j));
    }

    private static boolean areCoordinatesInsideBoard(int i, int j) {
        return i >= 0
            && j >= 0
            && i < BOARD_SIZE
            && j < BOARD_SIZE;
    }

    private static void printBoard(List<List<String>> board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board.get(i).get(j) + "  ");
            }
            System.out.println();
        }
        System.out.println("______________________________");
    }

    private static void replacePlaceholders(List<List<String>> board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (DEAD_PLACEHOLDER.equals(board.get(i).get(j))) {
                    board.get(i).set(j, DEAD);
                } else if (ALIVE_PLACEHOLDER.equals(board.get(i).get(j))) {
                    board.get(i).set(j, ALIVE);
                }
            }
        }
    }

}

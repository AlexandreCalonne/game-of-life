package com.company;

import com.company.board.BoardContext;
import com.company.board.strategies.RandomStrategy;

import java.util.List;
import java.util.Scanner;

import static com.company.board.BoardUtils.*;

public class Main {

    public static void main(String[] args) {
        BoardContext boardContext = new BoardContext();
        boardContext.setStrategy(new RandomStrategy());

        List<List<String>> board = boardContext.generate(15);
        printBoard(board);

        Scanner userInput = new Scanner(System.in);
        boolean isContinue = true;
        int currentGeneration = 1;

        while (isContinue) {
            System.out.println("Go to generation " + ++currentGeneration + "? (y/n)");

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
        List<List<String>> newBoard = generateEmptyBoard(board.size());

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
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

    private static int getAliveNeighborsCount(List<List<String>> board, int i, int j) {
        int count = 0;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int neighborX = i - 1 + x;
                int neighborY = j - 1 + y;

                if (areCoordinatesInsideBoard(neighborX, neighborY, board) && isAlive(board, neighborX, neighborY) && isNotInitialCell(x, y)) {
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

    private static boolean areCoordinatesInsideBoard(int i, int j, List<List<String>> board) {
        return i >= 0
            && j >= 0
            && i < board.size()
            && j < board.size();
    }

    private static void printBoard(List<List<String>> board) {
        board.forEach(row -> {
            row.forEach(cell -> System.out.print(cell + "  "));
            System.out.println();
        });
        System.out.println("______________________________");
    }

    private static void replacePlaceholders(List<List<String>> board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (DEAD_PLACEHOLDER.equals(board.get(i).get(j))) {
                    board.get(i).set(j, DEAD);
                } else if (ALIVE_PLACEHOLDER.equals(board.get(i).get(j))) {
                    board.get(i).set(j, ALIVE);
                }
            }
        }
    }

}

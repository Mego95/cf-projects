package gr.aueb.cf.projects;

import java.util.Scanner;

public class TicTacToeApp {
    public static final char[][] board = new char[3][3];
    public static int rounds = 0;
    public static final Scanner in = new Scanner(System.in);
    public static char winningPlayerChar = ' ';

    public static void main(String[] args) {
        String input;
        String[] strMove = new String[2];
        int[] intMove = new int[2];

        initBoard();
        printBoard();

        while (!isGameOver()) {
            try {
                input = getPlayerInput();
                strMove = inputToMove(input);
                intMove = moveToPosition(strMove);
                enterPlayerMoveToBoard(intMove);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            printBoard();
            rounds++;
        }
        printWinner();
    }

    public static void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println("  1  2  3");
        System.out.printf("A %c|%c|%c\n", board[0][0], board[0][1], board[0][2]);
        System.out.println("  -----");
        System.out.printf("B %c|%c|%c\n", board[1][0], board[1][1], board[1][2]);
        System.out.println("  -----");
        System.out.printf("C %c|%c|%c\n", board[2][0], board[2][1], board[2][2]);
    }

    public static String getPlayerInput() {
        System.out.printf("Player %d move:\n", (rounds % 2 == 0) ? 1 : 2);
        return in.nextLine().toUpperCase().trim();
    }

    public static String[] inputToMove(String input) throws Exception {
        String[] move = new String[2];

        if (input.length() != 2) throw new Exception("Please give valid move");

        move[0] = input.substring(0,1);
        move[1] = input.substring(1,2);
        return move;
    }

    public static void enterPlayerMoveToBoard(int[] playerMove) throws Exception {
        if (isPlayerMoveValid(playerMove) && isBoardSpaceEmpty(playerMove)) {
            board[playerMove[0]][playerMove[1]] = (rounds % 2 == 0) ? 'O' : 'X';
        } else {
            throw new Exception("Please input a blank board position");
        }
    }

    public static boolean isPlayerMoveValid(int[] playerMove) {
        return !(playerMove[0] > 2 || playerMove[0] < 0 || playerMove[1] > 2 || playerMove[1] < 0);
    }

    public static boolean isBoardSpaceEmpty(int[] playerMove) {
        return board[playerMove[0]][playerMove[1]] == ' ';
    }

    public static int[] moveToPosition(String[] move) throws Exception {
        int[] position = new int[2];

        try {
            char row = move[0].charAt(0);
            position[0] = (int) row - 65;

            position[1] = Integer.parseInt(move[1]) - 1;

            return position;
        } catch (Exception e) {
            throw new Exception("Please input a valid board position");
        }

    }

    public static void printWinner() {
        if (winningPlayerChar == 'O') {
            System.out.println("Player 1 Wins!");
        } else if (winningPlayerChar == 'X') {
            System.out.println("Player 2 Wins!");
        } else {
            System.out.println("Game is tied!");
        }
    }

    public static boolean isGameOver() {
        return isGameWon() || rounds >= 9;
    }

    public static boolean isGameWon() {
        return hasHorizontalWin() || hasVerticalWin() || hasDiagonalWin();
    }

    public static boolean hasHorizontalWin() {
        boolean isWin = false;

        if ((board[0][0] == board[0][1]) && (board[0][1] == board[0][2]) && board[0][0] != ' ') {
            isWin = true;
            winningPlayerChar = board[0][0];
        } else if ((board[1][0] == board[1][1]) && (board[1][1] == board[1][2]) && board[1][0] != ' ') {
            isWin = true;
            winningPlayerChar = board[1][0];
        } else if ((board[2][0] == board[2][1]) && (board[2][1] == board[2][2]) && board[2][0] != ' ') {
            isWin = true;
            winningPlayerChar = board[2][0];
        }

        return isWin;
    }

    public static boolean hasVerticalWin() {
        boolean isWin = false;

        if ((board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) && board[0][0] != ' ') {
            isWin = true;
            winningPlayerChar = board[0][0];
        } else if ((board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) && board[0][1] != ' ') {
            isWin = true;
            winningPlayerChar = board[0][1];
        } else if ((board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) && board[0][2] != ' ') {
            isWin = true;
            winningPlayerChar = board[0][2];
        }

        return isWin;
    }

    public static boolean hasDiagonalWin() {
        boolean isWin = false;

        if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && board[1][1] != ' ') {
            isWin = true;
            winningPlayerChar = board[0][0];
        } else if ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && board[1][1] != ' ') {
            isWin = true;
            winningPlayerChar = board[0][2];
        }
        return isWin;
    }
}

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static int playerWinCount = 0, computerWinCount = 0;

    public static void main(String[] args) {
        System.out.println("Let's play TIC-TAC-TOE!");

        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();

        char playerSymbol = choosePlayerSymbol();
        char computerSymbol = chooseComputerSymbol(playerSymbol);

        String menu = "StartGame";

        programLoop:
        while (true) {
            switch (menu) {
                case "StartGame":
                case "1":
                    playGame(playerName, playerSymbol, computerSymbol);
                    menu = printMenu();
                    break;
                case "0":
                    break programLoop;
                default:
                    menu = printMenu();
                    break;
            }
        }
    }

    private static Character choosePlayerSymbol() {
        while (true) {
            System.out.println("Choose your symbol [X/O]");
            switch (scanner.nextLine().toUpperCase()) {
                case "X":
                    return 'X';
                case "O":
                    return 'O';
                default:
                    break;
            }
        }
    }

    private static Character chooseComputerSymbol(char playerSymbol) {
        if (playerSymbol == 'X') {
            return 'O';
        } else {
            return 'X';
        }
    }

    private static void playGame(String playerName, char playerSymbol, char computerSymbol) {
        char[][] startBoard = {{'1', '2', '3'}, {'4', '5', '6',}, {'7', '8', '9'}};
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' ',}, {' ', ' ', ' '}};
        printBoard(startBoard);

        while (true) {
            playerTurn(board, playerName, playerSymbol);
            printBoard(board);
            if (isGameOver(board, playerName, playerSymbol, computerSymbol)) {
                break;
            }
            computerTurn(board, computerSymbol);
            printBoard(board);
            if (isGameOver(board, playerName, playerSymbol, computerSymbol)) {
                break;
            }
        }
    }

    private static String printMenu() {
        System.out.println("1. Play again");
        System.out.println("0. Exit");
        return scanner.nextLine();
    }

    private static boolean isGameOver(char[][] board, String playerName, char playerSymbol, char computerSymbol) {
        if (hasPlayerWon(board, playerSymbol)) {
            System.out.println("Game Over - " + playerName + " wins!");
            playerWinCount++;
            System.out.println(playerName + " " + playerWinCount + ":" + computerWinCount + " Computer");
            return true;
        }

        if (hasPlayerWon(board, computerSymbol)) {
            System.out.println("Game Over - Computer wins!");
            computerWinCount++;
            System.out.println(playerName + " " + playerWinCount + ":" + computerWinCount + " Computer");
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("Game Over - Tie");
        System.out.println(playerName + " " + playerWinCount + ":" + computerWinCount + " Computer");
        return true;
    }

    private static boolean hasPlayerWon(char[][] board, char symbol) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                        (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                        (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                        (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                        (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                        (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                        (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                        (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void computerTurn(char[][] board, char computerSymbol) {
        Random random = new Random();
        String position;

        while (true) {
            position = Integer.toString(random.nextInt(1, 10));
            if (isValidMove(board, position)) {
                System.out.println("Computer move:");
                placeMove(board, position, computerSymbol);
                break;
            }
        }
    }

    private static void playerTurn(char[][] board, String playerName, char playerSymbol) {
        String position;

        while (true) {
            System.out.println(playerName + ", place your move [1-9]:");
            position = scanner.nextLine();
            if (isValidMove(board, position)) {
                placeMove(board, position, playerSymbol);
                break;
            } else {
                System.out.println("Wrong move");
                printBoard(board);
            }
        }
    }

    private static boolean isValidMove(char[][] board, String position) {
        switch (position) {
            case "1":
                return board[0][0] == ' ';
            case "2":
                return board[0][1] == ' ';
            case "3":
                return board[0][2] == ' ';
            case "4":
                return board[1][0] == ' ';
            case "5":
                return board[1][1] == ' ';
            case "6":
                return board[1][2] == ' ';
            case "7":
                return board[2][0] == ' ';
            case "8":
                return board[2][1] == ' ';
            case "9":
                return board[2][2] == ' ';
            default:
                return false;
        }
    }

    private static void placeMove(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
            default:
                break;
        }
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
                if (j < board.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("-".repeat(5));
            }
        }
    }
}

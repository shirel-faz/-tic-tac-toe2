import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        char[][] gameBoard = {{'_', '_', '_'},
                              {'_', '_', '_'},
                              {'_', '_', '_'}};

        Scanner sorek = new Scanner(System.in);
        System.out.println("Welcome to the TIC TAC TOE game");
        System.out.print("Enter the first player name: ");
        String play1 = sorek.nextLine();
        System.out.print("Enter the second player name: ");
        String play2 = sorek.nextLine();

        final String EnterRowMsg = "Enter a row number (0, 1, or 2): ";
        final String EnterColMsg = "Enter a column number (0, 1, or 2): ";
        boolean player1 = true;
        boolean gameEnded = false;
        char cell = '_';
        int row = 0;
        int col = 0;



        while (!gameEnded) {

            printBord(gameBoard);

            if (player1) {
                System.out.println(play1 + "'s Turn (X): ");
            } else {
                System.out.println(play2 + "'s Turn (O): ");
            }


            if (player1) {
                cell = 'X';
            } else {
                cell = 'O';
            }


            while (true) {
                System.out.print(EnterRowMsg);
                row = validateInput(EnterRowMsg);

                System.out.print(EnterColMsg);
                col = validateInput(EnterColMsg);
                if (row < 0 || col < 0 || row > 2 || col > 2) {
                    System.out.println("This position is off the "+
                            "bounds of the board! Try again.");
                } else if (gameBoard[row][col] != '_') {
                    System.out.println("Someone has already used this spot so Try again.");
                } else {
                    break;
                }

            }


            gameBoard[row][col] = cell;


            if (checkWhoWon(gameBoard) == 'X') {
                System.out.println(play1 + " has won!");
                gameEnded = true;
            } else if (checkWhoWon(gameBoard) == 'O') {
                System.out.println(play2 + " has won!");
                gameEnded = true;
            } else {
                if (checkIfBoardIsFull(gameBoard)) {
                    System.out.println("No one won!");
                    gameEnded = true;
                } else {
                    player1 = !player1;
                }
            }
        }
        printBord(gameBoard);
    }

    public static void printBord(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("  " + board[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static char checkWhoWon(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '_') {
                return board[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '_') {
                return board[0][j];
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '_') {
            return board[0][0];
        }

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '_') {
            return board[2][0];
        }

        return ' ';
    }


    public static int validateInput(String msg) {
        Scanner sorek = new Scanner(System.in);
        final String[] allow  = { "0", "1", "2"};

        String input = sorek.nextLine();

        while(!( Arrays.stream(allow).anyMatch(input::equals) )){
            System.out.println(input + " doesn't match to the request: 0/1/2");
            if(msg.length() > 0){
                System.out.println(msg);
            }
            input = sorek.nextLine();
        }
        return Integer.parseInt(input);
    }

    public static boolean checkIfBoardIsFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }
}
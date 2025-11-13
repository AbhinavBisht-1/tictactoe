import java.util.Scanner;

public class TicTacToe {
    static void printBoard(char[][] a) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean checkWinner(char[][] a) {
        for (int i = 0; i < 3; i++) {
            if (a[i][0] != '*' && a[i][0] == a[i][1] && a[i][1] == a[i][2]) return true;
            if (a[0][i] != '*' && a[0][i] == a[1][i] && a[1][i] == a[2][i]) return true;
        }
        if (a[0][0] != '*' && a[0][0] == a[1][1] && a[1][1] == a[2][2]) return true;
        if (a[0][2] != '*' && a[0][2] == a[1][1] && a[1][1] == a[2][0]) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] a = new char[3][3];
        String ch1, ch2;
        char mood;

        System.out.println("**** WELCOME TO TIC TAC TOE ****");

        do {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    a[i][j] = '*';

            System.out.print("First player name: ");
            ch1 = sc.next();

            System.out.print("Second player name: ");
            ch2 = sc.next();

            int turn;
            boolean winnerFound = false;

            for (turn = 0; turn < 9; turn++) {
                printBoard(a);
                int r = -1, c = -1;
                boolean valid = false;

                while (!valid) {
                    try {
                        if (turn % 2 == 0)
                            System.out.print(ch1 + ", enter row and col (1-3): ");
                        else
                            System.out.print(ch2 + ", enter row and col (1-3): ");

                        r = sc.nextInt();
                        c = sc.nextInt();

                        if (r < 1 || r > 3 || c < 1 || c > 3) {
                            System.out.println("Invalid range! Enter numbers between 1 and 3.");
                            continue;
                        }
                        if (a[r - 1][c - 1] != '*') {
                            System.out.println("That spot is already filled. Try again.");
                            continue;
                        }

                        valid = true;
                    } catch (Exception e) {
                        System.out.println("Invalid input! Please enter numbers only.");
                        sc.nextLine();
                    }
                }

                a[r - 1][c - 1] = (turn % 2 == 0) ? 'X' : 'O';

                if (checkWinner(a)) {
                    printBoard(a);
                    System.out.println((turn % 2 == 0 ? ch1 : ch2) + " wins!");
                    winnerFound = true;
                    break;
                }
            }

            if (!winnerFound)
                System.out.println("Draw!");

            System.out.print("Play again? (N/n to play again): ");
            mood = sc.next().charAt(0);

        } while (mood == 'n' || mood == 'N');

        sc.close();
        System.out.println("Game over. Thanks for playing!");
    }
}

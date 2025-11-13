#include <stdio.h>

void printBoard(char a[3][3]) {
    for (int i=0;i<3;i++) {
        for (int j=0;j<3;j++)
            printf("%c ", a[i][j]);
        printf("\n");
    }
}

int checkWinner(char a[3][3]) {
    for (int i=0;i<3;i++) {
        if (a[i][0] != '*' && a[i][0] == a[i][1] && a[i][1] == a[i][2]) return 1;
        if (a[0][i] != '*' && a[0][i] == a[1][i] && a[1][i] == a[2][i]) return 1;
    }
    if (a[0][0] != '*' && a[0][0] == a[1][1] && a[1][1] == a[2][2]) return 1;
    if (a[0][2] != '*' && a[0][2] == a[1][1] && a[1][1] == a[2][0]) return 1;
    return 0;
}

int main() {
    char a[3][3], ch1[15], ch2[15], mood;
    printf("****WELCOME TO TIC TAC TOE****\n");

    do {
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                a[i][j] = '*';

        printf("First player name: ");
        scanf("%s", ch1);
        printf("Second player name: ");
        scanf("%s", ch2);

        int turn;
        for (turn=0; turn<9; turn++) {
            printBoard(a);
            int r, c;
            while (1) {
                if (turn % 2 == 0)
                printf("%s enter row and col (1-3): ", ch1);
                else
                printf("%s enter row and col (1-3): ", ch2);
                
                int result = scanf("%d %d", &r, &c);
                if (result != 2) {
                    printf("Invalid input! Please enter numbers.\n");
                    while (getchar() != '\n');
                    continue;
                }
                if (r < 1 || r > 3 || c < 1 || c > 3) {
                    printf("Invalid range! Enter between 1 and 3.\n");
                    continue;
                }
                if (a[r-1][c-1] != '*') {
                    printf("That spot is already filled. Try again.\n");
                    continue;
                }
                break;
            }

            if (r<1 || r>3 || c<1 || c>3 || a[r-1][c-1] != '*') {
                printf("Invalid move, try again.\n");
                turn--;
                continue;
            }

            a[r-1][c-1] = (turn % 2 == 0) ? 'X' : 'O';

            if (checkWinner(a)) {
                printBoard(a);
                printf("%s wins!\n", (turn % 2 == 0) ? ch1 : ch2);
                break;
            }
        }

        if (turn == 9)
            printf("Draw!\n");

        printf("Play again? (N/n to play again): ");
        scanf(" %c", &mood);
    } while (mood == 'n' || mood == 'N');

    return 0;
}

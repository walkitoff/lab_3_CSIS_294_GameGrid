import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class GameGrid {
    int[][] aiGrid = new int[10][10];
    String[][] aiBoard = new String[10][10];
    SecureRandom oRand = new SecureRandom();
    int iWallChance;
    int iTemp;

    //game & board pieces
    String wall = "\u274C"; //red X
    String clearedSpace = "\u2705"; //Green check mark
    String boat = "\u26F5";  //boat
    String boardPiece = "\u2652";  //purple water

    //game state
    int iUserRow = 0;
    int iUserCol = 0;
    char uInput;
    Scanner sc = new Scanner(System.in);

    public void run() {
        iWallChance  = oRand.nextInt(16) + 10; // random 15-25 %
        System.out.println("WALL CHANCE: " + iWallChance + "%");

        //fill aiBoard
        for (String[] row : aiBoard) {
            Arrays.fill(row, boardPiece);
        }

        //set initial game board
        for (int y = 0; y < aiGrid.length; y++) {
            for (int x = 0; x < aiGrid[y].length; x++) {
                iTemp = oRand.nextInt(100) + 1;  // random 1 - 100
                if (y == 0 && x == 0) {
                    aiGrid[y][x] = 0;
                    aiBoard[y][x] = boat;
                } else if (iTemp < iWallChance) {
                    aiGrid[y][x] = 1;
                } else {
                    aiGrid[y][x] = 0;
                }
                System.out.printf("%3s", aiBoard[y][x]);
            }
            /**
             * TODO: UN-COMMENT to display a Grid with answers
            System.out.print("\t\t");
            for(int x = 0; x < aiGrid[y].length; x++){
                System.out.printf("%3s", aiGrid[y][x]);
            }
            */
            System.out.println();
        }


        while (true) {
            System.out.println("Type 'R' for right or 'D' for down: ");
            uInput = Character.toUpperCase(sc.next().charAt(0));
            if (uInput == 'R') {
                if (aiGrid[iUserCol][iUserRow + 1] == 1) {
                    aiBoard[iUserCol][iUserRow + 1] = wall;
                   printGameBoard();
                    break;
                } else {
                    aiBoard[iUserCol][iUserRow] = clearedSpace;
                    iUserRow++;
                    aiBoard[iUserCol][iUserRow] = boat;

                }
            }else if (uInput == 'D') {
                if (aiGrid[iUserCol + 1][iUserRow] == 1) {
                    aiBoard[iUserCol + 1][iUserRow] = wall;
                    printGameBoard();
                    break;
                } else {
                    aiBoard[iUserCol][iUserRow] = clearedSpace;
                    iUserCol++;
                    aiBoard[iUserCol][iUserRow] = boat;
                }
            }
           printGameBoard();
            if(iUserRow == 9 || iUserCol == 9){
                System.out.println("***********************");
                System.out.println("        YOU WIN        ");
                System.out.println("***********************");
                System.exit(0);
            }
        }

        System.out.println("GAME OVER....YOUR SHIP HIT A WALL!!!!");

    }


    public void printGameBoard(){
        for (int y = 0; y < aiBoard.length; y++) {
            for (int x = 0; x < aiBoard[y].length; x++){
                System.out.printf("%3s", aiBoard[y][x]);
            }
            System.out.println();
        }
    }


}

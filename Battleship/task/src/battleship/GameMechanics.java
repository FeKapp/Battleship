package battleship;

import java.util.Scanner;

public class GameMechanics {

    public static void shootingPhase (Grid gridPlayer1, Grid gridPlayer2) {

        boolean allShipsSunk = false;
        while (!allShipsSunk) {
            GameMechanics.pressEnterToContinue();
            allShipsSunk = singleShotHandler(gridPlayer2, gridPlayer1, "Player 1");
            if (allShipsSunk) {
                break;
            }
            GameMechanics.pressEnterToContinue();
            allShipsSunk = singleShotHandler(gridPlayer1, gridPlayer2, "Player 2");
        }
    }

    private static boolean singleShotHandler(Grid oponentGrid, Grid yourGrid, String player) {

        oponentGrid.printWarGrid();
        System.out.print("---------------------");
        yourGrid.printGrid();
        System.out.printf("\n%s, it's your turn: %n", player);

        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        do {
            String input = scanner.nextLine();

            int row = input.charAt(0) - 'A';
            int column = Integer.parseInt(input.substring(1)) - 1;

            if (row < 0 || row > 9 || column < 0 || column > 9) {
                System.out.printf("\nError! You entered the wrong coordinates! Try again: %n");
                continue;
            }

            if (oponentGrid.isCellEmpty(row, column) || oponentGrid.isMiss(row, column)) {
                oponentGrid.markCell(row, column, 'M');
                oponentGrid.printWarGrid();
                System.out.println("\nYou missed!");
                validInput = true;
            } else if (oponentGrid.isShip(row, column)) {
                oponentGrid.markCell(row, column, 'X');
                oponentGrid.markHitOnWarShipGrid(row, column);
                oponentGrid.incShipFeildsHit();
                oponentGrid.printWarGrid();
                if (oponentGrid.isShipSunk(row, column)) {
                    if (oponentGrid.isAllShipFieldsHit()) {
                        System.out.println("\nYou sank the last ship. You won. Congratulations!");
                        return true;
                    } else {
                        System.out.println("\nYou sank a ship! Specify a new target: ");
                    }
                } else {
                    System.out.println("\nYou hit a ship! Try again: ");
                }
                validInput = true;
            } else if (oponentGrid.isHit(row, column)) {
                oponentGrid.printWarGrid();
                System.out.println("\nYou hit a ship!");
                validInput = true;
            } else {
                System.out.printf("\nError! You entered the wrong coordinates! Try again: %n");
            }
        } while (!validInput);
        return false;
    }

    public static void pressEnterToContinue() {
        System.out.println("\nPress Enter and pass the move to another player\n...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}

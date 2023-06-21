package battleship;

import java.util.Scanner;

public class GridInputHandler {
    public static void getInput(Grid grid) {
        Scanner scanner = new Scanner(System.in);
        for (Ship ship: grid.getShips()) {
            boolean validInput = false;
            String message = String.format("Enter the coordinates of the %s (%d cells): %n", ship.getName(), ship.getLength());
            grid.printGrid();
            while (!validInput) {
                System.out.println("\n" + message);

                String input = scanner.nextLine();
                String[] coordinates = input.split(" ");

                String areCoordinatesValid = areCoordinatesValid(grid, coordinates, ship);
                if (areCoordinatesValid.equals("valid")) {
                    int[] resolvedCoordinates = resolveCoordinates(coordinates);
                    boolean isHorizontal = isHorizontal(resolvedCoordinates);
                    grid.placeShip(resolvedCoordinates[0], resolvedCoordinates[1], ship, isHorizontal);
                    validInput = true;
                } else {
                    message = areCoordinatesValid;
                }
            }
        }
        grid.printGrid();
    }

    public static String areCoordinatesValid (Grid grid, String[] coordinates, Ship ship) {
        int[] resolvedCoordinates = resolveCoordinates(coordinates);
        boolean isHorizontal = isHorizontal(resolvedCoordinates);

         if (isDiagonal(resolvedCoordinates)) {
            return String.format("Error! Wrong ship location! Try again: %n");
        } else if (!isShipLengthValid(resolvedCoordinates, ship.getLength())) {
            return String.format("Error! Wrong length of the " + ship.getName() + "! Try again: %n");
        } else if (!grid.hasPlaceForShip(resolvedCoordinates[0], resolvedCoordinates[1],ship.getLength(), isHorizontal) || !grid.hasEnoughSpaceToOtherShips(resolvedCoordinates[0], resolvedCoordinates[1], ship.getLength(), isHorizontal)) {
            return String.format("Error! You placed it too close to another one. Try again: %n");
        }

        return "valid";
    }

    public static int[] resolveCoordinates (String[] coordinates) {
        int[] resolvedCoordinates = new int[4];
        int rowStart = coordinates[0].charAt(0) - 'A';
        int columnStart= Integer.parseInt(coordinates[0].substring(1)) - 1;
        int rowEnd = coordinates[1].charAt(0) - 'A';
        int columnEnd = Integer.parseInt(coordinates[1].substring(1)) - 1;

        if (rowStart > rowEnd) {
            resolvedCoordinates[0] = rowEnd;
            resolvedCoordinates[2] = rowStart;
        } else {
            resolvedCoordinates[0] = rowStart;
            resolvedCoordinates[2] = rowEnd;
        }

        if (columnStart > columnEnd) {
            resolvedCoordinates[1] = columnEnd;
            resolvedCoordinates[3] = columnStart;
        } else {
            resolvedCoordinates[1] = columnStart;
            resolvedCoordinates[3] = columnEnd;
        }

        return resolvedCoordinates;
    }

    public static boolean isShipLengthValid(int[] coordinates, int length) {
        if (isHorizontal(coordinates)) {
            return coordinates[3] - coordinates[1] + 1 == length;
        } else {
            return coordinates[2] - coordinates[0] + 1 == length;
        }
    }

    public static boolean isHorizontal(int[] coordinates) {
        return coordinates[0] == coordinates[2];
    }

    public static boolean isDiagonal(int[] coordinates) {
        return coordinates[0] != coordinates[2] && coordinates[1] != coordinates[3];
    }


}

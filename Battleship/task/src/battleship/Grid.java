package battleship;

public class Grid {
    int rows;
    int columns;
    char[][] grid;

    Ship[][] warShipGrid;
    Ship[] ships;

    int numberOfShipFields;
    int ShipFeildsHit;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];
        ships = new Ship[5];
        warShipGrid = new Ship[rows][columns];
        numberOfShipFields = 0;
        ShipFeildsHit = 0;
    }

    public void initGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns ; j++) {
                grid[i][j] = '~';
            }
        }
    }

    public void printGrid() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < rows; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < columns ; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printWarGrid() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < rows; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < columns ; j++) {
                if (grid[i][j] == 'O') {
                    System.out.print("~ ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public void addShip(Ship ship, int shipNumber) {
        ships[shipNumber - 1] = ship;
    }

    public void placeShip(int row, int column, Ship ship, boolean isHorizontal) {
        int length = ship.getLength();
        if (isHorizontal) {
            for (int i = 0; i < length; i++) {
                grid[row][column + i] = 'O';
                warShipGrid[row][column + i] = ship;
                numberOfShipFields++;
            }
        } else {
            for (int i = 0; i < length; i++) {
                grid[row + i][column] = 'O';
                warShipGrid[row + i][column] = ship;
                numberOfShipFields++;
            }
        }
    }

    public boolean hasPlaceForShip(int row, int column, int length, boolean isHorizontal) {
        try {
            if (isHorizontal) {
                for (int i = 0; i < length; i++) {
                    if (grid[row][column + i] == 'O') {
                        return false;
                    }
                }
            } else {
                for (int i = 0; i < length; i++) {
                    if (grid[row + i][column] == 'O') {
                        return false;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean hasEnoughSpaceToOtherShips(int row, int column, int length, boolean isHorizontal) {
        try {
            if (isHorizontal) {
                // Check if there is a ship above
                if(row -1 >= 0) {
                    for (int i = 0; i < length; i++) {
                        if (grid[row - 1][column + i] == 'O') {
                            return false;
                        }
                    }
                }
                // Check if there is a ship below
                if(row + 1 < rows) {
                    for (int i = 0; i < length; i++) {
                        if (grid[row + 1][column + i] == 'O') {
                            return false;
                        }
                    }
                }
                // Check if there i as ship on the left
                if(column - 1 >= 0) {
                    if (grid[row][column - 1] == 'O') {
                        return false;
                    }
                    if(row - 1 >= 0) {
                        if (grid[row - 1][column - 1] == 'O') {
                            return false;
                        }
                    }
                    if(row + 1 < rows) {
                        if (grid[row + 1][column - 1] == 'O') {
                            return false;
                        }
                    }
                }
                // Check if there is a ship on the right
                if(column + length < columns) {
                    if (grid[row][column + length] == 'O') {
                        return false;
                    }
                    if(row - 1 >= 0) {
                        if (grid[row - 1][column + length] == 'O') {
                            return false;
                        }
                    }
                    if(row + 1 < rows) {
                        if (grid[row + 1][column + length] == 'O') {
                            return false;
                        }
                    }
                }
            } else {
                // Check if there is a ship on the left
                if(column - 1 >= 0) {
                    for (int i = 0; i < length; i++) {
                        if (grid[row + i][column - 1] == 'O') {
                            return false;
                        }
                    }
                }
                // Check if there is a ship on the right
                if(column + 1 < columns) {
                    for (int i = 0; i < length; i++) {
                        if (grid[row + i][column + 1] == 'O') {
                            return false;
                        }
                    }
                }
                // Check if there i as ship above
                if(row - 1 >= 0) {
                    if (grid[row - 1][column] == 'O') {
                        return false;
                    }
                    if(column - 1 >= 0) {
                        if (grid[row - 1][column - 1] == 'O') {
                            return false;
                        }
                    }
                    if(column + 1 < columns) {
                        if (grid[row - 1][column + 1] == 'O') {
                            return false;
                        }
                    }
                }
                // Check if there is a ship below
                if(row + length < rows) {
                    if (grid[row + length][column] == 'O') {
                        return false;
                    }
                    if(column - 1 >= 0) {
                        if (grid[row + length][column - 1] == 'O') {
                            return false;
                        }
                    }
                    if(column + 1 < columns) {
                        if (grid[row + length][column + 1] == 'O') {
                            return false;
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean isShip(int row, int column) {
        return grid[row][column] == 'O';
    }

    public boolean isHit(int row, int column) {
        return grid[row][column] == 'X';
    }

    public void markHit(int row, int column) {
        grid[row][column] = 'X';
    }

    public void markCell(int row, int column, char mark) {
        grid[row][column] = mark;
    }

    public void markHitOnWarShipGrid(int row, int column) {
        warShipGrid[row][column].hit();
    }

    public boolean isShipSunk(int row, int column) {
        return warShipGrid[row][column].isSunk();
    }

    public boolean isMiss(int row, int column) {
        return grid[row][column] == 'M';
    }

    public boolean isCellEmpty(int row, int column) {
        return grid[row][column] == '~';
    }


    public Ship[] getShips() {
        return ships;
    }

    public int getNumberOfShipFields() {
        return numberOfShipFields;
    }

    public void incShipFeildsHit() {
        ShipFeildsHit++;
    }

    public boolean isAllShipFieldsHit() {
        return ShipFeildsHit == numberOfShipFields;
    }
}

package battleship;

public class Main {

    public static void main(String[] args) {
        // Init grid
        Grid gridPlayer1 = new Grid(10, 10);
        gridPlayer1.initGrid();

        Grid gridPlayer2 = new Grid(10, 10);
        gridPlayer2.initGrid();

        // Create Ships
        Ship aircraftCarrier = new Ship(5, "Aircraft Carrier");
        Ship battleship = new Ship(4, "Battleship");
        Ship submarine = new Ship(3, "Submarine");
        Ship cruiser = new Ship(3, "Cruiser");
        Ship destroyer = new Ship(2, "Destroyer");

        // Add ships to grid (player 1)
        gridPlayer1.addShip(aircraftCarrier, 1);
        gridPlayer1.addShip(battleship, 2);
        gridPlayer1.addShip(submarine, 3);
        gridPlayer1.addShip(cruiser, 4);
        gridPlayer1.addShip(destroyer, 5);

        // Add ships to grid (player 2)
        gridPlayer2.addShip(aircraftCarrier, 1);
        gridPlayer2.addShip(battleship, 2);
        gridPlayer2.addShip(submarine, 3);
        gridPlayer2.addShip(cruiser, 4);
        gridPlayer2.addShip(destroyer, 5);

        System.out.println("Player 1, place your ships on the game field");
        GridInputHandler.getInput(gridPlayer1);

        GameMechanics.pressEnterToContinue();

        System.out.println("Player 2, place your ships on the game field");
        GridInputHandler.getInput(gridPlayer2);


        GameMechanics.shootingPhase(gridPlayer1, gridPlayer2);
    }
}

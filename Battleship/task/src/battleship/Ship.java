package battleship;

public class Ship {
    private int length;
    private String name;
    private int hits;

    public Ship(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits == length;
    }
}

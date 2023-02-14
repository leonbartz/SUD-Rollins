package helpers.coordinate;

public enum CardinalDirection {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0);

    private final int xMod;
    private final int yMod;

    CardinalDirection(int xMod, int yMod) {
        this.xMod = xMod;
        this.yMod = yMod;
    }
     public int getXMod() {
        return xMod;
     }
     public int getYMod() {
        return yMod;
     }
}

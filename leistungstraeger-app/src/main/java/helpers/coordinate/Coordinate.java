package helpers.coordinate;

import lombok.Getter;
import lombok.Setter;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Coordinate {

    @Getter
    @Setter
    private int xPos;
    @Getter
    @Setter
    private int yPos;

    public Coordinate(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Coordinate getClone() {
        return new Coordinate(xPos, yPos);
    }

    public static boolean equals(Coordinate c1, Coordinate c2) {
        return c1.xPos == c2.xPos && c1.yPos == c2.yPos;
    }

    public static int distance(Coordinate c1, Coordinate c2) {
        return (int) Math.sqrt(Math.pow(c1.xPos - c2.xPos, 2) + Math.pow(c1.yPos-c2.yPos, 2));
    }

    public static boolean inRange(Coordinate c1, Coordinate c2, int range) {
        return distance(c1, c2) <= range;
    }

}

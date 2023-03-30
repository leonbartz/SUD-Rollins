package backend.obstacle;

import backend.abstract_object.AbstractObject;
import helpers.coordinate.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wall extends AbstractObject {
    protected Wall(String name, UUID owner, String sprite, Coordinate startingPosition) {
        super(name, owner, sprite, startingPosition);
    }

    public static List<Wall> getWallBulk(Coordinate ...coords) {
        List<Wall> walls = new ArrayList<>();
        for (Coordinate coord : coords) {
            walls.add(new Wall("wall", new UUID(1, 1), "wall_right.png", coord));
        }
        return walls;
    }
}

package backend.game_map.room;

import backend.abstract_object.AbstractObject;
import helpers.coordinate.Coordinate;

import java.util.UUID;

public class Wall extends AbstractObject {
    protected Wall(String name, UUID owner, String sprite, Coordinate startingPosition) {
        super(name, owner, sprite, startingPosition);
    }
}

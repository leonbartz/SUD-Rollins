package core.object;

import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * While moving is done by the Repository of a room, only {@class MovingAbstractObject} are able to
 * move during a turn. This class has a field range, which sets the maximum moving distance for this
 * object.
 */
@Getter
@Setter
public class MovingAbstractObject extends AbstractObject {

    // How many fields this object is able to move in one turn
    private int range;

    protected MovingAbstractObject(final String name,
                                   final UUID owner,
                                   final int movingRange,
                                   final String sprite,
                                   Coordinate startingPosition) {
        super(name, owner, sprite, startingPosition);
        range = movingRange;
    }

}

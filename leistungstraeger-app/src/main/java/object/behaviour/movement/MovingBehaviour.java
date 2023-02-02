package object.behaviour.movement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MovingBehaviour {

    private int movementRange;

    // TODO what to return
    abstract void move();
}

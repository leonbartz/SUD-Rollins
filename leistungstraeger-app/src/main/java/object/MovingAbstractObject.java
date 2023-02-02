package object;

import lombok.Getter;
import lombok.Setter;
import object.behaviour.movement.MovingBehaviour;

import java.util.UUID;

@Getter
@Setter
public class MovingAbstractObject extends AbstractObject {

    private MovingBehaviour behaviour;

    protected MovingAbstractObject(String name, MovingBehaviour movingBehaviour, UUID owner) {
        super(name, owner);
        behaviour = movingBehaviour;
    }

}

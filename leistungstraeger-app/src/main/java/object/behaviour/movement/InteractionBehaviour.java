package object.behaviour.movement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class InteractionBehaviour {

    private int interactionRange;

    // TODO what to return
    public abstract void interact();
}

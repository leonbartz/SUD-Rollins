package object.behaviour.movement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class VisibilityBehaviour {

    private int visibilityRange;

    // TODO what to return
    abstract void see();
}

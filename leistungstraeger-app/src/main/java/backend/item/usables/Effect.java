package backend.item.usables;

import backend.abstract_object.AbstractObject;
import backend.item.modifier.Modifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
public class Effect {

    @Getter
    private final int effectTime;

    @Getter
    private final boolean permanent;

    @Getter
    private final List<Modifier> modifiers;

    @Getter
    private final AbstractObject target;

    @Getter
    private final int healthPerTurn;

    public void use() {
        if (permanent || effectTime > 0) {

        }
    }
}

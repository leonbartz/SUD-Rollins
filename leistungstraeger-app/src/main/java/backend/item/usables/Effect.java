package backend.item.usables;

import backend.abstract_object.AbstractObject;
import backend.abstract_object.Combatable;
import backend.item.modifier.Modifier;
import backend.item.modifier.TimedModifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class Effect {

    @Getter
    private final int effectTime;

    @Getter
    private final boolean permanent;

    @Getter
    private final ArrayList<TimedModifier> modifiers;

    @Getter
    private final Combatable target;

    @Getter
    private final int healthPerTurn;

    public void use() {
        modifiers.forEach(effect -> target.applyEffect(effect));
    }
}

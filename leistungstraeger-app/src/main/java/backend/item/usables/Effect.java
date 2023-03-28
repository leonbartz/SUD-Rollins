package backend.item.usables;

import backend.abstract_object.Combatable;
import backend.item.modifier.TimedModifier;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record Effect(@Getter boolean permanent,
                     @Getter List<TimedModifier> modifiers,
                     @Getter Combatable target,
                     @Getter int healthPerTurn) {

    public void use() {
        //TODO: Hier muss eine Implementation für healthPerTurn her oder das wird als timedmodifier eingesetzt -> warum auch nicht es ist legit genau dafür da
        modifiers.forEach(target::applyEffect);
    }
}

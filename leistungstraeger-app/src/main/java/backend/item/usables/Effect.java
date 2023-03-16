package backend.item.usables;

import backend.abstract_object.Combatable;
import backend.item.modifier.ActiveEffectList;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import backend.item.modifier.TimedModifier;
import lombok.Builder;
import lombok.Getter;

@Builder
public record Effect(@Getter int effectTime,
                     @Getter boolean permanent,
                     @Getter ActiveEffectList modifiers,
                     @Getter Combatable target,
                     @Getter int healthPerTurn) {

    public void use() {
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            target.applyEffect(new TimedModifier(
                    new Modifier(identifier, modifiers.getValueForModifier(identifier)),
                    effectTime)
            );
        }
    }
}

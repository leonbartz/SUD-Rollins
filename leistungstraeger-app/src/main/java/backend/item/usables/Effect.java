package backend.item.usables;

import backend.abstract_object.Combatable;
import backend.item.modifier.TimedModifier;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record Effect(@Getter boolean permanent,
                     @Getter List<TimedModifier> modifiers,
                     @Getter Combatable target) {

    public void use() {
        modifiers.forEach(target::applyEffect);
    }
}

package backend.item;

import backend.item.modifier.ActiveEffectList;
import backend.item.modifier.ModifierIdentifier;
import backend.item.modifier.TimedModifier;
import lombok.Getter;

/**
 * AbstractItem with actual values, basically any item in game.
 * <p>
 * Separation due to items like Keys, which do not have any modifiers. These would be AbstractItems.
 */
public class AbstractModifyingItem extends AbstractItem {

    @Getter
    protected final ActiveEffectList activeModifiers = new ActiveEffectList();

    public AbstractModifyingItem(String name) {
        super(name);
    }

    public AbstractModifyingItem(final String name, final TimedModifier... modifiers) {
        super(name);
        //Add all modifiers
        activeModifiers.addAll(modifiers);
    }

    public double getModifierByIdentifier(final ModifierIdentifier identifier) {
        return activeModifiers.getValueForModifier(identifier);
    }
}

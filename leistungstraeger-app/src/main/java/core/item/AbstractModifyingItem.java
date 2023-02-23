package core.item;

import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;
import lombok.Getter;

import java.util.HashMap;

import static core.item.ItemUtils.createModifierHashMap;

/**
 * AbstractItem with actual values, basically any item in game.
 * <p>
 * Separation due to items like Keys, which do not have any modifiers. These would be AbstractItems.
 */
public class AbstractModifyingItem extends AbstractItem {

    @Getter
    protected final HashMap<ModifierIdentifier, Double> activeModifiers;

    public AbstractModifyingItem(String name) {
        super(name);
        activeModifiers = createModifierHashMap();
    }

    public AbstractModifyingItem(final String name, final Modifier... modifiers) {
        super(name);
        activeModifiers = createModifierHashMap();
        //Add all modifiers
        for (Modifier modifier : modifiers) activeModifiers.put(modifier.identifier(), modifier.value());
    }

    public double getModifierByIdentifier(final ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }
}

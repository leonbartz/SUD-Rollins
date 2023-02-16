package core.item;

import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

import static core.item.ItemUtils.createModifierHashMap;


public class AbstractItem {

    @Getter
    private final UUID id;

    @Getter
    private final String displayName;

    @Getter
    private final HashMap<ModifierIdentifier, Double> activeModifiers;

    public AbstractItem(final String name) {
        id = UUID.randomUUID();
        displayName = name;
        activeModifiers = createModifierHashMap();
    }

    public AbstractItem(final String name, Modifier... modifiers) {
        id = UUID.randomUUID();
        displayName = name;
        activeModifiers = createModifierHashMap();
        //Add all modifiers
        for (Modifier modifier : modifiers) activeModifiers.put(modifier.identifier(), modifier.value());
    }

    public double getModifierByIdentifier(ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }
}

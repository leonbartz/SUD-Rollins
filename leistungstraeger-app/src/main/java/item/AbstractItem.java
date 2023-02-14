package item;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class AbstractItem {

    @Getter
    private final UUID id;

    @Getter
    private final String displayName;

    //TODO auslagern in Klasse -> Code duplication in ItemStash
    @Getter
    private final HashMap<ModifierIdentifier, Double> activeModifiers;

    public AbstractItem(final String name) {
        id = UUID.randomUUID();
        displayName = name;
        activeModifiers = ItemUtils.createModifierHashMap();
    }

    public AbstractItem(final String name, Modifier... modifiers) {
        id = UUID.randomUUID();
        displayName = name;
        activeModifiers = ItemUtils.createModifierHashMap();
        //Add all modifiers
        for (Modifier modifier : modifiers) activeModifiers.put(modifier.identifier(), modifier.value());
    }

    public double getModifierByIdentifier(ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }
}

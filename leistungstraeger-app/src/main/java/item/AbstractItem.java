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
    private HashMap<ModifierIdentifier, Double> activeModifiers;

    public AbstractItem(final String name) {
        id = UUID.randomUUID();
        displayName = name;
        createEmptyHashMap();
    }

    public AbstractItem(final String name, Modifier... modifiers) {
        id = UUID.randomUUID();
        displayName = name;
        createEmptyHashMap();
        //Add all modifiers
        for (Modifier modifier : modifiers) activeModifiers.put(modifier.identifier(), modifier.value());
    }

    public double getModifierByIdentifier(ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }

    //TODO remove code duplication
    private void createEmptyHashMap() {
        final HashMap<ModifierIdentifier, Double> result = new HashMap<>();
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            result.put(identifier, 0.0);
        }
        activeModifiers = result;
    }
}

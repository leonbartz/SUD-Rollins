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
    }

    public AbstractItem(final String name,
                        final double health,
                        final double intelligence,
                        final double strength,
                        final double constitution,
                        final double wisdom,
                        final double skill,
                        final double vision,
                        final double armorClass) {
        id = UUID.randomUUID();
        displayName = name;

        activeModifiers = new HashMap<>();
        activeModifiers.put(ModifierIdentifier.HEALTH, health);
        activeModifiers.put(ModifierIdentifier.INTELLIGENCE, intelligence);
        activeModifiers.put(ModifierIdentifier.STRENGTH, strength);
        activeModifiers.put(ModifierIdentifier.CONSTITUTION, constitution);
        activeModifiers.put(ModifierIdentifier.WISDOM, wisdom);
        activeModifiers.put(ModifierIdentifier.SKILL, skill);
        activeModifiers.put(ModifierIdentifier.VISION, vision);
        activeModifiers.put(ModifierIdentifier.ARMORCLASS, armorClass);
    }

    public double getModifierByIdentifier(ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }

}

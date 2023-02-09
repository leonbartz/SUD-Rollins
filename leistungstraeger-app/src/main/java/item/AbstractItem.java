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
                        final double damage,
                        final double attack,
                        final double speed,
                        final double defence) {
        id = UUID.randomUUID();
        displayName = name;

        activeModifiers = new HashMap<>();
        activeModifiers.put(ModifierIdentifier.HEALTH, health);
        activeModifiers.put(ModifierIdentifier.ATTACK, attack);
        activeModifiers.put(ModifierIdentifier.DAMAGE, damage);
        activeModifiers.put(ModifierIdentifier.SPEED, speed);
        activeModifiers.put(ModifierIdentifier.DEFENCE, defence);
    }

    public double getModifierByIdentififer(ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }

}

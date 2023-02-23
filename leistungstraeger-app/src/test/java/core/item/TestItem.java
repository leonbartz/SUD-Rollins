package core.item;

import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;

/**
 * Class for testing purposes only, to be removed as soon as actual implementation exists.
 */
public class TestItem extends AbstractModifyingItem {

    @Deprecated
    public TestItem(String name) {
        super(name);
    }

    public TestItem(String name, double health, double damage, double attack, double speed, double defence) {
        super(name,
                new Modifier(ModifierIdentifier.HEALTH, health),
                new Modifier(ModifierIdentifier.DAMAGE, damage),
                new Modifier(ModifierIdentifier.ATTACK, attack),
                new Modifier(ModifierIdentifier.DEFENCE, defence),
                new Modifier(ModifierIdentifier.SPEED, speed));
    }
}

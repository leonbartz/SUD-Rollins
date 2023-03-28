package backend.item.usables.implementations;

import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import backend.item.modifier.TimedModifier;
import backend.item.usables.AbstractUsableItem;

import static backend.item.usables.ItemActivationType.SINGLE_USE;

public class DamagePotion extends AbstractUsableItem {

    public DamagePotion(final String name) {
        super(name, SINGLE_USE, 0, 10, false, new TimedModifier(
                new Modifier(ModifierIdentifier.DAMAGE, 200),
                3));
    }
}

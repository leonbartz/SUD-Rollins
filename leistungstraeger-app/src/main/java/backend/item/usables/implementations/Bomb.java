package backend.item.usables.implementations;

import backend.item.modifier.Modifier;
import backend.item.modifier.TimedModifier;
import backend.item.usables.AbstractUsableItem;

import static backend.item.usables.ItemActivationType.SINGLE_USE;

public class Bomb extends AbstractUsableItem {

    public Bomb(final String name,
                final int damage,
                final TimedModifier... modifiers) {
        super(name, SINGLE_USE, 0, damage * -1, false, modifiers);
    }
}

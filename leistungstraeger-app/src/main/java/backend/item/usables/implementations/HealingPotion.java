package backend.item.usables.implementations;

import backend.item.modifier.Modifier;
import backend.item.modifier.TimedModifier;
import backend.item.usables.AbstractUsableItem;

import static backend.item.usables.ItemActivationType.SINGLE_USE;

public class HealingPotion extends AbstractUsableItem {

    public HealingPotion(final String name,
                         final int healthPerTurn,
                         final TimedModifier... modifiers) {
        super(name, SINGLE_USE, 0,  healthPerTurn, false, modifiers);
    }
}

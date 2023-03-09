package backend.item.usables.implementations;

import backend.item.modifier.Modifier;
import backend.item.usables.AbstractUsableItem;

import static backend.item.usables.ItemActivationType.SINGLE_USE;

public class HealingPotion extends AbstractUsableItem {

    public HealingPotion(final String name,
                         final int activityTime,
                         final int healthPerTurn,
                         final Modifier... modifiers) {
        super(name, SINGLE_USE, 0, activityTime, healthPerTurn, false, modifiers);
    }
}

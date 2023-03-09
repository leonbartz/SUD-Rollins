package backend.item.usables.implementations;

import backend.item.modifier.Modifier;
import backend.item.usables.AbstractUsableItem;
import backend.item.usables.ItemActivationType;

public class Spell extends AbstractUsableItem {
    
    public Spell(final String name,
                 final ItemActivationType activationType,
                 final int cooldown,
                 final int healthPerTurn,
                 final int activityTime,
                 final boolean permanent,
                 final Modifier... modifiers) {
        super(name, activationType, cooldown, healthPerTurn, activityTime, permanent, modifiers);
    }
}

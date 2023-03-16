package backend.item.usables.implementations;

import backend.item.modifier.TimedModifier;
import backend.item.usables.AbstractUsableItem;
import backend.item.usables.ItemActivationType;

public class Spell extends AbstractUsableItem {

    public Spell(final String name,
                 final ItemActivationType activationType,
                 final int cooldown,
                 final int healthPerTurn,
                 final boolean permanent,
                 final TimedModifier... modifiers) {
        super(name, activationType, cooldown, healthPerTurn, permanent, modifiers);
    }
}

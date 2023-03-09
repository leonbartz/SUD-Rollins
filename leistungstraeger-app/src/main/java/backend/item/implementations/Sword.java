package backend.item.implementations;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;
import backend.item.modifier.TimedModifier;

public class Sword extends AbstractModifyingItem {

    public Sword(final String name) {
        super(name);
    }

    public Sword(final String name, final TimedModifier... modifiers) {
        super(name, modifiers);
    }

}

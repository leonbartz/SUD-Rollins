package backend.item.implementations;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;

public class Sword extends AbstractModifyingItem {

    public Sword(final String name) {
        super(name);
    }

    public Sword(final String name, final Modifier... modifiers) {
        super(name, modifiers);
    }

}

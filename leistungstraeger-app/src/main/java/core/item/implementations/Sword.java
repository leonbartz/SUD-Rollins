package core.item.implementations;

import core.item.AbstractItem;
import core.item.AbstractModifyingItem;
import core.item.modifier.Modifier;

public class Sword extends AbstractModifyingItem {

    public Sword(final String name) {
        super(name);
    }

    public Sword(final String name, final Modifier... modifiers) {
        super(name, modifiers);
    }

}

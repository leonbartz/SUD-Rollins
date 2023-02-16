package core.item.implementations;

import core.item.AbstractItem;
import core.item.modifier.Modifier;

public class Sword extends AbstractItem {

    public Sword(final String name) {
        super(name);
    }

    public Sword(final String name, final Modifier... modifiers) {
        super(name, modifiers);
    }

}

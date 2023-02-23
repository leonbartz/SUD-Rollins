package core.item.implementations;

import core.item.AbstractItem;
import core.item.AbstractModifyingItem;
import core.item.modifier.Modifier;

public class NoItem extends AbstractModifyingItem {

    public NoItem(final String name) {
        super(name);
    }

    public NoItem(final String name, final Modifier... modifiers) {
        super(name, modifiers);
    }

}

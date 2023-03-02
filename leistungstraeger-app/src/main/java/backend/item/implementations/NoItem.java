package backend.item.implementations;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;

public class NoItem extends AbstractModifyingItem {

    public NoItem(final String name) {
        super(name);
    }

    public NoItem(final String name, final Modifier... modifiers) {
        super(name, modifiers);
    }

}

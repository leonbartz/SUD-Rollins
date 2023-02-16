package core.item.implementations;

import core.item.AbstractItem;
import core.item.modifier.Modifier;

public class NoItem extends AbstractItem {

    public NoItem(String name) {
        super(name);
    }

    public NoItem(String name, Modifier... modifiers) {
        super(name, modifiers);
    }

}

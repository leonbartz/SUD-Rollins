package core.item.implementations;

import core.item.AbstractItem;
import core.item.modifier.Modifier;

public class Sword extends AbstractItem {

    public Sword(String name) {
        super(name);
    }

    public Sword(String name, Modifier... modifiers) {
        super(name, modifiers);
    }

}

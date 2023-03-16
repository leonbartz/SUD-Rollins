package backend.item.implementations.armors;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ChainMail extends AbstractModifyingItem {
    public ChainMail(String name) {
        super(name);
    }

    public ChainMail(String name, Modifier... modifiers) {
        super(name, modifiers);
    }
}

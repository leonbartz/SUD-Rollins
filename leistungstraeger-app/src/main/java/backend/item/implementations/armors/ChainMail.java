package backend.item.implementations.armors;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ChainMail extends AbstractModifyingItem {
    public ChainMail(String name) {
        super("ChainMail: "+name,
            new Modifier(ModifierIdentifier.ARMOR, 6)
        );
    }
}

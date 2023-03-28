package backend.item.implementations.armors;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Shield extends AbstractModifyingItem {
    public Shield(String name) {
        super("Shield: " + name,
                new Modifier(ModifierIdentifier.ARMOR, 2)
        );
    }
}
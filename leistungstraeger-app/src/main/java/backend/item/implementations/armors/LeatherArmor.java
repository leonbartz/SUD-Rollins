package backend.item.implementations.armors;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;

import java.util.ArrayList;


/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class LeatherArmor extends AbstractModifyingItem {
    public LeatherArmor(String name) {
        super("LeatherArmor: "+name,
                new Modifier(ModifierIdentifier.ARMOR, 1)
        );
    }
}

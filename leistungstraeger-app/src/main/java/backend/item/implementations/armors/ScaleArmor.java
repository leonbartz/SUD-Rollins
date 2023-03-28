package backend.item.implementations.armors;

import backend.item.AbstractModifyingItem;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ScaleArmor extends AbstractModifyingItem {
    public ScaleArmor(String name) {
        super(
                "ScaleArmor: "+name,
                new Modifier(ModifierIdentifier.ARMOR, 4)
        );
    }
}

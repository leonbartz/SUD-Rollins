package backend.item;

import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */

/**
 * AbstractItem with actual values, basically any item in game.
 * <p>
 * Separation due to items like Keys, which do not have any modifiers. These would be AbstractItems.
 */
public class AbstractModifyingItem extends AbstractItem {

    @Getter
    // These are permanent modifiers -> e. g. damage value for a sword
    private final ArrayList<Modifier> modifierList;

    public AbstractModifyingItem(String name) {
        super(name);
        modifierList = new ArrayList<>();
    }

    public AbstractModifyingItem(final String name, final Modifier... modifiers) {
        super(name);
        //Add all modifiers
        modifierList = new ArrayList<>(List.of(modifiers));
    }

    // Either returns value or 0 if no value selected
    public double getModifierByIdentifier(final ModifierIdentifier identifier) {
        return modifierList.stream()
                           .filter(modifier -> modifier
                                   .identifier()
                                   .equals(identifier))
                           .findFirst()
                           .map(Modifier::value)
                           .orElse(0.0);
    }
}

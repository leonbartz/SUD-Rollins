package core.item;

import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

import static core.item.ItemUtils.createModifierHashMap;


public class AbstractItem {

    @Getter
    protected final UUID id;

    @Getter
    protected final String displayName;

    public AbstractItem(final String name) {
        id = UUID.randomUUID();
        displayName = name;
    }

}

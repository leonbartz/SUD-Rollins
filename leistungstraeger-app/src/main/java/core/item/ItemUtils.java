package core.item;

import core.item.modifier.ModifierIdentifier;
import lombok.experimental.UtilityClass;

import java.util.HashMap;

@UtilityClass
public class ItemUtils {

    public static HashMap<ModifierIdentifier, Double> createModifierHashMap() {
        final HashMap<ModifierIdentifier, Double> result = new HashMap<>();
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            result.put(identifier, 0.0);
        }
        return result;
    }
}

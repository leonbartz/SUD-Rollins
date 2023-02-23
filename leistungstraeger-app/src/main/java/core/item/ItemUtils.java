package core.item;

import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

@UtilityClass
public class ItemUtils {

    public static HashMap<ModifierIdentifier, Double> createModifierHashMap() {
        final HashMap<ModifierIdentifier, Double> result = new HashMap<>();
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            result.put(identifier, 0.0);
        }
        return result;
    }

    public static List<Modifier> translateToModifier(final HashMap<ModifierIdentifier, Double> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) return List.of();

        ArrayList<Modifier> result = new ArrayList<>();
        for (ModifierIdentifier identifier : hashMap.keySet()) {
            result.add(new Modifier(identifier, hashMap.get(identifier)));
        }

        return result;
    }
}

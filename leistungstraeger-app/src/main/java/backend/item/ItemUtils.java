package backend.item;

import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@UtilityClass
public class ItemUtils {

    public static List<Modifier> translateToModifier(final HashMap<ModifierIdentifier, Double> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) return List.of();

        ArrayList<Modifier> result = new ArrayList<>();
        for (ModifierIdentifier identifier : hashMap.keySet()) {
            result.add(new Modifier(identifier, hashMap.get(identifier)));
        }

        return result;
    }
}

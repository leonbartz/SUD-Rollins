package backend.item;

import backend.item.modifier.ModifierIdentifier;

import java.text.MessageFormat;
import java.util.*;

import static backend.item.ItemUtils.createModifierHashMap;

/**
 * Repository Class for items. {@link AbstractModifyingItem}s are stored in an internal list and can  be accessed by the put-
 * and remove-Methods.
 * <p>
 * Any update to the inventory updates the modifiers, which this inventory provides. These can be obtained by using
 * their respective {@link ModifierIdentifier}.
 */
public class ItemStash {

    private ArrayList<AbstractModifyingItem> inventory;

    private HashMap<ModifierIdentifier, Double> activeModifiers;

    public ItemStash() {
        inventory = new ArrayList<>();
        updateValues();
    }

    public ItemStash(final AbstractModifyingItem... items) {
        inventory = new ArrayList<>(List.of(items));
        updateValues();
    }

    /**
     * Returns the {@link AbstractModifyingItem} if present or null.
     *
     * @param id - {@link UUID} of item.
     * @return {@link AbstractModifyingItem} if present or null.
     */
    public AbstractModifyingItem removeItem(final UUID id) {
        final Optional<AbstractModifyingItem> found = inventory.stream()
                                                      .filter(item -> item.getId() == id)
                                                      .findFirst();
        if (found.isEmpty()) throw new NullPointerException(MessageFormat.format(
                "No object with id {0} found in ItemStash.",
                id));
        final AbstractModifyingItem item = found.get();
        inventory.remove(item);
        updateValues();

        return item;
    }

    /**
     * Returns all {@link AbstractModifyingItem} as a List.
     *
     * @return List of {@link AbstractModifyingItem}.
     */
    public List<AbstractModifyingItem> removeAllItems() {
        final List<AbstractModifyingItem> returnedItems = inventory;
        inventory = new ArrayList<>();
        updateValues();

        return returnedItems;
    }

    /**
     * Places an {@link AbstractModifyingItem} in the inventory stash.
     *
     * @param item - {@link AbstractModifyingItem} to be added.
     */
    public void putItem(final AbstractModifyingItem item) {
        if (item != null) {
            inventory.add(item);
            updateValues();
        }
    }

    /**
     * Places multiple {@link AbstractModifyingItem} in the inventory stash.
     *
     * @param items - List of {@link AbstractModifyingItem} to be added.
     */
    public void putItems(final List<AbstractModifyingItem> items) {
        inventory.addAll(items);
        updateValues();
    }

    /**
     * Returns the calculated modification to stats for all currently possessed objects.
     *
     * @param identifier - {@link ModifierIdentifier}
     * @return - {@class double} value of overall modification
     */
    public double getValueForModifier(final ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }

    /**
     * Updates all modifiers in activeModifiers.
     */
    private void updateValues() {
        // Merge all stats into single HashMap
        final HashMap<ModifierIdentifier, Double> result = new HashMap<>();
        if (inventory.size() == 0) {
            activeModifiers = createModifierHashMap();
            return;
        }
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            for (AbstractModifyingItem item : inventory) {
                double activeValue = item.getModifierByIdentifier(identifier);
                if (result.containsKey(identifier)) {
                    result.put(identifier, result.get(identifier) + activeValue);
                } else {
                    result.put(identifier, activeValue);
                }
            }
        }
        activeModifiers = result;
    }
}

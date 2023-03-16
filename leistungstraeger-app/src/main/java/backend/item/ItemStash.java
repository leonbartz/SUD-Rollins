package backend.item;

import backend.item.modifier.ModifierIdentifier;

import java.text.MessageFormat;
import java.util.*;

import static backend.item.ItemUtils.createModifierHashMap;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */

/**
 * Repository Class for items. {@link AbstractItem}s are stored in an internal list and can  be accessed by the put-
 * and remove-Methods.
 * <p>
 * Any update to the inventory updates the modifiers, which this inventory provides. These can be obtained by using
 * their respective {@link ModifierIdentifier}.
 */
public class ItemStash {

    private ArrayList<AbstractItem> inventory;

    private HashMap<ModifierIdentifier, Double> activeModifiers;

    public ItemStash() {
        inventory = new ArrayList<>();
        updateValues();
    }

    public ItemStash(final AbstractItem... items) {
        inventory = new ArrayList<>(List.of(items));
        updateValues();
    }

    /**
     * Returns the {@link AbstractItem} if present or null.
     *
     * @param id - {@link UUID} of item.
     * @return {@link AbstractItem} if present or null.
     */
    public AbstractItem removeItem(final UUID id) {
        final Optional<AbstractItem> found = inventory.stream()
                                                      .filter(item -> item.getId() == id)
                                                      .findFirst();
        if (found.isEmpty()) throw new NullPointerException(MessageFormat.format(
                "No object with id {0} found in ItemStash.",
                id));
        final AbstractItem item = found.get();
        inventory.remove(item);
        updateValues();

        return item;
    }

    /**
     * Returns all {@link AbstractItem} as a List.
     *
     * @return List of {@link AbstractItem}.
     */
    public List<AbstractItem> removeAllItems() {
        final List<AbstractItem> returnedItems = inventory;
        inventory = new ArrayList<>();
        updateValues();

        return returnedItems;
    }

    /**
     * Places an {@link AbstractItem} in the inventory stash.
     *
     * @param item - {@link AbstractItem} to be added.
     */
    public void putItem(final AbstractItem item) {
        if (item != null) {
            inventory.add(item);
            updateValues();
        }
    }

    /**
     * Places multiple {@link AbstractItem} in the inventory stash.
     *
     * @param items - List of {@link AbstractItem} to be added.
     */
    public void putItems(final List<AbstractItem> items) {
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
            for (AbstractItem item : inventory) {
                if (item instanceof AbstractModifyingItem modifyingItem) {
                    double activeValue = modifyingItem.getModifierByIdentifier(identifier);
                    if (result.containsKey(identifier)) {
                        result.put(identifier, result.get(identifier) + activeValue);
                    } else {
                        result.put(identifier, activeValue);
                    }
                }
            }
        }
        activeModifiers = result;
    }
}

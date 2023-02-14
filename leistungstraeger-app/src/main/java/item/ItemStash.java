package item;

import java.text.MessageFormat;
import java.util.*;

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
        updateModifiers();
    }

    public ItemStash(AbstractItem... items) {
        inventory = new ArrayList<>(List.of(items));
        updateModifiers();
    }

    ;

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
        updateModifiers();

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
        updateModifiers();

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
            updateModifiers();
        }
    }

    /**
     * Places multiple {@link AbstractItem} in the inventory stash.
     *
     * @param items - List of {@link AbstractItem} to be added.
     */
    public void putItems(List<AbstractItem> items) {
        inventory.addAll(items);
        updateModifiers();
    }

    /**
     * Returns the calculated modification to stats for all currently possessed objects.
     *
     * @param identifier - {@class ModifierIdentifier}
     * @return - {@class Double} value of overall modification
     */
    public double getModifierByIdentifier(ModifierIdentifier identifier) {
        return activeModifiers.get(identifier);
    }

    /**
     * Updates all modifiers in activeModifiers.
     */
    private void updateModifiers() {
        // Merge all stats into single HashMap
        final HashMap<ModifierIdentifier, Double> result = new HashMap<>();
        if (inventory.size() == 0) {
            createEmptyInventory();
            return;
        }
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            for (AbstractItem item : inventory) {
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

    private void createEmptyInventory() {
        final HashMap<ModifierIdentifier, Double> result = new HashMap<>();
        for (ModifierIdentifier identifier : ModifierIdentifier.values()) {
            result.put(identifier, 0.0);
        }
        activeModifiers = result;
    }
}

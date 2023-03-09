package backend.item;

import backend.item.modifier.ModifierIdentifier;
import backend.item.modifier.TimedModifier;
import backend.item.usables.AbstractUsableItem;

import java.text.MessageFormat;
import java.util.*;

import static backend.item.usables.ItemActivationType.SINGLE_USE;

/**
 * Repository Class for items. {@link AbstractItem}s are stored in an internal list and can  be accessed by the put-
 * and remove-Methods.
 * <p>
 * Any update to the inventory updates the modifiers, which this inventory provides. These can be obtained by using
 * their respective {@link ModifierIdentifier}.
 */
public class ItemStash {

    private ArrayList<AbstractItem> inventory;

    private ArrayList<TimedModifier> activeModifiers;

    public ItemStash() {
        inventory = new ArrayList<>();
        updateInventory();
    }

    public ItemStash(final AbstractItem... items) {
        inventory = new ArrayList<>(List.of(items));
        updateInventory();
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
        updateInventory();

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
        updateInventory();

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
            updateInventory();
        }
    }

    /**
     * Places multiple {@link AbstractItem} in the inventory stash.
     *
     * @param items - List of {@link AbstractItem} to be added.
     */
    public void putItems(final List<AbstractItem> items) {
        inventory.addAll(items);
        updateInventory();
    }

    /**
     * Returns the calculated modification to stats for all currently possessed objects.
     *
     * @param identifier - {@link ModifierIdentifier}
     * @return - {@link double} value of overall modification
     */
    public double getValueForModifier(final ModifierIdentifier identifier) {
        final List<TimedModifier> result = activeModifiers.stream()
                                                    .filter(timedModifier -> timedModifier
                                                            .modifier()
                                                            .identifier()
                                                            .equals(identifier))
                                                    .filter(timedModifier -> timedModifier.turns() > 0)
                                                    .toList();

        return result.stream()
                     .map(timedModifier -> timedModifier.modifier().value())
                     .reduce(Double::sum)
                     .orElse(0.0);
    }

    /**
     * Cleanup and updates which happen every turn.
     */
    private void updateInventory() {
        removeUselessItems();
        updateValues();
    }

    /**
     * Updates all modifiers in activeModifiers.
     */
    private void updateValues() {
        removeUselessItems();

        // Remove all modifiers where time is up
        activeModifiers.removeAll(activeModifiers.stream()
                                                 .filter(modifier -> modifier.turns() == 0)
                                                 .toList());
    }

    private void removeUselessItems() {
        final List<AbstractItem> uselessItems = findUselessItems();
        uselessItems.forEach(item -> removeItem(item.id));
    }

    /**
     * Filters out all items with no usages remaining
     */
    private List<AbstractItem> findUselessItems() {
        return inventory.stream()
                        .filter(item -> item instanceof AbstractUsableItem)
                        .filter(item -> ((AbstractUsableItem) item)
                                .getActivationType() == SINGLE_USE)
                        .filter(item -> ((AbstractUsableItem) item).isUsedUp())
                        .toList();
    }
}

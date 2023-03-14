package backend.item;

import backend.item.modifier.ModifierIdentifier;
import backend.item.usables.AbstractUsableItem;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static backend.item.usables.ItemActivationType.SINGLE_USE;

/**
 * Repository Class for items. {@link AbstractItem}s are stored in an internal list and can  be accessed by the put-
 * and remove-Methods.
 * <p>
 * Any update to the inventory updates the modifiers, which this inventory provides. These can be obtained by using
 * their respective {@link ModifierIdentifier}.
 */
public class ItemStash {

    // List of items in this stash
    private ArrayList<AbstractItem> inventory;

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
     * Cleanup which happens every turn.
     */
    private void updateInventory() {
        removeUselessItems();
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

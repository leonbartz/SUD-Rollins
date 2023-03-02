package backend.item.usables;

import backend.item.AbstractModifyingItem;
import backend.item.ItemStash;
import backend.item.modifier.Modifier;
import lombok.Getter;

import java.util.List;

import static backend.item.usables.ItemActivationType.COUNT_ROUNDS;
import static backend.item.usables.ItemActivationType.ON_REACTIVATION;


/**
 * Item which can be used to generate an effect on the user. Can either be reactivated by healing or resting,
 * reactivates after a set amount of turns or is used up and marked as such. (Removal of empty items is handled in
 * the {@link ItemStash}.
 */
public class AbstractUsableItem extends AbstractModifyingItem {

    // item can only be used if not used up
    @Getter
    private boolean usedUp = false;

    @Getter
    private final ItemActivationType activationType;

    @Getter
    private final ItemActivityType activityType;

    // Whether effect is currently active
    @Getter
    private boolean isActive = true;

    // The amount of turns after which the item activates again
    private final int cooldown;

    // How many turns the item can stay active
    private final int activityTime;

    private int activityCounter = 0;

    // Counts inactive rounds if ItemActivationType is COUNT_ROUNDS
    private int cooldownCounter = 0;

    public AbstractUsableItem(final String name,
                              final ItemActivationType activationType,
                              final ItemActivityType activityType,
                              final int cooldown,
                              final int activityTime,
                              final Modifier... modifiers) {
        super(name, modifiers);
        this.activationType = activationType;
        this.activityType = activityType;
        this.cooldown = cooldown;
        this.activityTime = activityTime;
    }

    /**
     * Processes a turn for this item. Can be used for every item, counts internally how many turns to activation
     * remain or reactivates, if item has necessary activation type.
     */
    public void countTurn() {
        // If item is permanently used up, don't count
        if (!isUsedUp()) return;

        if (isActive() && activityType.equals(ItemActivityType.DEACTIVATES_AFTER_COUNT)) {
            // Item is active and might deactivate
            activityCounter++;
            if (activityCounter == activityTime) {
                isActive = false;
                activityCounter = 0;
            }
        } else if (!isActive() && activationType.equals(COUNT_ROUNDS)) {
            // Item is inactive and might be usable again
            cooldownCounter++;
            if (cooldownCounter == cooldown) {
                usedUp = false;
                cooldownCounter = 0;
            }
        }
    }

    /**
     * Attempt to reactivate item. If necessary type is set, item is reactivated.
     */
    public void reactivate() {
        if (getActivationType() == ON_REACTIVATION) isActive = true;
    }

    /**
     * Attempt to use this item. Deactivates item.
     *
     * @return - if active, list of effects, otherwise empty list.
     */
    public List<Modifier> use() {
        if (!usedUp) {
            usedUp = true;
            if (!isActive) isActive = true;
            return ItemUtils.translateToModifier(getActiveModifiers());
        }
        return List.of();
    }
}

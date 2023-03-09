package backend.item.usables;

import backend.abstract_object.AbstractObject;
import backend.item.AbstractModifyingItem;
import backend.item.ItemStash;
import backend.item.ItemUtils;
import backend.item.modifier.Modifier;
import lombok.Getter;

import static backend.item.usables.ItemActivationType.COUNT_ROUNDS;


/**
 * Item which can be used to generate an effect on the user. Can either be reactivated by healing or resting,
 * reactivates after a set amount of turns or is used up and marked as such. (Removal of empty items is handled in
 * the {@link ItemStash}.
 */
public abstract class AbstractUsableItem extends AbstractModifyingItem {

    // item can only be used if not used up
    @Getter
    private boolean usedUp = false;

    @Getter
    private final ItemActivationType activationType;

    // The amount of turns after which the item activates again
    private final int cooldown;

    // How many turns the item can stay active
    private final int activityTime;

    // Whether item has permanent effect
    private final boolean permanent;

    // How much health the item adds/removes per
    private final int healthPerTurn;

    // Counts inactive rounds if ItemActivationType is COUNT_ROUNDS
    private int cooldownCounter = 0;

    public AbstractUsableItem(final String name,
                              final ItemActivationType activationType,
                              final int cooldown,
                              final int activityTime,
                              final int healthPerTurn,
                              final boolean permanent,
                              final Modifier... modifiers) {
        super(name, modifiers);
        this.activationType = activationType;
        this.cooldown = cooldown;
        this.activityTime = activityTime;
        this.permanent = permanent;
        this.healthPerTurn = healthPerTurn;
    }

    /**
     * Processes a turn for this item. Can be used for every item, counts internally how many turns to activation
     * remain or reactivates, if item has necessary activation type.
     */
    public void update() {
        // If item is permanently used up, don't count
        if (isUsedUp() && activationType.equals(COUNT_ROUNDS)) {
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
        if (getActivationType().equals(ItemActivationType.ON_REACTIVATION)) usedUp = false;
    }

    /**
     * Attempt to use this item. Deactivates item.
     *
     * @return - if active, list of effects, otherwise empty list.
     */
    public Effect use(AbstractObject target) {
        usedUp = true;
        if(getActivationType() == COUNT_ROUNDS) cooldownCounter = 0;
        return Effect.builder()
                .effectTime(activityTime)
                .healthPerTurn(healthPerTurn)
                .permanent(permanent)
                .target(target) //TODO ist das bad?
                .modifiers(ItemUtils.translateToModifier(getActiveModifiers()))
                .build();
    }
}
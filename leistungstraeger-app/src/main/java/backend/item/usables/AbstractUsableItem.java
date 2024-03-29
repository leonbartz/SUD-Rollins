package backend.item.usables;

import backend.abstract_object.Combatable;
import backend.item.AbstractItem;
import backend.item.ItemStash;
import backend.item.modifier.TimedModifier;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static backend.item.usables.ItemActivationType.COUNT_ROUNDS;


/**
 * Item which can be used to generate an effect on the user. Can either be reactivated by healing or resting,
 * reactivates after a set amount of turns or is used up and marked as such. (Removal of empty items is handled in
 * the {@link ItemStash}.
 */
public abstract class AbstractUsableItem extends AbstractItem {

    // item can only be used if not used up
    @Getter
    private boolean usedUp = false;

    @Getter
    private final ItemActivationType activationType;

    // The amount of turns after which the item activates again
    private final int cooldown;

    // Whether item has permanent effect
    private final boolean permanent;

    // Counts inactive rounds if ItemActivationType is COUNT_ROUNDS
    private int cooldownCounter = 0;

    // These values are generated as effect when this item is used
    //This is NOT an ActiveEffectsList, because these effects are not yet ACTIVE
    private final ArrayList<TimedModifier> modifiers;

    public AbstractUsableItem(final String name,
                              final ItemActivationType activationType,
                              final int cooldown,
                              final boolean permanent,
                              final TimedModifier... modifiers) {
        super(name);
        this.activationType = activationType;
        this.cooldown = cooldown;
        this.permanent = permanent;
        this.modifiers = new ArrayList<>(List.of(modifiers));
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
    public Effect use(Combatable target) {
        usedUp = true;
        if (getActivationType() == COUNT_ROUNDS) cooldownCounter = 0;
        return Effect.builder()
                     .permanent(permanent)
                     .target(target)
                     .modifiers(modifiers)
                     .build();
    }
}

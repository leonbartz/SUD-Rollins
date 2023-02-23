package core.item.usables;

import core.item.AbstractModifyingItem;
import core.item.ItemStash;
import core.item.ItemUtils;
import core.item.modifier.Modifier;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static core.item.usables.ItemActivationType.*;

/**
 * Item which can be used to generate an effect on the user. Can either be reactivated by healing or resting,
 * reactivates after a set amount of turns or is used up and marked as such. (Removal of empty items is handled in
 * the {@link ItemStash}.
 */
public class AbstractUsableItem extends AbstractModifyingItem {

    @Getter
    private boolean isEmpty = false;

    @Getter
    private final ItemActivationType activationType;

    @Getter
    @Setter
    private boolean isActive = true;

    // The amount of turns after which the item activates again
    private final int resetCounter;

    // If inactive, counts how many inactive turns have passed
    private int inactiveRounds = 0;

    public AbstractUsableItem(final String name,
                              final ItemActivationType type,
                              final int resetCounter,
                              final Modifier... modifiers) {
        super(name, modifiers);
        this.activationType = type;
        this.resetCounter = resetCounter;
    }

    public AbstractUsableItem(final String name,
                              final ItemActivationType type,
                              final Modifier... modifiers) {
        super(name, modifiers);
        this.activationType = type;
        this.resetCounter = 0;
    }

    /**
     * Processes a turn for this item. Can be used for every item, counts internally how many turns to activation
     * remain or reactivates, if item has necessary activation type.
     */
    public void countTurn() {
        if (isActive()) return;

        // If inactive, either count up or reactivate item
        if(activationType == COUNT_ROUNDS) {
            if (inactiveRounds == resetCounter) {
                inactiveRounds = 0;
                setActive(true);
            } else {
                inactiveRounds++;
            }
        }
    }

    /**
     * Attempt to reactivate item. If necessary type is set, item is reactivated.
     */
    public void reactivate() {
        if(getActivationType() == ON_HEAL_OR_REST) setActive(true);
    }

    /**
     * Attempt to use this item. Deactivates item.
     *
     * @return - if active, list of effects, otherwise empty list.
     */
    public List<Modifier> use() {
        if (isActive() && !isEmpty()) {
            if(activationType == SINGLE_USE) isEmpty = true;
            setActive(false);
            return ItemUtils.translateToModifier(getActiveModifiers());
        }

        return List.of();
    }
}

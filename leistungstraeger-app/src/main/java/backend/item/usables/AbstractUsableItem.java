package backend.item.usables;

import backend.item.AbstractModifyingItem;
import backend.item.ItemStash;
import backend.item.ItemUtils;
import backend.item.modifier.Modifier;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static backend.item.usables.ItemActivationType.*;


/**
 * Item which can be used to generate an effect on the user. Can either be reactivated by healing or resting,
 * reactivates after a set amount of turns or is used up and marked as such. (Removal of empty items is handled in
 * the {@link ItemStash}.
 */
public class AbstractUsableItem extends AbstractModifyingItem {

    // (non)permanent item deactivation -> cannot be used while empty
    @Getter
    private boolean isEmpty = false;

    @Getter
    private final ItemActivationType activationType;

    // TODO remaining usages implementieren -> ItemActivityType

    // Whether effect is currently active
    // TODO effekt aktiv für x runden -> danach empty
    // TODO das muss überarbeitet werden, oder der kommentar muss angepasst werden
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
        if (activationType == COUNT_ROUNDS) {
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
        if (getActivationType() == ON_REACTIVATION) setActive(true);
    }

    /**
     * Attempt to use this item. Deactivates item.
     *
     * @return - if active, list of effects, otherwise empty list.
     */
    public List<Modifier> use() {
        if (isActive() && !isEmpty()) {
            if (activationType == SINGLE_USE) isEmpty = true;
            setActive(false);
            return ItemUtils.translateToModifier(getActiveModifiers());
        }

        return List.of();
    }
}

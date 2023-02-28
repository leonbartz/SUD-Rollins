package backend.item.implementations;

import backend.item.modifier.Modifier;
import backend.item.usables.AbstractUsableItem;

import static backend.item.usables.ItemActivationType.COUNT_ROUNDS;

public class Spell extends AbstractUsableItem {

    /**
     * Generic spell, resets after a fixed amount of rounds.
     * @param name - name of the spell
     * @param resetCounter - number of
     * @param modifiers
     */
    public Spell(String name, int resetCounter, Modifier... modifiers) {
        super(name, COUNT_ROUNDS, resetCounter, modifiers);
    }
}

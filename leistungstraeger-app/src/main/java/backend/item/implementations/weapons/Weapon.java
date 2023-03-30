package backend.item.implementations.weapons;

import backend.character.stats.Stats;
import backend.item.AbstractItem;
import helpers.dice.Dice;
import lombok.Getter;

public abstract class Weapon extends AbstractItem {

    @Getter
    public final Stats modifier;
    public Weapon(String name, Stats modifier) {
        super(name);
        this.modifier = modifier;
    }

    public abstract int getDamage(int statValue);

    public int getToHit(int statValue) {
        return Dice.roll(1, 20, getModifyingValueFromAttribute(statValue));
    }

    protected int getModifyingValueFromAttribute(int statValue) {
        return (statValue - 10) / 2;
    }
}

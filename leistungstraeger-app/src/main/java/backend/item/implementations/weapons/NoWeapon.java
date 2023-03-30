package backend.item.implementations.weapons;

import backend.character.stats.Stats;
import helpers.dice.Dice;

public class NoWeapon extends Weapon{
    public NoWeapon() {
        super("No Weapon", Stats.STRENGTH);
    }

    @Override
    public int getDamage(int statValue) {
        return Dice.roll(1,1, getModifyingValueFromAttribute(statValue));
    }
}
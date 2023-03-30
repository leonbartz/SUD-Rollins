package backend.item.implementations.weapons;

import backend.character.stats.Stats;
import helpers.dice.Dice;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Dagger extends Weapon{
    public Dagger() {
        super("Dagger", Stats.DEXTERITY);
    }

    @Override
    public int getDamage(int statValue) {
        return Dice.roll(1,4, getModifyingValueFromAttribute(statValue));
    }
}

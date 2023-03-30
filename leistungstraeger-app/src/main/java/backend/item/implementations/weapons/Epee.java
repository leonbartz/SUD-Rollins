package backend.item.implementations.weapons;

import backend.character.stats.Stats;
import helpers.dice.Dice;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Epee extends Weapon{
    public Epee() {
        super("Epee", Stats.DEXTERITY);
    }

    @Override
    public int getDamage(int statValue) {
        return Dice.roll(1,8, getModifyingValueFromAttribute(statValue));
    }
}

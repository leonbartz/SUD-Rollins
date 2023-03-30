package backend.item.implementations.weapons;

import backend.character.stats.Stats;
import helpers.dice.Dice;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Sword extends Weapon{
    public Sword() {
        super("Sword", Stats.DEXTERITY);
    }

    @Override
    public int getDamage(int statValue) {
        return Dice.roll(1,8, getModifyingValueFromAttribute(statValue));
    }
}

package backend.item.implementations.weapons;

import backend.character.stats.Stats;
import helpers.dice.Dice;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class BattleAxe extends Weapon {
    public BattleAxe() {
        super("BattleAxe", Stats.STRENGTH);
    }

    @Override
    public int getDamage(int statValue) {
        return Dice.roll(1,6, getModifyingValueFromAttribute(statValue));
    }
}
package application.core.character;

import application.core.character.races.*;
import application.core.character.types.*;

public class Character {
    protected Race race;
    protected Type type;
    protected int intelligence;
    protected int strength;
    protected int constitution;
    protected int wisdom;
    protected int skill;
    protected int basicHP;
    protected int maxHP;
    protected int xp;
    protected int level;
    protected Direction direction;
    protected String name;
    protected int vision;



    public enum Direction{
        NORTH, EAST, SOUTH, WEST
    }
    public enum Type{
        Mage, Thief, Warrior
    }
}

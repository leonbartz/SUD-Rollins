package application.core.character.races;

public class Dwarf implements Race{
    protected int intelligence = 0;
    protected int strength = 2;
    protected int constitution = 2;
    protected int wisdom = 1;
    protected int skill = 0;

    @Override
    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getConstitution() {
        return this.constitution;
    }

    @Override
    public int getWisdom() {
        return this.wisdom;
    }

    @Override
    public int getSkill() {
        return this.skill;
    }
}

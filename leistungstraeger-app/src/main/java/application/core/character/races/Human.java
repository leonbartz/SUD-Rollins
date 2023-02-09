package application.core.character.races;

public class Human implements Race{
    protected int intelligence = 1;
    protected int strength = 1;
    protected int constitution = 1;
    protected int wisdom = 1;
    protected int skill = 1;
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

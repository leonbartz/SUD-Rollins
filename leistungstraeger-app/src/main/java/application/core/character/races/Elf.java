package application.core.character.races;

public class Elf implements Race{
    protected int intelligence = 2;
    protected int strength = 0;
    protected int constitution = 0;
    protected int wisdom = 1;
    protected int skill = 2;
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

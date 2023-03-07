package backend.character.races;

public class Human implements CharacterRace {
    protected int intelligenceModRace = 1;
    protected int strengthModRace = 1;
    protected int constitutionModRace = 1;
    protected int wisdomModRace = 1;
    protected int skillModRace = 1;

    @Override
    public String getRaceName() {
        return "Human";
    }

    @Override
    public int getIntelligence() {
        return this.intelligenceModRace;
    }

    @Override
    public int getStrength() {
        return this.strengthModRace;
    }

    @Override
    public int getConstitution() {
        return this.constitutionModRace;
    }

    @Override
    public int getWisdom() {
        return this.wisdomModRace;
    }

    @Override
    public int getSkill() {
        return this.skillModRace;
    }
}

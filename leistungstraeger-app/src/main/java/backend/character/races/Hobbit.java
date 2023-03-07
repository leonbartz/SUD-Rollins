package backend.character.races;

public class Hobbit implements CharacterRace {
    protected int intelligenceModRace = 0;
    protected int strengthModRace = 0;
    protected int constitutionModRace = 1;
    protected int wisdomModRace = 2;
    protected int skillModRace = 2;

    @Override
    public String getRaceName() {
        return "Hobbit";
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

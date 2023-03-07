package backend.character.races;

public class Elf implements CharacterRace {
    protected int intelligenceModRace = 2;
    protected int strengthModRace = 0;
    protected int constitutionModRace = 0;
    protected int wisdomModRace = 1;
    protected int skillModRace = 2;

    @Override
    public String getRaceName() {
        return "Elf";
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

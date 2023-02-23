package application.core.character.races;

public class Dwarf implements Race{
    protected int intelligenceModRace = 0;
    protected int strengthModRace = 2;
    protected int constitutionModRace = 2;
    protected int wisdomModRace = 1;
    protected int skillModRace = 0;

    @Override
    public String getRaceName() {
        return "Dwarf";
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

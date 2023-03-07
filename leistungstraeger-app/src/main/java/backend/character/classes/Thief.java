package backend.character.classes;

public class Thief extends CharacterClass {

    public Thief() {
        super();
        this.baseHpModType = 8;
        this.armorClassModType = 10;
    }

    @Override
    public String getTypeName() {
        return "Thief";
    }

    @Override
    public int getBasicHp() {
        return this.baseHpModType;
    }

    @Override
    public int getArmorClass() {
        return this.armorClassModType;
    }

    @Override
    public void attack() {

    }

    @Override
    public void defend() {

    }
}

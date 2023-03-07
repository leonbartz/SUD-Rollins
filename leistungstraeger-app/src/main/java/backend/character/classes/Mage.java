package backend.character.classes;

public class Mage extends CharacterClass {

    public Mage() {
        super();
        this.baseHpModType = 8;
        this.armorClassModType = 10;
    }

    @Override
    public String getTypeName() {
        return "Mage";
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

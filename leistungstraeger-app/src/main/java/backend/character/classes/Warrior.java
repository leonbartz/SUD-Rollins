package backend.character.classes;

public class Warrior extends CharacterClass {

    public Warrior() {
        super();
        this.baseHpModType = 10;
        this.armorClassModType = 13;
    }

    @Override
    public String getTypeName() {
        return "Warrior";
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

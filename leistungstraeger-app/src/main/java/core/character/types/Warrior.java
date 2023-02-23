package core.character.types;

public class Warrior extends Type {

    protected int baseHpModType = 10;
    protected int armorClassModType = 13;

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

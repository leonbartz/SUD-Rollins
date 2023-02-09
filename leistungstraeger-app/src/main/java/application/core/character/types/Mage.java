package application.core.character.types;

public class Mage extends Type {
    protected int baseHpModType = 6;
    protected int armorClassModType = 10;

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

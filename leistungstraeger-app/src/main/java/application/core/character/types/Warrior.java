package application.core.character.types;

public class Warrior extends Type {

    protected int baseHpMod = 10;
    protected int armorClassMod = 13;

    @Override
    public int getBaseHpMod() {
        return this.baseHpMod;
    }

    @Override
    public int getArmorClassMod() {
        return this.armorClassMod;
    }

    @Override
    public void attack() {

    }

    @Override
    public void defend() {

    }
}

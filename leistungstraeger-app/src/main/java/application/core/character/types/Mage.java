package application.core.character.types;

public class Mage extends Type {
    protected int baseHpMod = 6;
    protected int armorClassMod = 10;

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

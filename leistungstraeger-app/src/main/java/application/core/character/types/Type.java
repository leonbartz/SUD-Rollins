package application.core.character.types;

public abstract class Type {
    protected int baseHpMod;
    protected int armorClassMod;
    public abstract int getBaseHpMod();
    public abstract int getArmorClassMod();
    public abstract void attack();
    public abstract void defend();

}

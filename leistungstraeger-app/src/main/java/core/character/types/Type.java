package core.character.types;

public abstract class Type {
    protected int baseHpModType;
    protected int armorClassModType;
    public abstract String getTypeName();
    public abstract int getBasicHp();
    public abstract int getArmorClass();
    public abstract void attack();
    public abstract void defend();

}

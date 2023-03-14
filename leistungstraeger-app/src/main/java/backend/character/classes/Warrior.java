package backend.character.classes;

public class Warrior extends CharacterClass {

    public Warrior() {
        super();
        this.basicHp = 10;
        this.armorClass = 13;
    }

    @Override
    public String getTypeName() {
        return "Warrior";
    }

    @Override
    public void attack() {

    }

    @Override
    public void defend() {

    }
}

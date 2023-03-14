package backend.character.classes;

public class Mage extends CharacterClass {

    public Mage() {
        super();
        this.basicHp = 8;
        this.armorClass = 10;
    }

    @Override
    public String getTypeName() {
        return "Mage";
    }
}

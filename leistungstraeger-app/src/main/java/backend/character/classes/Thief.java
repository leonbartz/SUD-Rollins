package backend.character.classes;

public class Thief extends CharacterClass {

    public Thief() {
        super();
        this.basicHp = 8;
        this.armorClass = 10;
    }

    @Override
    public String getTypeName() {
        return "Thief";
    }
}

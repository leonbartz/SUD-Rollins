package backend.character.classes;

import lombok.Getter;
import lombok.Setter;

public abstract class CharacterClass {
    @Getter
    protected int basicHp;
    @Getter
    protected int armorClass;
    @Getter
    private int xp;
    @Getter
    private int level;
    private int xpToNextLvl;

    public CharacterClass() {
        this.level = 1;
        this.xpToNextLvl = 10;
        this.xp = 0;
    }
    public abstract String getTypeName();

    public void addXp(int points){
        if (points > 0){
            this.xp += points;
            handleLvlUp();
        }
    }
    private void handleLvlUp(){
        if (xp >= xpToNextLvl && level < 20){
            this.level += 1;
            xpToNextLvl += level*15;
        }
    }
}

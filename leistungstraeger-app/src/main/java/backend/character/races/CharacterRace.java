package backend.character.races;

import lombok.Getter;

public abstract class CharacterRace {
    @Getter
    protected int intelligence;
    @Getter
    protected int strength;
    @Getter
    protected int constitution;
    @Getter
    protected int wisdom;
    @Getter
    protected int skill;

    public CharacterRace(int intelligence, int strength, int constitution, int wisdom, int skill) {
        this.intelligence = intelligence;
        this.strength = strength;
        this.constitution = constitution;
        this.wisdom = wisdom;
        this.skill = skill;
    }
}

package application.core.character;

import application.core.character.races.*;
import application.core.character.types.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public class Character {
    protected Race race;
    protected Type type;
    protected int intelligence;
    protected int strength;
    protected int constitution;
    protected int wisdom;
    protected int skill;
    @Setter
    @Getter
    protected int basicHP;
    @Setter
    @Getter
    protected int currentHP;
    @Setter
    @Getter
    protected int maxHP;
    @Setter
    @Getter
    protected int xp;
    @Setter
    @Getter
    protected int level;
    @Setter
    @Getter
    protected String name;
    @Setter
    @Getter
    protected int vision;
    @Setter
    @Getter
    protected int armorClass;
    @Setter
    @Getter
    protected int goldStat;
    @Setter
    @Getter
    protected boolean special;

    public Character(String name, Type type, Race race){
        setName(name);
        setLevel(1);
        setXp(0);
        generateAttributes();
        this.type = type;
        type.getBasicHp();
        type.getArmorClass();
        this.race = race;
        constitution += race.getConstitution();
        intelligence += race.getIntelligence();
        skill += race.getSkill();
        strength += race.getStrength();
        wisdom += race.getWisdom();
        calculateMaxHP();
        setCurrentHP(maxHP);
        setSpecial(true);
    }

    private void generateAttributes() {
        Random random = new Random();
        strength = random.nextInt(16) + 3;
        intelligence = random.nextInt(16) + 3;
        constitution = random.nextInt(16) + 3;
        wisdom = random.nextInt(16) + 3;
        skill = random.nextInt(16) + 3;

        int sum = strength + intelligence + constitution + wisdom + skill;
        while (sum < 60) {
            int lowestAttribute = Math.min(Math.min(Math.min(Math.min(strength, intelligence), constitution), wisdom), skill);
            int attributeToReplace = random.nextInt(5);
            if (attributeToReplace == 0) {
                strength = random.nextInt(16) + 3;
            } else if (attributeToReplace == 1) {
                intelligence = random.nextInt(16) + 3;
            } else if (attributeToReplace == 2) {
                constitution = random.nextInt(16) + 3;
            } else if (attributeToReplace == 3) {
                wisdom = random.nextInt(16) + 3;
            } else {
                skill = random.nextInt(16) + 3;
            }
            sum = strength + intelligence + constitution + wisdom + skill;
        }
    }

    private void calculateMaxHP() {
        setMaxHP(basicHP + (this.constitution - 10) / 2);
    }
    public void displayCharacterInformation() {
        System.out.println("Character name: " + name);
        System.out.println("Class: " + type.getTypeName());
        System.out.println("Race: " + race.getRaceName());
        System.out.println("Hit Points: " + currentHP);
        System.out.println("Strength: " + strength);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Constitution: " + constitution);
        System.out.println("Wisdom: " + wisdom);
        System.out.println("Skill: " + skill);
        System.out.println("Armor Class: " + armorClass);
    }
}

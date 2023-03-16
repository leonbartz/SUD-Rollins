package backend.character;

import backend.character.classes.CharacterClass;
import backend.character.races.CharacterRace;
import backend.abstract_object.interaction.Interactable;
import backend.network.client.Client;
import backend.item.AbstractModifyingItem;
import backend.item.implementations.NoItem;
import backend.item.modifier.ModifierIdentifier;
import backend.abstract_object.AbstractObject;
import backend.abstract_object.Combatable;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.game_map.Room;
import helpers.command.AttackCommand;
import helpers.command.GameCommand;
import helpers.command.MoveCommand;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class GameCharacter extends Combatable {

    @Getter
    private final Client client;

    @Getter
    @Setter
    private AbstractModifyingItem item;
    private final CharacterRace characterRace;
    private final CharacterClass characterClass;
    private int intelligence;
    @Getter
    private int strength;
    private int constitution;
    private int wisdom;
    @Getter
    private int skill;
    @Getter
    private int vision;
    @Setter
    @Getter
    private int goldStat;
    public GameCharacter(final Client client,
                         final String name,
                         final CharacterClass cClass,
                         final CharacterRace cRace,
                         final Coordinate position,
                         final String spriteName,
                         final int maxMovingRange,
                         final double baseDamage) {
        super(name, spriteName, position, baseDamage, maxMovingRange);
        this.client = client;
        setItem(new NoItem("Markus"));
        this.characterClass = cClass;
        this.characterRace = cRace;
        addRaceStatAttributes();
        setMaxHitpoints(calculateMaxHP());
        setHitpoints(getMaxHitpoints());
    }

    @Override
    public double getDamage() {
        return baseDamage + item.getModifierByIdentifier(ModifierIdentifier.DAMAGE);
    }

    /**
     * Removes hitpoints based on incoming attack, after removing defence points from incoming value
     * @param damage - enemy damage value
     */
    @Override
    public void defend(final double damage) {
        double hpWithDefence = hitpoints + item.getModifierByIdentifier(ModifierIdentifier.DEFENCE);
        hitpoints = Math.max(0, hpWithDefence - damage);
    }

    public GameCommand interact(AbstractObject target, Client source, GameMap gameMap, Room room, Coordinate mousePos) {
        Coordinate targetPos = target != null ? target.getPosition() : mousePos;
        if (isAlive() && Coordinate.inRange(getPosition(), targetPos, 1)) {
            if (target == null) {
                return new MoveCommand(source, this, room, mousePos);
            } else if(!target.equals(this) && target instanceof Combatable combatObject) {
                return new AttackCommand(source, this, combatObject);
            } else if (target instanceof Door door) {
                return door.interact(source, this, gameMap);
            }
        }
        return null;
    }

    public int getArmorClass() {
        // TODO: getItemACMod integrieren
        return characterClass.getArmorClass();
    }

    private double calculateMaxHP() {
        return characterClass.getBasicHp() + (this.constitution - 10) * 0.5;
    }

    private void addRaceStatAttributes() {
        constitution += characterRace.getConstitution();
        intelligence += characterRace.getIntelligence();
        skill += characterRace.getSkill();
        strength += characterRace.getStrength();
        wisdom += characterRace.getWisdom();
    }
}

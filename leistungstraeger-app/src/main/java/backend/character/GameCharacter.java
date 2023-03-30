package backend.character;

import backend.abstract_object.Combatable;
import backend.abstract_object.interaction.Interactable;
import backend.character.classes.CharacterClass;
import backend.character.races.CharacterRace;
import backend.item.implementations.weapons.NoWeapon;
import backend.item.implementations.weapons.Weapon;
import backend.item.AbstractModifyingItem;
import backend.item.modifier.ModifierIdentifier;
import backend.item.usables.AbstractUsableItem;
import backend.item.usables.HasActiveEffectList;
import backend.item.usables.AbstractUsableItem;
import backend.network.client.Client;
import helpers.command.CommandInfoDto;
import helpers.command.GameCommand;
import helpers.command.MoveCommand;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

public class GameCharacter extends Combatable implements HasActiveEffectList {

    @Getter
    private final Client client;

    @Getter
    @Setter
    private Weapon weapon;
    @Getter
    @Setter
    private AbstractUsableItem usable;
    private final CharacterRace characterRace;

    private final CharacterClass characterClass;

    private int intelligence;

    @Getter
    private int strength;

    private int constitution;

    private int wisdom;

    @Getter
    private int dexterity;
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
                         final int maxMovingRange) {
        super(name, spriteName, position, maxMovingRange);
        this.client = client;
        setWeapon(new NoWeapon());
        this.characterClass = cClass;
        this.characterRace = cRace;
        addRaceStatAttributes();
        setMaxHitpoints(calculateMaxHP());
        setHitpoints(getMaxHitpoints());
    }

    @Override
    public double getDamage() {
        return switch (weapon.getModifier()) {
            case STRENGTH -> weapon.getDamage(strength);
            case DEXTERITY -> weapon.getDamage(dexterity);
            default -> throw  new UnsupportedOperationException("Falsche Eigenschaft für die Waffe");
        };
    }

    /**
     * Removes hitpoints based on incoming attack, after removing defence points from incoming value
     *
     * @param damage - enemy damage value
     */
    @Override
    public void defend(final double damage) {
//        double hpWithDefence = hitpoints + item.getModifierByIdentifier(ModifierIdentifier.DEFENCE);
//        hitpoints = Math.max(0, hpWithDefence - damage);
    }

    public GameCommand checkInteractions(CommandInfoDto dto) {
        Coordinate targetPos = dto.getTarget() != null
                ? dto.getTarget().getPosition()
                : dto.getMouseClickPos();
        if (isAlive()) {
            // Can the object move to coordinate?
            final int distance = Coordinate.distance(getPosition(), targetPos);
            if (dto.getTarget() == null
                    && dto.getSource().canMoveTiles(distance)
                    && Coordinate.inRange(getPosition(),
                    targetPos,
                    dto.getSource().getMaximumRange())) {
                // Decrease remaining range (this should always work since we check in if clause)
                if (!dto.getSource().moveTiles(distance))
                    throw new IllegalStateException("This should not have happened");
                return new MoveCommand(dto.getClient(),
                        this,
                        dto.getMap().getActiveRoom(),
                        targetPos);
            } else if (dto.getTarget() instanceof Interactable interactable
                    && dto.getSource().canMoveTiles(distance)) {
                return interactable.interact(dto);
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
        dexterity += characterRace.getDexterity();
        strength += characterRace.getStrength();
        wisdom += characterRace.getWisdom();
    }

    @Override
    public void update() {
        activeModifiers.update();
        setHitpoints(getHitpoints() + activeModifiers.getValueForIdentifier(ModifierIdentifier.HEALTH_PER_TURN));
    }
}

package backend.character;

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

public class GameCharacter extends Combatable {

    @Getter
    private final Client client;

    @Getter
    @Setter
    private AbstractModifyingItem item;

    public GameCharacter(final Client client,
                         final String name,
                         final Coordinate position,
                         final String spriteName,
                         final int maxMovingRange,
                         final double maxHitpoints,
                         final double baseDamage) {
        super(name, spriteName, position, maxHitpoints, baseDamage, maxMovingRange);
        this.client = client;
        setItem(new NoItem("Markus"));
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
        if (isAlive()) {
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
}

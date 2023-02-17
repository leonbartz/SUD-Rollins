package core.character;

import core.Item.Item;
import core.Item.NoItem;
import core.client.Client;
import core.playingfield.door.Door;
import core.playingfield.map.GameMap;
import core.playingfield.room.Room;
import helpers.command.AttackCommand;
import helpers.command.ChangeRoomCommand;
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
    private Item item;

    public GameCharacter(Client client, Coordinate position, String spriteName, int maxHitpoints, int baseDamage) {
        super(spriteName, position, maxHitpoints, baseDamage);
        this.client = client;
        setItem(new NoItem());
    }

    @Override
    public int getDamage() {
        return baseDamage + item.getDmgMod();
    }


    public GameCommand interact(GameObject target, Client source, GameMap gameMap, Room room, Coordinate mousePos) {
        if (isAlive() && Coordinate.inRange(mousePos, getPosition(), 1)) {
            if (target == null) {
                return new MoveCommand(source, this, room, mousePos);
            } else if (!target.equals(this) && target instanceof Combatable combatObject) {
                return new AttackCommand(source, this, combatObject);
            } else if (target instanceof Door door) {
                return door.interact(source, this, gameMap);
            }
        }
        return null;
    }
}

package core.playingfield.door;

import core.character.GameCharacter;
import core.character.GameObject;
import core.client.Client;
import core.playingfield.map.GameMap;
import core.playingfield.room.Room;
import helpers.command.ChangeRoomCommand;
import helpers.command.GameCommand;
import helpers.coordinate.CardinalDirection;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Door extends GameObject {

    private Door linkedDoor;
    private final Coordinate exitPosition;

    public Door(String spriteName, Coordinate position, CardinalDirection exitDirection) {
        super(spriteName, position);
        this.exitPosition = new Coordinate(
                position.getXPos() + exitDirection.getXMod(),
                position.getYPos() + exitDirection.getYMod());
    }

    public static void linkDoors(Door door1, Door door2) {
        door1.setLinkedDoor(door2);
        door2.setLinkedDoor(door1);
    }

    public GameCommand interact(Client source, GameCharacter character, GameMap gameMap) {
        return new ChangeRoomCommand(
                source,
                character,
                gameMap,
                gameMap.getObjectRoom(this),
                gameMap.getObjectRoom(linkedDoor),
                linkedDoor.getExitPosition()
        );
    }
}

package helpers.command;

import core.client.Client;
import core.character.GameCharacter;
import core.playingfield.map.GameMap;
import core.playingfield.room.Room;
import helpers.coordinate.Coordinate;

public class ChangeRoomCommand extends GameCommand {

    private final GameCharacter character;
    private final GameMap gameMap;
    private final Room oldRoom;
    private final Room newRoom;
    private final Coordinate exitPosition;

    public ChangeRoomCommand(Client source, GameCharacter character, GameMap gameMap, Room oldRoom, Room newRoom, Coordinate exitPosition) {
        super(source);
        this.character = character;
        this.gameMap = gameMap;
        this.oldRoom = oldRoom;
        this.newRoom = newRoom;
        this.exitPosition = exitPosition;
    }

    @Override
    public void doCommand() {
        if (oldRoom != newRoom) {
            oldRoom.remove(character);
            newRoom.add(character);
            character.setPosition(exitPosition);
            gameMap.setActiveRoom(newRoom);
        }
    }
}

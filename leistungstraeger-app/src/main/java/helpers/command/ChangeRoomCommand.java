package helpers.command;

import backend.character.GameCharacter;
import backend.game.Turn;
import backend.game_map.GameMap;
import backend.game_map.room.Room;
import backend.network.client.Client;
import helpers.coordinate.Coordinate;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ChangeRoomCommand extends GameCommand {

    private final Turn turn;
    private final GameCharacter character;
    private final GameMap gameMap;
    private final Room oldRoom;
    private final Room newRoom;
    private final Coordinate exitPosition;

    public ChangeRoomCommand(final Client source,
                             Turn turn, final GameCharacter character,
                             final GameMap gameMap,
                             final Room oldRoom,
                             final Room newRoom,
                             final Coordinate exitPosition) {
        super(source);
        this.turn = turn;
        this.character = character;
        this.gameMap = gameMap;
        this.oldRoom = oldRoom;
        this.newRoom = newRoom;
        this.exitPosition = exitPosition;
    }

    @Override
    public void doCommand() {
        if (oldRoom != newRoom && turn.consumeMovement()) {
            oldRoom.remove(character);
            newRoom.add(character);
            character.setPosition(exitPosition);
            gameMap.setActiveRoom(newRoom);
        }
    }
}

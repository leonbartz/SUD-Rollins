package helpers.command;


import backend.character.GameCharacter;
import backend.game_map.room.Room;
import backend.network.client.Client;
import helpers.coordinate.Coordinate;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class MoveCommand extends GameCommand {

    private final GameCharacter gameCharacter;
    private final Room room;
    private final Coordinate targetPosition;

    public MoveCommand(final Client source,
                       final GameCharacter gameCharacter,
                       final Room room,
                       final Coordinate targetPosition) {
        super(source);
        this.gameCharacter = gameCharacter;
        this.room = room;
        this.targetPosition = targetPosition;
    }

    @Override
    public void doCommand() {
        if (isInBounds(targetPosition) && gameCharacter.getRemainingRange() > 0) {
            gameCharacter.setPosition(targetPosition);
        }
    }

    public boolean isInBounds(final Coordinate position) {
        return position.getXPos() >= 0 &&
                position.getYPos() >= 0 &&
                position.getXPos() < room.getWidth() &&
                position.getYPos() < room.getHeight();
    }
}

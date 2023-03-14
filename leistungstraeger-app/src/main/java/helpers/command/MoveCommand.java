package helpers.command;


import backend.character.GameCharacter;
import backend.network.client.Client;
import backend.game_map.room.Room;
import helpers.coordinate.Coordinate;

public class MoveCommand extends GameCommand {

    private final GameCharacter gameCharacter;
    private final Room room;
    private final Coordinate targetPosition;

    public MoveCommand(Client source, GameCharacter gameCharacter, Room room, Coordinate targetPosition) {
        super(source);
        this.gameCharacter = gameCharacter;
        this.room = room;
        this.targetPosition = targetPosition;
    }

    @Override
    public void doCommand() {
        if (isInBounds(targetPosition)) {
            gameCharacter.setPosition(targetPosition);
        }
    }

    public boolean isInBounds(Coordinate position) {
        return position.getXPos() >= 0 &&
                position.getYPos() >= 0 &&
                position.getXPos() < room.getWidth() &&
                position.getYPos() < room.getHeight();
    }
}

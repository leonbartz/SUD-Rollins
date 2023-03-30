package helpers.command;


import backend.abstract_object.interaction.Interactable;
import backend.character.GameCharacter;
import backend.game.Turn;
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
    private final Turn turn;

    public MoveCommand(final Client source,
                       final Turn turn,
                       final GameCharacter gameCharacter,
                       final Room room,
                       final Coordinate targetPosition) {
        super(source);
        this.turn = turn;
        this.gameCharacter = gameCharacter;
        this.room = room;
        this.targetPosition = targetPosition;
    }

    @Override
    public void doCommand() {
        final int distance = Coordinate.distance(gameCharacter.getPosition(), targetPosition);
        if (isInBounds(targetPosition) &&
                turn.getMovement() > 0 &&
                gameCharacter.canMoveTiles(distance) &&
                Coordinate.inRange(gameCharacter.getPosition(), targetPosition, turn.getMovement())
        ) {
            if (!gameCharacter.moveTiles(distance)) {
                throw new UnsupportedOperationException("Error");
            } else {
                turn.setMovement(turn.getMovement() - distance);
            }
            gameCharacter.setPosition(targetPosition);
        }
    }

    public boolean isInBounds(final Coordinate position) {
        return position.getXPos() >= 0 &&
                position.getYPos() >= 0 &&
                position.getXPos() < room.getWidth() &&
                position.getYPos() < room.getHeight();
    }


    public void tmp() {
        final int distance = Coordinate.distance(gameCharacter.getPosition(), targetPosition);
        if (gameCharacter.canMoveTiles(distance)
                && Coordinate.inRange(gameCharacter.getPosition(), targetPosition, turn.getMovement())
        ) {
            if (!gameCharacter.moveTiles(distance)) {
                throw new UnsupportedOperationException("Error");
            }
        }
    }
}

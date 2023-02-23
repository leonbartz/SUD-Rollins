package helpers.command;


import core.object.implementation.GameCharacter;
import core.client.Client;
import helpers.coordinate.Coordinate;

public class MoveCommand extends GameCommand {

    private final GameCharacter gameCharacter;
    private final Coordinate targetPosition;

    public MoveCommand(Client source, GameCharacter gameCharacter, Coordinate targetPosition) {
        super(source);
        this.gameCharacter = gameCharacter;
        this.targetPosition = targetPosition;
    }

    @Override
    public void doCommand() {
        gameCharacter.setPosition(targetPosition);
    }
}

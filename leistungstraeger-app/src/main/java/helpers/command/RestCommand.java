package helpers.command;

import backend.character.GameCharacter;
import backend.game.Turn;
import backend.network.client.Client;

public class RestCommand extends GameCommand {

    private final Turn turn;
    private final GameCharacter gameCharacter;

    public RestCommand(Client source, Turn turn, GameCharacter gameCharacter) {
        super(source);
        this.turn = turn;
        this.gameCharacter = gameCharacter;
    }

    @Override
    public void doCommand() {
        if (gameCharacter.isAlive() && turn.consumeAction()) {
            gameCharacter.setHitpoints(gameCharacter.getMaxHitpoints());
            System.out.println("Resting");
        }
    }
}

package helpers.command;


import backend.network.client.Client;
import backend.game.Game;

public class EndTurnCommand extends GameCommand {

    private final Game game;

    public EndTurnCommand(Client source, Game game) {
        super(source);
        this.game = game;
    }

    @Override
    public void doCommand() {
        game.newTurn();
    }
}

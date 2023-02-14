package helpers.command;


import core.client.Client;
import core.game.Game;

public class EndTurnCommand extends GameCommand {

    private final Game game;

    public EndTurnCommand(Client source, Game game) {
        super(source);
        this.game = game;
    }

    @Override
    public void doCommand() {
        game.endTurn();
    }
}

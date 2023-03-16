package helpers.command;

import backend.game.Game;
import backend.network.client.Client;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
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

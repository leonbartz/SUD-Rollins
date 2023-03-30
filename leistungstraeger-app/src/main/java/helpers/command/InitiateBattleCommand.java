package helpers.command;

import backend.abstract_object.Combatable;
import backend.game.Battleround;
import backend.network.client.Client;

public class InitiateBattleCommand extends GameCommand {

    private Battleround battleround;

    public InitiateBattleCommand(Client source, Combatable attacker, Combatable defender) {
        super(source);
        battleround = new Battleround(attacker, defender);
    }

    @Override
    public void doCommand() {
        battleround.doBattleround();
    }
}

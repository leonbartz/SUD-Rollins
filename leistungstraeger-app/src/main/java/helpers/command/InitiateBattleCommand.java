package helpers.command;

import backend.abstract_object.Combatable;
import backend.game.Battleround;
import backend.game.Turn;
import backend.network.client.Client;

public class InitiateBattleCommand extends GameCommand {

    private final Turn turn;
    private final Battleround battleround;

    public InitiateBattleCommand(Client source, Turn turn, Combatable attacker, Combatable defender) {
        super(source);
        this.turn = turn;
        battleround = new Battleround(attacker, defender);
    }

    @Override
    public void doCommand() {
        if (battleround.battlePossible() && turn.consumeMovement()) {
            battleround.doBattleround();
        }
    }
}

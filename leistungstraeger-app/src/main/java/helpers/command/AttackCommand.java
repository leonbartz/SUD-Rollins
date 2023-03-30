package helpers.command;

import backend.abstract_object.Combatable;
import backend.game.Turn;
import backend.network.client.Client;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class AttackCommand extends GameCommand {

    private final Turn turn;

    private final Combatable attacker;

    private final Combatable defender;

    public AttackCommand(Client source, Turn turn, Combatable attacker, Combatable defender) {
        super(source);
        this.turn = turn;
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void doCommand() {
        if (turn.consumeMovement()) {
            defender.defend(attacker.getDamage());
        }
    }
}

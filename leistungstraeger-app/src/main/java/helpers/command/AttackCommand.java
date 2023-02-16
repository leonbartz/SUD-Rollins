package helpers.command;

import core.character.Combatable;
import core.client.Client;

public class AttackCommand extends GameCommand {

    private final Combatable attacker;
    private final Combatable defender;

    public AttackCommand(Client source, Combatable attacker, Combatable defender) {
        super(source);
        this.attacker = attacker;
        this.defender = defender;
    }

    @Override
    public void doCommand() {
        defender.defend(attacker.getDamage());
    }
}

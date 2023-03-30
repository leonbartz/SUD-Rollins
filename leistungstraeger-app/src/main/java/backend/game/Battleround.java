package backend.game;

import backend.abstract_object.Combatable;

public class Battleround {
    private final Combatable attacker;
    private final Combatable defender;

    public Battleround(Combatable attacker, Combatable defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public void doBattleround() {
        defender.defend(attacker.getDamage());
    }
}

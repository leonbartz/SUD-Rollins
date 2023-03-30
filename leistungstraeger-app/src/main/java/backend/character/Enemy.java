package backend.character;

import backend.abstract_object.Combatable;
import helpers.coordinate.Coordinate;

public class Enemy extends Combatable {

    public Enemy(Coordinate position, int maxHitpoints, double baseDamage) {
        super("enemy", "big_demon_idle_anim_f0.png", position, baseDamage, 0);
        this.maxHitpoints = maxHitpoints;
        this.hitpoints = maxHitpoints;
    }

    @Override
    public void defend(double damage) {
        hitpoints = Math.max(0, hitpoints - damage);
    }

    @Override
    public double getDamage() {
        return baseDamage;
    }
}

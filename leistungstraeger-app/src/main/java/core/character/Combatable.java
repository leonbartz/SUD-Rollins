package core.character;

import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import helpers.view.ViewTransformation;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Combatable extends GameObject {

    @Getter
    @Setter
    private int hitpoints;

    @Getter
    @Setter
    private int maxHitpoints;
    protected int baseDamage;

    public Combatable(String spriteName, Coordinate position, int maxHitpoints, int baseDamage) {
        super(spriteName, position);
        this.hitpoints = maxHitpoints;
        this.maxHitpoints = maxHitpoints;
        this.baseDamage = baseDamage;
    }

    public void defend(int damage) {
        hitpoints = Math.max(0, hitpoints - damage);
    }

    public int getDamage() {
        return baseDamage;
    }

    public boolean isAlive() {
        return hitpoints > 0;
    }

}

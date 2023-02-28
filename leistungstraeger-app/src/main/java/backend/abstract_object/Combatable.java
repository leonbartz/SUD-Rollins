package backend.abstract_object;

import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public abstract class Combatable extends MovingAbstractObject {

    @Getter
    @Setter
    protected double hitpoints;

    @Getter
    @Setter
    protected double maxHitpoints;

    protected double baseDamage;

    public Combatable(final String name,
                      final String spriteName,
                      final Coordinate position,
                      final double maxHitpoints,
                      final double baseDamage,
                      final int maxMovingDistance) {
        super(name, UUID.randomUUID(), maxMovingDistance, spriteName, position);
        this.hitpoints = maxHitpoints;
        this.maxHitpoints = maxHitpoints;
        this.baseDamage = baseDamage;
    }

    /**
     * Removes hitpoints based on incoming attack.
     * @param damage - enemy damage value
     */
    public abstract void defend(final double damage);

    public abstract double getDamage();

    public boolean isAlive() {
        return hitpoints > 0;
    }

}

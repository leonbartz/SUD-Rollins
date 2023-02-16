package core.object.implementation;

import core.object.AbstractObject;
import core.object.MovingAbstractObject;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

public class Combatable extends MovingAbstractObject {

    @Getter
    @Setter
    private int hitpoints;

    @Getter
    @Setter
    private int maxHitpoints;

    protected int baseDamage;

    public Combatable(final String name,
                      final String spriteName,
                      final Coordinate position,
                      final int maxHitpoints,
                      final int baseDamage,
                      final int maxMovingDistance) {
        super(name, UUID.randomUUID(), maxMovingDistance, spriteName, position);
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

    @Override
    public void render(Graphics2D g2D, int mapXPos, int mapYPos, int tile_size) {
        super.render(g2D, mapXPos, mapYPos, tile_size);
        if (!isAlive()) {
            g2D.setColor(Color.RED);
            g2D.setStroke(new BasicStroke(2));
            g2D.drawLine(
                    getPosition().getXPos() * tile_size + mapXPos,
                    getPosition().getYPos() * tile_size + mapYPos,
                    getPosition().getXPos() * tile_size + tile_size + mapXPos,
                    getPosition().getYPos() * tile_size + tile_size + mapYPos
            );
            g2D.drawLine(
                    getPosition().getXPos() * tile_size + tile_size+ mapXPos,
                    getPosition().getYPos() * tile_size + mapYPos,
                    getPosition().getXPos() * tile_size + mapXPos,
                    getPosition().getYPos() * tile_size + tile_size + mapYPos
            );
        } else if (maxHitpoints != hitpoints) {
            g2D.setColor(Color.GREEN);
            g2D.fillRect(
                    getPosition().getXPos() * tile_size + mapXPos,
                    getPosition().getYPos() * tile_size + mapYPos,
                    tile_size * hitpoints/maxHitpoints,
                    tile_size /10
            );
        }
    }
}

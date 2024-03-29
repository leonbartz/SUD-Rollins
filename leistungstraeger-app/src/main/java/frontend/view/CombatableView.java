package frontend.view;

import backend.abstract_object.Combatable;
import helpers.view.Renderable;
import helpers.view.ViewTransformation;

import java.awt.*;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class CombatableView extends AbstractObjectView {

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        super.render(g2D, viewTransformation, renderable);
        Combatable combatable = (Combatable) renderable;
        int xPos = combatable.getPosition().getXPos();
        int yPos = combatable.getPosition().getYPos();
        int tile_size = viewTransformation.getTileSize();
        double maxHitpoints = combatable.getMaxHitpoints();
        double hitpoints = combatable.getHitpoints();
        int mapXPos = viewTransformation.getXPos();
        int mapYPos = viewTransformation.getYPos();
        if (!combatable.isAlive()) {
            g2D.setColor(Color.RED);
            g2D.setStroke(new BasicStroke(2));
            g2D.drawLine(
                    xPos * tile_size + mapXPos,
                    yPos * tile_size + mapYPos,
                    xPos * tile_size + tile_size + mapXPos,
                    yPos * tile_size + tile_size + mapYPos
            );
            g2D.drawLine(
                    xPos * tile_size + tile_size+ mapXPos,
                    yPos * tile_size + mapYPos,
                    xPos * tile_size + mapXPos,
                    yPos * tile_size + tile_size + mapYPos
            );
        } else if (maxHitpoints != hitpoints) {
            g2D.setColor(Color.GREEN);
            g2D.fillRect(
                    xPos * tile_size + mapXPos,
                    yPos * tile_size + mapYPos,
                    (int) (tile_size * hitpoints/maxHitpoints),
                    tile_size /10
            );
        }
    }
}

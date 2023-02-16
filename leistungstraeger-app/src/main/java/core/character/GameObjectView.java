package core.character;

import helpers.coordinate.Coordinate;
import helpers.image.ImageController;
import helpers.view.Renderable;
import helpers.view.View;
import helpers.view.ViewTransformation;

import java.awt.*;

public class GameObjectView implements View {

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        GameObject gameObject = (GameObject) renderable;
        Coordinate position = gameObject.getPosition();
        int tile_size = viewTransformation.getTileSize();
        Coordinate mapPos = viewTransformation.getMapPos();
        Image image = ImageController.getImage(gameObject.getSpriteName(), tile_size, tile_size);
        g2D.drawImage(
                image,
                position.getXPos() * tile_size + mapPos.getXPos(),
                position.getYPos() * tile_size + mapPos.getYPos(),
                null
        );
        if (gameObject.isHighlighted()) {
            g2D.setColor(new Color(255, 0, 194, 239));
            g2D.setStroke(new BasicStroke(2));
            g2D.drawRect(
                    position.getXPos() * tile_size + mapPos.getXPos(),
                    position.getYPos() * tile_size + mapPos.getYPos(),
                    tile_size,
                    tile_size
            );
        }
    }
}

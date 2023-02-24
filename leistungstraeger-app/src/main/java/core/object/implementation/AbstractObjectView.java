package core.object.implementation;

import core.object.AbstractObject;
import helpers.coordinate.Coordinate;
import helpers.image.ImageController;
import helpers.view.Renderable;
import helpers.view.View;
import helpers.view.ViewTransformation;

import java.awt.*;

public class AbstractObjectView implements View {

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        AbstractObject abstractObject = (AbstractObject) renderable;
        Coordinate position = abstractObject.getPosition();
        int tile_size = viewTransformation.getTileSize();
        Coordinate mapPos = viewTransformation.getMapPos();
        Image image = ImageController.getImage(abstractObject.getSpriteIdentifier(), tile_size, tile_size);
        g2D.drawImage(
                image,
                position.getXPos() * tile_size + mapPos.getXPos(),
                position.getYPos() * tile_size + mapPos.getYPos(),
                null
        );
    }
}

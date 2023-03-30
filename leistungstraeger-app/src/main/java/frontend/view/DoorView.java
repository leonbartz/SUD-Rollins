package frontend.view;

import backend.game_map.Door;
import helpers.view.Renderable;
import helpers.view.ViewTransformation;

import java.awt.*;

import static helpers.image.DrawImageHelper.drawPictureOnPosition;


/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class DoorView implements View {


    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        Door door = (Door) renderable;
        drawDoorStyles(g2D, door, viewTransformation);
    }

    private void drawDoorStyles(Graphics2D g2D, Door door, ViewTransformation viewTransformation) {
        drawPictureOnPosition(g2D, viewTransformation, door.getPosition().getXPos(), door.getPosition().getYPos(), door.getSpriteIdentifier());
    }
}

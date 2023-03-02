package frontend.view;

import backend.abstract_object.AbstractObject;
import backend.game_map.Room;
import helpers.view.Renderable;
import helpers.coordinate.Coordinate;
import helpers.view.ViewTransformation;
import lombok.Setter;

import java.awt.*;

public class RoomView implements View {

    @Setter
    private Coordinate highlightedField;

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        Room room = (Room) renderable;
        drawGrid(g2D, room, viewTransformation);
        drawGameObjects(g2D, room, viewTransformation);
        drawHighlightedField(g2D, room, viewTransformation);
    }

    private void drawGameObjects(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        for (AbstractObject abstractObject: room.getAbstractObjects()) {
            View view = ViewManager.getView(abstractObject);
            view.render(g2D, viewTransformation, abstractObject);
        }
    }

    private void drawGrid(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        int tile_size = viewTransformation.getTileSize();
        for (int x = 0; x < room.getWidth(); x++) {
            for (int y = 0; y < room.getHeight(); y++) {
                int xPos = x * tile_size + viewTransformation.getXPos();
                int yPos = y * tile_size + viewTransformation.getYPos();
                g2D.setColor(new Color(192, 137, 0, 255));
                g2D.fillRect(xPos, yPos, tile_size, tile_size);
                g2D.setColor(Color.BLACK);
                g2D.drawRect(xPos, yPos, tile_size, tile_size);
            }
        }
    }

    private void drawHighlightedField(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        int tile_size = viewTransformation.getTileSize();
        if (highlightedField == null) return;
        int xPos = highlightedField.getXPos();
        int yPos = highlightedField.getYPos();
        if (xPos >= 0 && xPos < room.getWidth() && yPos >= 0 && yPos < room.getHeight()) {
            g2D.setColor(new Color(208, 208, 208, 76));
            g2D.fillRect(
                    xPos * tile_size + viewTransformation.getXPos(),
                    yPos * tile_size + viewTransformation.getYPos(),
                    tile_size,
                    tile_size
            );
        }
    }
}

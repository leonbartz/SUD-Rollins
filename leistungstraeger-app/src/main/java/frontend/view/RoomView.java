package frontend.view;

import backend.abstract_object.AbstractObject;
import backend.game_map.room.Room;
import backend.game_map.room.RowStyle;
import helpers.coordinate.Coordinate;
import helpers.image.ImageController;
import helpers.view.Renderable;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import helpers.view.ViewTransformation;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;

import static helpers.image.DrawPictureHelber.drawPictureOnPosition;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class RoomView implements View {

    @Setter
    private Coordinate highlightedField;

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        Room room = (Room) renderable;
        drawFloorStyle(g2D, room, viewTransformation);
        drawGameObjects(g2D, room, viewTransformation);
        drawHighlightedField(g2D, room, viewTransformation);
        drawRowStyles(g2D, room, viewTransformation);
        drawSiteStyles(g2D, room, viewTransformation);
    }

    private void drawGameObjects(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        for (AbstractObject abstractObject : room.getAbstractObjects()) {
            View view = ViewManager.getView(abstractObject);
            view.render(g2D, viewTransformation, abstractObject);
        }
    }

    private void drawFloorStyle(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        String[][] roomTileArray = room.getRoomStyle().getTileNameArray();
        for (int x = 0; x < room.getWidth(); x++) {
            for (int y = 0; y < room.getHeight(); y++) {
                String tilePictureName = roomTileArray[x][y];
                drawPictureOnPosition(g2D, viewTransformation, x, y, tilePictureName);
            }
        }
    }

    private void drawSiteStyles(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        String leftSide = room.getRoomStyle().getLeftSideStyle();
        String rightSide = room.getRoomStyle().getRightSideStyle();
        if (!leftSide.isBlank() || !rightSide.isBlank())
            for (int y = 0; y < room.getHeight(); y++) {
                drawPictureOnPosition(g2D, viewTransformation, 0, y, room.getRoomStyle().getLeftSideStyle());
                drawPictureOnPosition(g2D, viewTransformation, room.getWidth() - 1, y, room.getRoomStyle().getRightSideStyle());
            }
    }

    private void drawRowStyles(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        HashMap<Integer, RowStyle> rowStyles = room.getRoomStyle().getRowStyles();
        for (int key : rowStyles.keySet()) {
            String wallPictureName;
            RowStyle rowStyle = rowStyles.get(key);
            for (int x = 0; x < room.getWidth(); x++) {
                if (x == 0) {
                    wallPictureName = rowStyle.getLeftCorner();
                } else if (x == room.getWidth() - 1) {
                    wallPictureName = rowStyle.getRightCorner();
                } else {
                    wallPictureName = rowStyle.getMidRow();
                }
                drawPictureOnPosition(g2D, viewTransformation, x, key, wallPictureName);

            }
        }
    }

    /*
     * Deprecated
     */
    private void drawGrid(Graphics2D g2D, Room room, ViewTransformation viewTransformation) {
        int tileSize = viewTransformation.getTileSize();
        for (int x = 0; x < room.getWidth(); x++) {
            for (int y = 0; y < room.getHeight(); y++) {
                int xPos = x * tileSize + viewTransformation.getXPos();
                int yPos = y * tileSize + viewTransformation.getYPos();
                g2D.setColor(Color.BLACK);
                g2D.drawRect(xPos, yPos, tileSize, tileSize);
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

package core.playingfield.map;

import core.character.Combatable;
import core.character.GameObject;
import core.game.GameView;
import helpers.coordinate.Coordinate;
import helpers.mouse.MapDragInputHandler;
import helpers.view.Renderable;
import helpers.view.View;
import helpers.view.ViewTransformation;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameMapView implements View {

    @Setter
    private Coordinate highlightedField;

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        GameMap gameMap = (GameMap) renderable;
        drawGrid(g2D, gameMap, viewTransformation);
        drawGameObjects(g2D, gameMap, viewTransformation);
        drawHighlightedField(g2D, gameMap, viewTransformation);
    }

    private void drawGameObjects(Graphics2D g2D, GameMap gameMap, ViewTransformation viewTransformation) {
        for (GameObject gameObject: gameMap.getObjects()) {
            View view = GameView.getView(gameObject);
            view.render(g2D, viewTransformation, gameObject);
        }
    }

    private void drawGrid(Graphics2D g2D, GameMap gameMap, ViewTransformation viewTransformation) {
        int tile_size = viewTransformation.getTileSize();
        for (int x = 0; x < gameMap.getWidth(); x++) {
            for (int y = 0; y < gameMap.getHeight(); y++) {
                int xPos = x * tile_size + viewTransformation.getXPos();
                int yPos = y * tile_size + viewTransformation.getYPos();
                g2D.setColor(new Color(192, 137, 0, 255));
                g2D.fillRect(xPos, yPos, tile_size, tile_size);
                g2D.setColor(Color.BLACK);
                g2D.drawRect(xPos, yPos, tile_size, tile_size);
            }
        }
    }

    private void drawHighlightedField(Graphics2D g2D, GameMap gameMap, ViewTransformation viewTransformation) {
        int tile_size = viewTransformation.getTileSize();
        if (highlightedField == null) return;
        int xPos = highlightedField.getXPos();
        int yPos = highlightedField.getYPos();
        if (xPos >= 0 && xPos < gameMap.getWidth() && yPos >= 0 && yPos < gameMap.getHeight()) {
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

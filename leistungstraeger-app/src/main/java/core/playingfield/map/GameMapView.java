package core.playingfield.map;

import core.character.GameObject;
import helpers.Socket.Socket;
import helpers.coordinate.Coordinate;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GameMapView extends JPanel {

    private GameMap map;
    @Getter
    private Coordinate mapPos;
    private Coordinate highlightedField;
    @Getter
    private final Socket<Double> mapZoom;
    @Getter
    private final int baseTileSize = 30;

    public GameMapView() {
        mapZoom = new Socket<>();
        mapZoom.setValue(1.0);
        MouseAdapter mouseAdapter = new MapDragMouseAdapter();
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseWheelListener(mouseAdapter);
    }

    public void setMap(GameMap gameMap) {
        this.map = gameMap;
        mapPos = new Coordinate(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width - map.getWidth()*baseTileSize* getMapZoom().getValue())/2,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height - map.getHeight()*baseTileSize*getMapZoom().getValue())/2
        );
    }

    public void render() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (map != null) {
            Graphics2D g2D = (Graphics2D) g;
            int tile_size = (int) (baseTileSize * mapZoom.getValue());
            drawGrid(g2D, tile_size);
            drawGameObjects(g2D, mapPos, tile_size);
            drawHighlightedField(g2D, tile_size);
        }
    }

    private void drawGameObjects(Graphics2D g2D, Coordinate mapPos, int tile_size) {
        for (GameObject gameObject: map.getObjects()) {
            gameObject.render(g2D, mapPos.getXPos(), mapPos.getYPos(), tile_size);
        }
    }

    private void drawGrid(Graphics2D g2D, int tile_size) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int xPos = x * tile_size + mapPos.getXPos();
                int yPos = y * tile_size + mapPos.getYPos();
                g2D.setColor(new Color(192, 137, 0, 255));
                g2D.fillRect(xPos, yPos, tile_size, tile_size);
                g2D.setColor(Color.BLACK);
                g2D.drawRect(xPos, yPos, tile_size, tile_size);
            }
        }
    }

    private void drawHighlightedField(Graphics2D g2D, int tile_size) {
        if (highlightedField == null) return;
        int xPos = highlightedField.getXPos();
        int yPos = highlightedField.getYPos();
        if (xPos >= 0 && xPos < map.getWidth() && yPos >= 0 && yPos < map.getHeight()) {
            g2D.setColor(new Color(208, 208, 208, 76));
            g2D.fillRect(
                    xPos * tile_size + mapPos.getXPos(),
                    yPos * tile_size + mapPos.getYPos(),
                    tile_size,
                    tile_size
            );
        }
    }

    private class MapDragMouseAdapter extends MouseAdapter {
        private int lastX;
        private int lastY;
        @Override
        public void mousePressed(MouseEvent e) {
            lastX = e.getX();
            lastY = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mapPos.setXPos(mapPos.getXPos() + e.getX() - lastX);
            lastX = e.getX();
            mapPos.setYPos(mapPos.getYPos() + e.getY() - lastY);
            lastY = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            int newX = (e.getX() - mapPos.getXPos()) / (int) (baseTileSize * mapZoom.getValue());
            int newY = (e.getY() - mapPos.getYPos()) / (int) (baseTileSize * mapZoom.getValue());
            newX = e.getX() - mapPos.getXPos() < 0 ? newX - 1 : newX;
            newY = e.getY() - mapPos.getYPos() < 0 ? newY - 1 : newY;
            highlightedField = new Coordinate(newX, newY);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            double minZoom = 0.5;
            double maxZoom = 3;
            mapZoom.setValue(Math.max(Math.min(mapZoom.getValue() + e.getWheelRotation() * 0.1, maxZoom), minZoom));
        }
    }
}

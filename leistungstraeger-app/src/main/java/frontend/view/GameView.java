package frontend.view;

import backend.game_map.GameMap;
import helpers.coordinate.Coordinate;
import helpers.mouse.MapDragInputHandler;
import helpers.view.ViewTransformation;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameView extends JPanel {

    @Setter
    private GameMap gameMap;
    @Getter
    private final ViewTransformation viewTransformation;
    @Getter
    private boolean isRendering;

    public GameView() {
        isRendering = false;
        viewTransformation = new ViewTransformation(new Coordinate(0, 0), 30);
        MouseAdapter mouseAdapter = new MapDragInputHandler(viewTransformation);
        this.setBackground(Color.BLACK);
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
        this.addMouseWheelListener(mouseAdapter);
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int newX = (e.getX() - viewTransformation.getXPos()) / viewTransformation.getTileSize();
                int newY = (e.getY() - viewTransformation.getYPos()) / viewTransformation.getTileSize();
                newX = e.getX() - viewTransformation.getXPos() < 0 ? newX - 1 : newX;
                newY = e.getY() - viewTransformation.getYPos() < 0 ? newY - 1 : newY;
                ViewManager.getRoomView().setHighlightedField(new Coordinate(newX, newY));
            }
        });
    }

    public void render() {
        isRendering = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isRendering) {
            Graphics2D g2D = (Graphics2D) g;
            View gameMapView = ViewManager.getView(gameMap);
            gameMapView.render(g2D, viewTransformation, gameMap);
            isRendering = false;
        }
    }

    public Coordinate getTransformedMousePosition(Coordinate mousePixelCoordinate) {
        int mouseXPos = mousePixelCoordinate.getXPos();
        int mouseYPos = mousePixelCoordinate.getYPos();
        int mapXPos = viewTransformation.getXPos();
        int mapYPos = viewTransformation.getYPos();
        int tile_size = viewTransformation.getTileSize();
        int newX = (mouseXPos - mapXPos) / tile_size;
        int newY = (mouseYPos - mapYPos) / tile_size;
        newX = mouseXPos - mapXPos < 0 ? newX - 1 : newX;
        newY = mouseYPos - mapYPos < 0 ? newY - 1 : newY;
        return new Coordinate(newX, newY);
    }
}

package core.game;

import core.character.*;
import core.playingfield.map.GameMap;
import core.playingfield.map.GameMapView;
import core.playingfield.room.Room;
import core.playingfield.room.RoomView;
import helpers.coordinate.Coordinate;
import helpers.mouse.MapDragInputHandler;
import helpers.view.Renderable;
import helpers.view.View;
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
    private static final GameMapView gameMapView = new GameMapView();
    private static final RoomView roomView = new RoomView();
    private static final GameObjectView gameObjectView = new GameObjectView();
    private static final GameCharacterView gameCharacterView = new GameCharacterView();

    public GameView() {
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
                roomView.setHighlightedField(new Coordinate(newX, newY));
            }
        });
    }

    public void render() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        gameMapView.render(g2D, viewTransformation, gameMap);
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

    public static View getView(Renderable renderable) {
        if (renderable instanceof GameMap) {
            return gameMapView;
        } else if (renderable instanceof Room) {
            return roomView;
        } else if (renderable instanceof GameCharacter) {
            return gameCharacterView;
        } else if (renderable instanceof GameObject) {
            return gameObjectView;
        }
        throw new UnsupportedOperationException();
    }
}

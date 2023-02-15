package helpers.mouse;

import helpers.Socket.Socket;
import helpers.coordinate.Coordinate;
import lombok.Setter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapMouseInputHandler extends MouseAdapter {

    private Coordinate lastClickedPosition;
    @Setter
    private Coordinate mapPos;
    @Setter
    private Socket<Double> mapZoom;
    @Setter
    private int baseTileSize;

    public MapMouseInputHandler() {
        lastClickedPosition = null;
    }

    public Coordinate getLastClickedPosition() {
        Coordinate lastPos = lastClickedPosition;
        lastClickedPosition = null;
        return lastPos;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mapPos != null) {
            int newX = (e.getX() - mapPos.getXPos()) / (int) (baseTileSize * mapZoom.getValue());
            int newY = (e.getY() - mapPos.getYPos()) / (int) (baseTileSize * mapZoom.getValue());
            newX = e.getX() - mapPos.getXPos() < 0 ? newX - 1 : newX;
            newY = e.getY() - mapPos.getYPos() < 0 ? newY - 1 : newY;
            lastClickedPosition = new Coordinate(newX, newY);
        }
    }
}

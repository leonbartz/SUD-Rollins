package helpers.mouse;

import helpers.Socket.Socket;
import helpers.coordinate.Coordinate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private Coordinate lastClickedPosition;
    private final Coordinate mapPos;
    private final Socket<Double> mapZoom;
    private final int baseTileSize;

    public MouseHandler(Coordinate mapPos, Socket<Double> mapZoom, int baseTileSize) {
        lastClickedPosition = null;
        this.mapPos = mapPos;
        this.mapZoom = mapZoom;
        this.baseTileSize = baseTileSize;
    }

    public Coordinate getLastClickedPosition() {
        Coordinate lastPos = lastClickedPosition;
        lastClickedPosition = null;
        return lastPos;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int newX = (e.getX() - mapPos.getXPos()) / (int) (baseTileSize * mapZoom.getValue());
        int newY = (e.getY() - mapPos.getYPos()) / (int) (baseTileSize * mapZoom.getValue());
        newX = e.getX() - mapPos.getXPos() < 0 ? newX - 1 : newX;
        newY = e.getY() - mapPos.getYPos() < 0 ? newY - 1 : newY;
        lastClickedPosition = new Coordinate(newX, newY);
    }
}

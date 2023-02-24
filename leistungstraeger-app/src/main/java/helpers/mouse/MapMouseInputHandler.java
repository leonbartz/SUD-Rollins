package helpers.mouse;

import helpers.Socket.Socket;
import helpers.coordinate.Coordinate;
import lombok.Setter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapMouseInputHandler extends MouseAdapter {

    private Coordinate lastClickedPosition;
    private MouseButton lastClickedButton;

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
        lastClickedPosition = new Coordinate(e.getX(), e.getY());
        lastClickedButton = switch (e.getButton()) {
            case 1 -> MouseButton.LEFT_BUTTON;
            case 2 -> MouseButton.MOUSE_WHEEL;
            case 3 -> MouseButton.RIGHT_BUTTON;
            default -> null;
        };
    }
}

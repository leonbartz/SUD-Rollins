package helpers.mouse;

import helpers.view.ViewTransformation;
import lombok.Getter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import static helpers.mouse.MouseButton.*;

@SuppressWarnings("FieldCanBeLocal")
public class MapDragInputHandler extends MouseAdapter {

    @Getter
    private final ViewTransformation vt;
    private int lastX;
    private int lastY;
    private MouseButton lastClickedButton;
    private final double MIN_ZOOM = 0.5;
    private final double MAX_ZOOM = 3;
    private final double SCROLL_CHANGE_RATE = 0.1;

    public MapDragInputHandler(ViewTransformation viewTransformation) {
        this.vt = viewTransformation;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastClickedButton = switch (e.getButton()) {
            case 1 -> LEFT_BUTTON;
            case 2 -> MOUSE_WHEEL;
            case 3 -> RIGHT_BUTTON;
            default -> null;
        };
        if (lastClickedButton == RIGHT_BUTTON) {
            lastX = e.getX();
            lastY = e.getY();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (lastClickedButton == RIGHT_BUTTON) {
            vt.setXPos(vt.getXPos() + e.getX() - lastX);
            lastX = e.getX();
            vt.setYPos(vt.getYPos() + e.getY() - lastY);
            lastY = e.getY();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double oldZoom = (double) vt.getTileSize()/vt.getBaseTileSize();
        double newZoom = oldZoom + e.getWheelRotation() * SCROLL_CHANGE_RATE;
        double mapZoom = Math.max(Math.min(newZoom, MAX_ZOOM), MIN_ZOOM);
        vt.setTileSize((int) (mapZoom * vt.getBaseTileSize()));
    }
}

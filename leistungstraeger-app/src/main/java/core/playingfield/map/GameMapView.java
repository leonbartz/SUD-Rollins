package core.playingfield.map;

import core.game.GameView;
import core.playingfield.room.Room;
import helpers.view.Renderable;
import helpers.view.View;
import helpers.view.ViewTransformation;
import java.awt.*;

public class GameMapView implements View {

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        GameMap gameMap = (GameMap) renderable;
        Room activeRoom = gameMap.getActiveRoom();
        View roomView = GameView.getView(activeRoom);
        roomView.render(g2D, viewTransformation, activeRoom);
    }
}

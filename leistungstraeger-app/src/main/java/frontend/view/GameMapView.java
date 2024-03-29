package frontend.view;

import backend.game_map.GameMap;
import backend.game_map.room.Room;
import helpers.view.Renderable;
import helpers.view.ViewTransformation;

import java.awt.*;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class GameMapView implements View {

    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        GameMap gameMap = (GameMap) renderable;
        Room activeRoom = gameMap.getActiveRoom();
        View roomView = ViewManager.getView(activeRoom);
        roomView.render(g2D, viewTransformation, activeRoom);
    }
}

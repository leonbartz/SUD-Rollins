package frontend.view;

import backend.abstract_object.AbstractObject;
import backend.character.GameCharacter;
import backend.game_map.GameMap;
import backend.game_map.Room;
import helpers.view.Renderable;

public class ViewManager {

    private static final GameMapView gameMapView = new GameMapView();
    private static final RoomView roomView = new RoomView();
    private static final AbstractObjectView gameObjectView = new AbstractObjectView();
    private static final GameCharacterView gameCharacterView = new GameCharacterView();

    public static View getView(Renderable renderable) {
        if (renderable instanceof GameMap) {
            return gameMapView;
        } else if (renderable instanceof Room) {
            return roomView;
        } else if (renderable instanceof GameCharacter) {
            return gameCharacterView;
        } else if (renderable instanceof AbstractObject) {
            return gameObjectView;
        }
        throw new UnsupportedOperationException();
    }

    public static RoomView getRoomView() {
        return roomView;
    }
}

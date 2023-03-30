package frontend.view;

import backend.abstract_object.AbstractObject;
import backend.character.GameCharacter;
import backend.game_map.GameMap;
import backend.game_map.room.Room;
import backend.item.Inventory;
import helpers.view.Renderable;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class ViewManager {

    private static final GameMapView gameMapView = new GameMapView();
    private static final RoomView roomView = new RoomView();
    private static final AbstractObjectView gameObjectView = new AbstractObjectView();
    private static final GameCharacterView gameCharacterView = new GameCharacterView();
    private static final InventoryView inventoryView = new InventoryView();

    public static View getView(Renderable renderable) {
        if (renderable instanceof GameMap) {
            return gameMapView;
        } else if (renderable instanceof Room) {
            return roomView;
        } else if (renderable instanceof GameCharacter) {
            return gameCharacterView;
        } else if (renderable instanceof AbstractObject) {
            return gameObjectView;
        } else if (renderable instanceof Inventory) {
            return inventoryView;
        }
        throw new UnsupportedOperationException();
    }

    public static RoomView getRoomView() {
        return roomView;
    }
}

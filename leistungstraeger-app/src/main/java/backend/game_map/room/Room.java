package backend.game_map.room;

import backend.abstract_object.AbstractObject;
import backend.game_map.Door;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Room implements Renderable {

    @Getter
    private final HashSet<AbstractObject> abstractObjects;

    @Getter
    private final int width;

    @Getter
    private final int height;

    @Getter
    private final RoomStyle roomStyle;

    @Getter
    private final ArrayList<Door> doors = new ArrayList<>();

    public Room(int width, int height, RoomStyle roomStyle) {
        abstractObjects = new HashSet<>();
        this.width = width;
        this.height = height;
        this.roomStyle = roomStyle;
        roomStyle.fitStyleToRoom(width, height);
    }

    public void add(AbstractObject abstractObject) {
        if(abstractObject instanceof Door){
            this.doors.add((Door) abstractObject);
        }
        this.abstractObjects.add(abstractObject);
    }

    public void add(List<? extends AbstractObject> gameObjects) {
        abstractObjects.addAll(gameObjects);
    }

    public void remove(AbstractObject gameObject) {
        this.abstractObjects.remove(gameObject);
    }

    public AbstractObject getObject(Coordinate position) {
        for (AbstractObject gameObject : abstractObjects) {
            if (Coordinate.equals(gameObject.getPosition(), position)) {
                return gameObject;
            }
        }
        return null;
    }

    public boolean containsAbstractObject(AbstractObject abstractObject) {
        for (AbstractObject go : abstractObjects) {
            if (go == abstractObject) {
                return true;
            }
        }
        return false;
    }
}

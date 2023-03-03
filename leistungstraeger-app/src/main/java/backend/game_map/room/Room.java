package backend.game_map.room;

import backend.abstract_object.AbstractObject;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Room implements Renderable {

    @Getter
    private final HashSet<AbstractObject> abstractObjects;
    @Getter
    private final int width;
    @Getter
    private final int height;

    @Getter
    private final RoomStyle roomStyle;

    public Room(int width, int height, RoomStyle roomStyle) {
        abstractObjects = new HashSet<>();
        this.width = width;
        this.height = height;
        this.roomStyle = roomStyle;
        roomStyle.setTileNameArray(width, height);
    }

    public void addWalls(int width, int height){
        String name = "wall";
        // first row; wall top
        add(new Wall(name, UUID.randomUUID(), "wall_top_left.png", new Coordinate(0,0) ));
        for (int i = 1; i <width-1; i++){
            add(new Wall(name, UUID.randomUUID(), "wall_top_mid.png", new Coordinate(i,0)));
        }
        add(new Wall(name, UUID.randomUUID(), "wall_top_right.png", new Coordinate(width,0)));

        // second row; wall
        add(new Wall(name, UUID.randomUUID(), "wall_corner_top_left.png", new Coordinate(0,1) ));
        for (int i = 1; i <width-1; i++){
            add(new Wall(name, UUID.randomUUID(), "wall_corner_top_mid.png", new Coordinate(i,1)));
        }
        add(new Wall(name, UUID.randomUUID(), "wall_corner_top_right.png", new Coordinate(width,1)));

        // left wall
        for (int i = 2; i <height-1; i++){
            add(new Wall(name, UUID.randomUUID(), "wall_corner_top_mid.png", new Coordinate(i,1)));
        }
    }

    public void add(AbstractObject abstractObject) {
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

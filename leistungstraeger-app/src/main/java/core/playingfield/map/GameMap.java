package core.playingfield.map;

import core.object.AbstractObject;
import helpers.coordinate.Coordinate;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;

public class GameMap {

    @Getter
    private final HashSet<AbstractObject> objects;
    @Getter
    private final int width;
    @Getter
    private final int height;
    public GameMap(final int width,final int height) {
        objects = new HashSet<>();
        this.width = width;
        this.height = height;
    }

    public void add(final AbstractObject gameObject) {
        this.objects.add(gameObject);
    }

    public void add(final List<? extends AbstractObject> gameObjects) {
        objects.addAll(gameObjects);
    }

    public void remove(final AbstractObject gameObject) {
        this.objects.remove(gameObject);
    }

    public AbstractObject getObject(final Coordinate position) {
        for (AbstractObject gameObject : objects) {
            if (Coordinate.equals(gameObject.getPosition(), position)) {
                return gameObject;
            }
        }
        return null;
    }
}

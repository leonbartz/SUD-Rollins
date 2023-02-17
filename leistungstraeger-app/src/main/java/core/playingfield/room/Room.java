package core.playingfield.room;

import core.character.GameCharacter;
import core.character.GameObject;
import core.client.Client;
import core.game.Game;
import core.playingfield.door.Door;
import core.playingfield.map.GameMap;
import helpers.command.ChangeRoomCommand;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;

public class Room implements Renderable {

    @Getter
    private final HashSet<GameObject> objects;
    @Getter
    private final int width;
    @Getter
    private final int height;

    public Room(int width, int height) {
        objects = new HashSet<>();
        this.width = width;
        this.height = height;
    }

    public void add(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    public void add(List<? extends GameObject> gameObjects) {
        objects.addAll(gameObjects);
    }

    public void remove(GameObject gameObject) {
        this.objects.remove(gameObject);
    }

    public GameObject getObject(Coordinate position) {
        for (GameObject gameObject : objects) {
            if (Coordinate.equals(gameObject.getPosition(), position)) {
                return gameObject;
            }
        }
        return null;
    }

    public boolean containsGameObject(GameObject gameObject) {
        for (GameObject go : objects) {
            if (go == gameObject) {
                return true;
            }
        }
        return false;
    }
}

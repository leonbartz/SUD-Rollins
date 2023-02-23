package core.playingfield.map;

import core.character.GameObject;
import core.playingfield.room.Room;
import helpers.view.Renderable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class GameMap implements Renderable {

    @Getter
    @Setter
    private Room activeRoom;
    @Getter
    private final List<Room> objects;

    public GameMap() {
        objects = new ArrayList<>();
    }

    public void add(Room... rooms) {
        this.objects.addAll(Arrays.asList(rooms));
    }

    public void remove(Room room) {
        this.objects.remove(room);
    }

    public Room getObjectRoom(GameObject gameObject) {
        for (Room room : objects) {
            if (room.containsGameObject(gameObject)) {
                return room;
            }
        }
        return null;
    }
}

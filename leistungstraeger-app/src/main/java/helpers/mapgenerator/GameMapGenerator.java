package helpers.mapgenerator;

import backend.character.GameCharacter;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.game_map.room.DungeonRoomStyle;
import backend.game_map.room.Room;
import helpers.coordinate.CardinalDirection;
import helpers.coordinate.Coordinate;

import java.util.List;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class GameMapGenerator {

    private final GameMap gameMap = new GameMap();

    public GameMapGenerator() {}

    public GameMap generate(List<GameCharacter> characters){
        startRoom(characters);
        hallway();
        labyrinth();
        trophyRoom();
        return gameMap;
    }

    private final Door door1 = new Door("floor_ladder.png", new Coordinate(5, 3), CardinalDirection.NORTH);
    public void startRoom(List<GameCharacter> characters) {
        Room room = new Room(7, 4, new DungeonRoomStyle());
        room.add(characters);
        room.add(door1);
        gameMap.add(room);
        gameMap.setActiveRoom(room);
    }
    private final Door door2 = new Door("floor_ladder.png", new Coordinate(0, 1), CardinalDirection.EAST);
    private final Door door3 = new Door("floor_ladder.png", new Coordinate(7, 1), CardinalDirection.WEST);
    public void hallway() {
        Room room = new Room(8, 3, new DungeonRoomStyle());
        room.add(door2);
        room.add(door3);
        Door.linkDoors(door1, door2);
        gameMap.add(room);
    }

    private final Door door4 = new Door("floor_ladder.png", new Coordinate(4, 0), CardinalDirection.SOUTH);
    private final Door door5 = new Door("floor_ladder.png", new Coordinate(9, 9), CardinalDirection.WEST);
    public void labyrinth() {
        Room room = new Room(10, 10, new DungeonRoomStyle());
        room.add(door4);
        room.add(door5);
        Door.linkDoors(door3, door4);
        gameMap.add(room);
    }

    private final Door door6 = new Door("floor_ladder.png", new Coordinate(1, 2), CardinalDirection.EAST);
    public void trophyRoom() {
        Room room = new Room(5, 5, new DungeonRoomStyle());
        room.add(door6);
        Door.linkDoors(door5, door6);
        gameMap.add(room);
    }

}

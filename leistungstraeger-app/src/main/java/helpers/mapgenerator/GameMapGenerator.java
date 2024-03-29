package helpers.mapgenerator;

import backend.character.Enemy;
import backend.character.GameCharacter;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.game_map.room.DungeonRoomStyle;
import backend.game_map.room.Room;
import backend.obstacle.Wall;
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
        room.add(Wall.getWallBulk(
                new Coordinate(3, 1),
                new Coordinate(2, 3)
        ));
        gameMap.setActiveRoom(room);
    }
    private final Door door2 = new Door("floor_ladder.png", new Coordinate(0, 1), CardinalDirection.EAST);
    private final Door door3 = new Door("floor_ladder.png", new Coordinate(7, 1), CardinalDirection.WEST);
    public void hallway() {
        Room room = new Room(8, 3, new DungeonRoomStyle());
        room.add(door2);
        room.add(door3);
        room.add(new Enemy(new Coordinate(5,1), 10, 1));
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
        room.add(new Enemy(new Coordinate(7,2), 5, 1));
        room.add(new Enemy(new Coordinate(1,2), 7, 1));
        room.add(new Enemy(new Coordinate(5,4), 7, 1));
        room.add(new Enemy(new Coordinate(6,9), 10, 2));
        room.add(Wall.getWallBulk(
                new Coordinate(3, 0),
                new Coordinate(5, 0),
                new Coordinate(1, 1),
                new Coordinate(2, 1),
                new Coordinate(3, 1),
                new Coordinate(5, 1),
                new Coordinate(6, 1),
                new Coordinate(8, 1),
                new Coordinate(6, 2),
                new Coordinate(8,2),
                new Coordinate(1,3),
                new Coordinate(2,3),
                new Coordinate(3,3),
                new Coordinate(4,3),
                new Coordinate(8,3),
                new Coordinate(6,4),
                new Coordinate(7,4),
                new Coordinate(8,4),
                new Coordinate(1,5),
                new Coordinate(3,5),
                new Coordinate(4,5),
                new Coordinate(6,5),
                new Coordinate(7,5),
                new Coordinate(8,5),
                new Coordinate(0,6),
                new Coordinate(3,6),
                new Coordinate(4,6),
                new Coordinate(5,6),
                new Coordinate(1,7),
                new Coordinate(2,7),
                new Coordinate(7,7),
                new Coordinate(8,7),
                new Coordinate(9,7),
                new Coordinate(1,8),
                new Coordinate(2,8),
                new Coordinate(4,8),
                new Coordinate(5,8),
                new Coordinate(6,8),
                new Coordinate(7,8),
                new Coordinate(8,8),
                new Coordinate(9,8)
        ));
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

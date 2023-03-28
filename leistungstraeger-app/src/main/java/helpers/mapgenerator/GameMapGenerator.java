package helpers.mapgenerator;

import backend.character.GameCharacter;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.game_map.room.DungeonDoorStyle;
import backend.game_map.room.DungeonRoomStyle;
import backend.game_map.room.Room;
import helpers.coordinate.CardinalDirection;
import helpers.coordinate.Coordinate;

import java.util.List;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class GameMapGenerator {

    public GameMapGenerator() {

    }

    public GameMap generate(List<GameCharacter> characters){
        GameMap gameMap = new GameMap();

        Door door1 = new Door(new DungeonDoorStyle().getMiddleSprite(), new Coordinate(5, 4), CardinalDirection.NORTH);
        Door door2 = new Door(new DungeonDoorStyle().getSideSprite(), new Coordinate(-1, 1), CardinalDirection.EAST);
        Door door3 = new Door(new DungeonDoorStyle().getTopDownSprite(), new Coordinate(5, 10), CardinalDirection.EAST);
        Door.linkDoors(door1, door2);
        Room room1 = new Room(10, 10, new DungeonRoomStyle());
        room1.add(characters);
        room1.add(door1);
        Room room2 = new Room(10, 3, new DungeonRoomStyle());
        room2.add(door2);
        gameMap.add(room1, room2);
        gameMap.setActiveRoom(room1);

        return gameMap;
    }

}

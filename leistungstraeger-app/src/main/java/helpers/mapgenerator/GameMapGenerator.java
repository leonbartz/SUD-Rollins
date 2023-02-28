package helpers.mapgenerator;

import backend.character.GameCharacter;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.game_map.Room;
import helpers.coordinate.CardinalDirection;
import helpers.coordinate.Coordinate;

import java.util.List;

public class GameMapGenerator {

    public GameMapGenerator() {

    }

    public GameMap generate(List<GameCharacter> characters){
        GameMap gameMap = new GameMap();

        Door door1 = new Door("character", new Coordinate(5, 5), CardinalDirection.NORTH);
        Door door2 = new Door("character", new Coordinate(0, 1), CardinalDirection.EAST);
        Door.linkDoors(door1, door2);
        Room room1 = new Room(10, 10);
        room1.add(characters);
        room1.add(door1);
        Room room2 = new Room(10, 3);
        room2.add(door2);
        gameMap.add(room1, room2);
        gameMap.setActiveRoom(room1);

        return gameMap;
    }

}

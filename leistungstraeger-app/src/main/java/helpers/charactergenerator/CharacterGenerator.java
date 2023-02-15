package helpers.charactergenerator;

import core.character.GameCharacter;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;

public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters() {
        RingList<GameCharacter> list = new RingList<>();
        list.add(new GameCharacter(1, new Coordinate(2, 2), "character", 20, 1));
        list.add(new GameCharacter(1, new Coordinate(4, 2), "character", 20, 1));
        return list;
    }
}

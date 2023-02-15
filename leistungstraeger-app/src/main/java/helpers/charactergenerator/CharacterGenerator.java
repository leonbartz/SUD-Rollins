package helpers.charactergenerator;

import core.character.GameCharacter;
import core.client.Client;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;

public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters(Client client) {
        RingList<GameCharacter> list = new RingList<>();
        list.add(new GameCharacter(client, new Coordinate(2, 2), "character", 20, 1));
        list.add(new GameCharacter(new Client(2), new Coordinate(4, 2), "character", 20, 1));
        return list;
    }
}

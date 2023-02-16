package helpers.charactergenerator;

import core.object.implementation.GameCharacter;
import core.client.Client;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;

public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters(Client client) {
        RingList<GameCharacter> list = new RingList<>();
        list.add(new GameCharacter(
                client,
                "David",
                new Coordinate(2, 2),
                "character",
                2,
                20,
                1));
        list.add(new GameCharacter(
                new Client(2),
                "David2",
                new Coordinate(4, 2),
                "character",
                2,
                20,
                1));
        return list;
    }
}
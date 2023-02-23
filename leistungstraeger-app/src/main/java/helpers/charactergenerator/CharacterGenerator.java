package helpers.charactergenerator;

import core.client.Client;
import core.item.implementations.Sword;
import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;
import core.object.implementation.GameCharacter;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;

public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters(Client client) {
        RingList<GameCharacter> list = new RingList<>();
        list.add(new GameCharacter(client,"Burbl", new Coordinate(2, 2), "character.png", 2, 20,1));
        list.add(new GameCharacter(new Client(2),"Dieter", new Coordinate(4, 2), "character.png",2, 20, 1));
        Sword sword = new Sword("Hammer", new Modifier(ModifierIdentifier.DAMAGE, 200));
        GameCharacter character = new GameCharacter(
                client,
                "David",
                new Coordinate(2, 2),
                "character",
                2,
                20,
                1);
        character.setItem(sword);
        list.add(character);
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

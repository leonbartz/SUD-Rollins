package helpers.charactergenerator;

import core.client.Client;
import core.item.implementations.Sword;
import core.item.modifier.Modifier;
import core.item.modifier.ModifierIdentifier;
import core.character.GameCharacter;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;

public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters(Client client) {
        RingList<GameCharacter> list = new RingList<>();
        Sword sword = new Sword("Hammer",
                new Modifier(ModifierIdentifier.DAMAGE, 200));
        GameCharacter character1 = new GameCharacter(
                client,
                "Dieter",
                new Coordinate(4, 2),
                "character.png",
                2,
                20,
                1);
        GameCharacter character2 = new GameCharacter(
                new Client(2),
                "David",
                new Coordinate(2, 2),
                "character.png",
                2,
                20,
                1);
        character1.setItem(sword);
        list.add(character1);
        list.add(character2);
        return list;
    }
}

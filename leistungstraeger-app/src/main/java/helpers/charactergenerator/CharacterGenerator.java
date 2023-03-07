package helpers.charactergenerator;

import backend.character.classes.Mage;
import backend.character.classes.Warrior;
import backend.character.races.Dwarf;
import backend.character.races.Hobbit;
import backend.network.client.Client;
import backend.item.implementations.Sword;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import backend.character.GameCharacter;
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
                new Warrior(),
                new Dwarf(),
                new Coordinate(4, 2),
                "character.png",
                2,
                1);
        GameCharacter character2 = new GameCharacter(
                new Client(2),
                "David",
                new Mage(),
                new Hobbit(),
                new Coordinate(2, 2),
                "character.png",
                2,
                1);
        character1.setItem(sword);
        list.add(character1);
        list.add(character2);
        return list;
    }
}

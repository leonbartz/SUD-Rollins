package helpers.charactergenerator;

import backend.network.client.Client;
import backend.item.implementations.Sword;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import backend.character.GameCharacter;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters(Client client) {
        RingList<GameCharacter> list = new RingList<>();
        Sword sword = new Sword("Hammer",
                new Modifier(ModifierIdentifier.DAMAGE, 200));
        GameCharacter character1 = new GameCharacter(
                client,
                "Dieter",
                new Coordinate(4, 2),
                "knight_f_idle_anim_f0.png",
                2,
                20,
                1);
        GameCharacter character2 = new GameCharacter(
                new Client(2),
                "David",
                new Coordinate(2, 2),
                "big_demon_idle_anim_f0.png",
                2,
                20,
                1);
        character1.setItem(sword);
        list.add(character1);
        list.add(character2);
        return list;
    }
}

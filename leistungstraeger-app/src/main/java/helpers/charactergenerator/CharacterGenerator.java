package helpers.charactergenerator;

import backend.character.GameCharacter;
import backend.character.classes.Mage;
import backend.character.classes.Warrior;
import backend.character.races.Dwarf;
import backend.character.races.Hobbit;
import backend.item.implementations.Sword;
import backend.item.modifier.Modifier;
import backend.item.modifier.ModifierIdentifier;
import backend.item.usables.implementations.DamagePotion;
import backend.network.client.Client;
import helpers.collections.RingList;
import helpers.coordinate.Coordinate;

import java.util.Arrays;
import java.util.Random;

public class CharacterGenerator {

    public RingList<GameCharacter> generateCharacters(Client client) {
        RingList<GameCharacter> list = new RingList<>();
        Sword sword = new Sword("Hammer",
                new Modifier(ModifierIdentifier.DAMAGE, 1));
        Sword shortSword = new Sword("shortSword",
                new Modifier(ModifierIdentifier.DAMAGE, 1));
        GameCharacter character1 = new GameCharacter(
                client,
                "Dieter",
                new Warrior(),
                new Dwarf(),
                new Coordinate(4, 2),
                "knight_f_idle_anim_f0.png",
                5,
                1);
        GameCharacter character2 = new GameCharacter(
                client,
                "David",
                new Mage(),
                new Hobbit(),
                new Coordinate(2, 2),
                "lizard_f_idle_anim_f0.png",
                5,
                1);
        character1.setItem(sword);
        character1.setUsable(new DamagePotion("Frisch gepreschtes Monschterle"));
        character2.setItem(shortSword);
        list.add(character1);
        list.add(character2);
        return list;
    }
    private int[] generateAttributes() {
        Random random = new Random();
        int[] attributes = new int[5];
        Arrays.fill(attributes,random.nextInt(16) + 3);
        int sum = Arrays.stream(attributes).sum();
        while (sum < 60) {
            int min = 0;
            for (int j = 0; j < attributes.length; j++) {
                if (attributes[j] < attributes[min]) {
                    min = j;
                }
            }
            attributes[min] = random.nextInt(18 - attributes[min] + 1) + attributes[min];
            sum = Arrays.stream(attributes).sum();
        }
        return attributes;
    }
}

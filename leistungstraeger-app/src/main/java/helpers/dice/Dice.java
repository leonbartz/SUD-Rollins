package helpers.dice;

import java.util.Random;

public class Dice {
    private static Random random = new Random();
    public static int roll(int diceSides) {
        return random.nextInt(diceSides) + 1;
    }

    public static int roll(int numberOfDice, int diceSides) {
        int count = 0;
        for (int i = 0; i < numberOfDice; i++) {
            count += roll(diceSides);
        }
        return count;
    }

    public static int roll(int numberOfDice, int diceSides, int flatMod) {
        return roll(numberOfDice, diceSides) + flatMod;
    }
}

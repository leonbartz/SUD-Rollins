package backend.game;

import backend.character.GameCharacter;
import backend.network.client.Client;
import lombok.Getter;
import lombok.Setter;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Turn {
    @Getter
    @Setter
    private int movement;
    @Getter
    @Setter
    private int actions;
    @Getter
    private final GameCharacter turnCharacter;
    @Getter
    private final Client turnClient;

    public Turn(final GameCharacter turnPlayer) {
        actions = 1;
        movement = turnPlayer.getMaximumRange();
        this.turnCharacter = turnPlayer;
        this.turnClient = turnCharacter.getClient();
    }
}

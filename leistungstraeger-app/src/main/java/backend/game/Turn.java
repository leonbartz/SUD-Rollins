package backend.game;

import backend.character.GameCharacter;
import backend.network.client.Client;
import lombok.Getter;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Turn {
    @Getter
    private final GameCharacter turnCharacter;
    @Getter
    private final Client turnClient;

    public Turn(final GameCharacter turnPlayer) {
        this.turnCharacter = turnPlayer;
        this.turnClient = turnCharacter.getClient();
    }
}

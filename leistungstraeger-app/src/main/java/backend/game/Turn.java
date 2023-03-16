package backend.game;

import backend.character.GameCharacter;
import backend.network.client.Client;
import lombok.Getter;

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

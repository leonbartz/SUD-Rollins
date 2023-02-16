package core.game;

import core.object.implementation.GameCharacter;
import core.client.Client;
import lombok.Getter;

public class Turn {
    @Getter
    private final GameCharacter turnCharacter;
    @Getter
    private final Client turnClient;

    public Turn(GameCharacter turnPlayer) {
        this.turnCharacter = turnPlayer;
        this.turnClient = turnCharacter.getClient();
    }
}
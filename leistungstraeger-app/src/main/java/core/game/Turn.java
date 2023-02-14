package core.game;

import core.character.GameCharacter;
import lombok.Getter;

public class Turn {
    @Getter
    private final GameCharacter turnCharacter;

    public Turn(GameCharacter turnPlayer) {
        this.turnCharacter = turnPlayer;
    }
}

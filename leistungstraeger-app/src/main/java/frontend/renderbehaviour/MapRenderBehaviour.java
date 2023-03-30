package frontend.renderbehaviour;

import backend.abstract_object.AbstractObject;
import backend.character.GameCharacter;
import backend.game.Game;
import backend.game_map.GameMap;
import backend.network.client.Client;
import helpers.command.CommandInfoDto;
import helpers.command.EndTurnCommand;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.sql.SQLOutput;

public class MapRenderBehaviour extends RenderBehaviour {

    private final GameMap gameMap;

    public MapRenderBehaviour(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Renderable getRenderable() {
        return gameMap;
    }

    @Override
    public void handleMouseClick(Game game) {
        Coordinate mousePos = game.getMouseHandler().getLastClickedPosition();
        if (mousePos != null) {
            Coordinate mouseClickPos = game.getGameView().getTransformedMousePosition(mousePos);
            AbstractObject target = game.getGameMap().getActiveRoom().getObject(mouseClickPos);
            Client turnClient = game.getTurnSocket().getValue().getTurnClient();
            GameCharacter turnCharacter = game.getTurnSocket().getValue().getTurnCharacter();
            CommandInfoDto dto = new CommandInfoDto(turnCharacter, target, turnClient, game.getGameMap(), mouseClickPos);
            GameCommand command = turnCharacter.checkInteractions(dto);
            game.getCommandManager().receiveCommand(command);
        }
    }

    @Override
    public void handleKeyboard(Game game) {
        Client turnClient = game.getTurnSocket().getValue().getTurnClient();
        if (game.getKeyHandler().isKeyPressed(KeyEvent.VK_ENTER)) {
            game.getCommandManager().receiveCommand(new EndTurnCommand(turnClient, game));
        }
        if (game.getKeyHandler().isKeyPressed(KeyEvent.VK_ESCAPE)) {
            game.endGame();
        }
    }
}

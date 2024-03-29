package frontend.renderbehaviour;

import backend.abstract_object.AbstractObject;
import backend.character.GameCharacter;
import backend.game.Game;
import backend.game.Turn;
import backend.game_map.GameMap;
import backend.item.usables.Effect;
import backend.network.client.Client;
import helpers.command.*;
import helpers.coordinate.Coordinate;
import helpers.keyboard.KeyboardHandler;
import helpers.view.Renderable;

import java.awt.event.KeyEvent;

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
            AbstractObject target = gameMap.getActiveRoom().getObject(mouseClickPos);
            Client turnClient = game.getTurnSocket().getValue().getTurnClient();
            GameCharacter turnCharacter = game.getTurnSocket().getValue().getTurnCharacter();
            Turn turn = game.getTurnSocket().getValue();
            CommandInfoDto dto = new CommandInfoDto(turnCharacter, turn, target, turnClient, gameMap, mouseClickPos);
            GameCommand command = turnCharacter.checkInteractions(dto);
            game.getCommandManager().receiveCommand(command);
        }
    }

    @Override
    public void handleKeyboard(Game game) {
        final Client turnClient = game.getTurnSocket().getValue().getTurnClient();
        final GameCharacter character = game.getTurnSocket().getValue().getTurnCharacter();
        GameCommand gameCommand = null;
        CommandManager commandManager = game.getCommandManager();
        KeyboardHandler keyHandler = game.getKeyHandler();
        Turn turn = game.getTurnSocket().getValue();
        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            commandManager.receiveCommand(new EndTurnCommand(turnClient, game));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            game.endGame();
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_W)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos(),
                    character.getPosition().getYPos() - 1);
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    turn,
                    gameMap.getActiveRoom().getObject(targetPos),
                    turnClient,
                    gameMap,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_S)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos(),
                    character.getPosition().getYPos() + 1);
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    turn,
                    gameMap.getActiveRoom().getObject(targetPos),
                    turnClient,
                    gameMap,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_A)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos() - 1,
                    character.getPosition().getYPos());
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    turn,
                    gameMap.getActiveRoom().getObject(targetPos),
                    turnClient,
                    gameMap,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_D)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos() + 1,
                    character.getPosition().getYPos());
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    turn,
                    gameMap.getActiveRoom().getObject(targetPos),
                    turnClient,
                    gameMap,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_E)) {
            if (character.getUsable() != null) {
                final Effect usableEffect = character.getUsable().use(character);
                usableEffect.use();
                System.out.println(character.getName() + " used " + character.getUsable().getDisplayName() + ".");
            }
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_R)) {
            gameCommand = new RestCommand(turnClient, turn, turn.getTurnCharacter());
        }
        if (gameCommand != null) commandManager.receiveCommand(gameCommand);
    }
}

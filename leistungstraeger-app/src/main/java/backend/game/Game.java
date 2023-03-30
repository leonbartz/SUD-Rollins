package backend.game;

import backend.abstract_object.AbstractObject;
import backend.abstract_object.MovingAbstractObject;
import backend.character.GameCharacter;
import backend.game_map.GameMap;
import frontend.renderbehaviour.RenderBehaviour;
import frontend.renderbehaviour.RenderBehaviourManager;
import backend.item.usables.Effect;
import backend.network.client.Client;
import backend.network.client.socket.TurnSocket;
import frontend.view.GameView;
import helpers.collections.RingList;
import helpers.command.CommandInfoDto;
import helpers.command.CommandManager;
import helpers.command.EndTurnCommand;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import helpers.keyboard.KeyboardHandler;
import helpers.mouse.MapMouseInputHandler;
import helpers.view.ViewTransformation;
import lombok.Getter;

import java.awt.*;
import java.awt.event.KeyEvent;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Game {

    private boolean isRunning;
    private final int frames_per_second;
    @Getter
    private final TurnSocket turnSocket;
    private final RingList<GameCharacter> characters;
    @Getter
    private final GameMap gameMap;
    @Getter
    private final GameView gameView;
    @Getter
    private final CommandManager commandManager;
    @Getter
    private final MapMouseInputHandler mouseHandler;
    @Getter
    private final KeyboardHandler keyHandler;
    @Getter
    private final RenderBehaviourManager renderBehaviourManager;

    public Game(final int fps,
                final GameMap gameMap,
                final RingList<GameCharacter> characters,
                final GameView gameView, CommandManager commandManager,
                final MapMouseInputHandler mouseHandler,
                final KeyboardHandler keyHandler, RenderBehaviourManager renderBehaviourManager) {
        this.frames_per_second = fps;
        this.gameMap = gameMap;
        this.gameView = gameView;
        this.characters = characters;
        this.commandManager = commandManager;
        this.mouseHandler = mouseHandler;
        this.keyHandler = keyHandler;
        this.renderBehaviourManager = renderBehaviourManager;

        this.isRunning = true;
        this.turnSocket = new TurnSocket();
        adjustMapStartTransformation();
    }

    private void adjustMapStartTransformation() {
        ViewTransformation vt = gameView.getViewTransformation();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        vt.setXPos((screenSize.width - gameMap.getActiveRoom().getWidth() * vt.getTileSize()) / 2);
        vt.setYPos((screenSize.height - gameMap.getActiveRoom().getHeight() * vt.getTileSize()) / 2);
    }

    public void newTurn() {
        characters.next();
        gameMap.setActiveRoom(gameMap.getObjectRoom(characters.getElement()));
        turnSocket.setValue(new Turn(characters.getElement()));
        updateOnTurn();
    }

    // For updates which happen once per turn
    private void updateOnTurn() {
        // Resets the moving distance counter
        characters.toList().forEach(MovingAbstractObject::resetAfterTurn);
        // Update the ActiveEffectLists
        characters.toList().forEach(GameCharacter::update);
    }

    public void start() {
        newTurn();
        while (isRunning) {
            checkInputs();
            update();
            render();
            sleep(1000 / frames_per_second);
        }
    }

    private void checkInputs() {
        RenderBehaviour activeBehaviour = renderBehaviourManager.getActiveRenderBehaviour();
        activeBehaviour.handleMouseClick(this);
        activeBehaviour.handleKeyboard(this);
    }

    public void endGame() {
        isRunning = false;
    }

    private void update() {
        // Other update
        commandManager.consumeCommand();
    }

    private void render() {
        // Other renders
        gameView.render();
        while (gameView.isRendering()) {
            sleep(1);
        }
    }

    private void sleep(final long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

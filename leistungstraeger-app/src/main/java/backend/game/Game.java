package backend.game;

import backend.abstract_object.AbstractObject;
import backend.abstract_object.MovingAbstractObject;
import backend.character.GameCharacter;
import backend.game_map.GameMap;
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

import java.awt.*;
import java.awt.event.KeyEvent;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class Game {

    private boolean isRunning;
    private final int frames_per_second;
    private final TurnSocket turnSocket;
    private final GameMap map;
    private final RingList<GameCharacter> characters;
    private final GameView gameView;
    private final CommandManager commandManager;
    private final MapMouseInputHandler mouseHandler;
    private final KeyboardHandler keyHandler;

    public Game(final int fps,
                final GameMap gameMap,
                final RingList<GameCharacter> characters,
                final GameView gameView, CommandManager commandManager,
                final MapMouseInputHandler mouseHandler,
                final KeyboardHandler keyHandler) {
        this.frames_per_second = fps;
        this.map = gameMap;
        this.gameView = gameView;
        this.characters = characters;
        this.commandManager = commandManager;
        this.mouseHandler = mouseHandler;
        this.keyHandler = keyHandler;

        this.isRunning = true;
        this.turnSocket = new TurnSocket();
        adjustMapStartTransformation();
    }

    private void adjustMapStartTransformation() {
        ViewTransformation vt = gameView.getViewTransformation();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        vt.setXPos((screenSize.width - map.getActiveRoom().getWidth() * vt.getTileSize()) / 2);
        vt.setYPos((screenSize.height - map.getActiveRoom().getHeight() * vt.getTileSize()) / 2);
    }

    public void newTurn() {
        characters.next();
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
        Coordinate lastClickedPosition = mouseHandler.getLastClickedPosition();
        handleMouseClick(lastClickedPosition);
        handleKeyboard();
    }

    public void handleMouseClick(Coordinate mousePos) {
        if (mousePos != null) {
            Coordinate mouseClickPos = gameView.getTransformedMousePosition(mousePos);
            AbstractObject target = map.getActiveRoom().getObject(mouseClickPos);
            Client turnClient = turnSocket.getValue().getTurnClient();
            GameCharacter turnCharacter = turnSocket.getValue().getTurnCharacter();
            CommandInfoDto dto = new CommandInfoDto(turnCharacter, target, turnClient, map, mouseClickPos);
            GameCommand command = turnCharacter.checkInteractions(dto);
            commandManager.receiveCommand(command);
        }
    }

    public void handleKeyboard() {
        final Client turnClient = turnSocket.getValue().getTurnClient();
        final GameCharacter character = turnSocket.getValue().getTurnCharacter();
        GameCommand gameCommand = null;
        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            commandManager.receiveCommand(new EndTurnCommand(turnClient, this));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            endGame();
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_W)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos(),
                    character.getPosition().getYPos() - 1);
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    map.getActiveRoom().getObject(targetPos),
                    turnClient,
                    map,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_S)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos(),
                    character.getPosition().getYPos() + 1);
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    map.getActiveRoom().getObject(targetPos),
                    turnClient,
                    map,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_A)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos() - 1,
                    character.getPosition().getYPos());
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    map.getActiveRoom().getObject(targetPos),
                    turnClient,
                    map,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_D)) {
            final Coordinate targetPos = new Coordinate(
                    character.getPosition().getXPos() + 1,
                    character.getPosition().getYPos());
            gameCommand = character.checkInteractions(new CommandInfoDto(
                    character,
                    map.getActiveRoom().getObject(targetPos),
                    turnClient,
                    map,
                    targetPos));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_E)) {
            if (character.getUsable() != null) {
                final Effect usableEffect = character.getUsable().use(character);
                usableEffect.use();
                System.out.println(character.getName() + " used " + character.getUsable().getDisplayName() + ".");
            }
        }
        if (gameCommand != null) commandManager.receiveCommand(gameCommand);
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

package backend.game;

import backend.network.client.Client;
import backend.abstract_object.AbstractObject;
import backend.character.GameCharacter;
import backend.network.client.socket.TurnSocket;
import backend.game_map.GameMap;
import backend.game_map.Room;
import frontend.view.GameView;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.command.EndTurnCommand;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import helpers.keyboard.KeyboardHandler;
import helpers.mouse.MapMouseInputHandler;
import helpers.view.ViewTransformation;

import java.awt.*;
import java.awt.event.KeyEvent;

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

    public Game(int fps, GameMap gameMap, RingList<GameCharacter> characters, GameView gameView, CommandManager commandManager, MapMouseInputHandler mouseHandler, KeyboardHandler keyHandler) {
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
    }

    public void start() {
        newTurn();
        while(isRunning) {
            checkInputs();
            update();
            render();
            sleep();
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
            Room activeRoom = map.getActiveRoom();
            GameCharacter turnCharacter = turnSocket
                    .getValue()
                    .getTurnCharacter()
                    ;
            GameCommand command = turnCharacter.interact(target, turnClient, map, activeRoom, mouseClickPos);
            commandManager.receiveCommand(command);
        }
    }

    public void handleKeyboard() {
        Client turnClient = turnSocket.getValue().getTurnClient();
        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            commandManager.receiveCommand(new EndTurnCommand(turnClient, this));
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            endGame();
        }
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
    }

    private void sleep() {
        try {
            Thread.sleep(1000/ frames_per_second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

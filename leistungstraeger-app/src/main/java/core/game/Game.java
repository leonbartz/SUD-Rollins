package core.game;

import core.object.implementation.GameCharacter;
import core.object.AbstractObject;
import core.playingfield.map.GameMap;
import core.playingfield.map.GameMapView;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.command.EndTurnCommand;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import helpers.keyboard.KeyboardHandler;
import helpers.mouse.MapMouseInputHandler;

import java.awt.event.KeyEvent;

public class Game {

    private boolean isRunning;
    private final int frames_per_second;
    private final TurnSocket turnSocket;
    private final GameMap map;
    private final RingList<GameCharacter> characters;
    private final GameMapView mapView;
    private final CommandManager commandManager;
    private final MapMouseInputHandler mouseHandler;
    private final KeyboardHandler keyHandler;

    public Game(final int fps,
                final GameMap gameMap,
                final RingList<GameCharacter> characters,
                final GameMapView mapView,
                final CommandManager commandManager,
                final MapMouseInputHandler mouseHandler,
                final KeyboardHandler keyHandler) {
        this.frames_per_second = fps;
        this.map = gameMap;
        this.mapView = mapView;
        this.characters = characters;
        this.commandManager = commandManager;
        this.mouseHandler = mouseHandler;
        this.keyHandler = keyHandler;

        this.isRunning = true;
        this.turnSocket = new TurnSocket();
    }

    public void newTurn() {
        characters.getElement().setHighlighted(false);
        characters.next();
        characters.getElement().setHighlighted(true);
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
        Coordinate mouseClickPos = mouseHandler.getLastClickedPosition();
        if (mouseClickPos != null) {
            AbstractObject target = map.getObject(mouseClickPos);
            GameCommand command = turnSocket
                    .getValue()
                    .getTurnCharacter()
                    .interact(target, turnSocket.getValue().getTurnClient(), mouseClickPos);
            commandManager.receiveCommand(command);
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            commandManager.receiveCommand(new EndTurnCommand(turnSocket.getValue().getTurnClient(), this));
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
        mapView.render();
    }

    private void sleep() {
        try {
            Thread.sleep(1000/ frames_per_second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
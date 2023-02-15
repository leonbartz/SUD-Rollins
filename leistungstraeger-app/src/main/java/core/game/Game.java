package core.game;

import core.character.GameCharacter;
import core.character.GameObject;
import core.client.Client;
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
    private final Client client;
    private final TurnSocket turnSocket;
    private final GameMap map;
    private final RingList<GameCharacter> characters;
    private final GameMapView mapView;
    private final CommandManager commandManager;
    private final MapMouseInputHandler mouseHandler;
    private final KeyboardHandler keyHandler;

    public Game(Client client, int fps, GameMap gameMap, RingList<GameCharacter> characters, GameMapView mapView, CommandManager commandManager, MapMouseInputHandler mouseHandler, KeyboardHandler keyHandler) {
        this.client = client;
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
            GameObject target = map.getObject(mouseClickPos);
            GameCommand command = turnSocket.getValue().getTurnCharacter().interact(target, client, mouseClickPos);
            commandManager.receiveCommand(command);
        }
        if (keyHandler.isKeyPressed(KeyEvent.VK_ENTER)) {
            commandManager.receiveCommand(new EndTurnCommand(client, this));
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

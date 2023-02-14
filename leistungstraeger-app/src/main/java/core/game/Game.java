package core.game;

import core.character.GameCharacter;
import core.character.GameObject;
import core.client.Client;
import core.playingfield.map.GameMap;
import core.playingfield.map.MapView;
import core.window.Window;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.command.EndTurnCommand;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import helpers.keyboard.KeyboardHandler;
import helpers.mouse.MouseHandler;

import java.awt.event.KeyEvent;

public class Game {

    private boolean isRunning;
    private final int frames_per_second;
    private final Client client;
    private final TurnSocket turn;
    private final GameMap map;
    private final RingList<GameCharacter> characters;
    private final Window window;
    private final MapView mapView;
    private final CommandManager commandManager;
    private final MouseHandler mouseHandler;
    private final KeyboardHandler keyHandler;

    public Game(Client client, int fps) {
        isRunning = true;
        this.client = client;
        frames_per_second = fps;

        keyHandler = new KeyboardHandler();

        characters = generateCharacters();

        turn = new TurnSocket();
        turn.setValue(new Turn(characters.getElement()));
        characters.getElement().setHighlighted(true);

        commandManager = new CommandManager(client);

        window = new Window(700, 500);

        map = new GameMap(20, 10);
        map.add(characters.toList());

        mapView = new MapView(map);
        mouseHandler = new MouseHandler(mapView.getMapPos(), mapView.getMapZoom(), mapView.getBaseTileSize());
        mapView.addMouseListener(mouseHandler);

        window.getWindowPanel().add(mapView);
        window.addKeyListener(keyHandler);
    }

    private RingList<GameCharacter> generateCharacters() {
        RingList<GameCharacter> list = new RingList<>();
        list.add(new GameCharacter(1, new Coordinate(2, 2), "character", 20, 1));
        list.add(new GameCharacter(1, new Coordinate(4, 2), "character", 20, 1));
        list.resetActiveElement();
        return list;
    }

    public void endTurn() {
        characters.getElement().setHighlighted(false);
        characters.next();
        characters.getElement().setHighlighted(true);
        turn.setValue(new Turn(characters.getElement()));
    }

    public void start() {
        window.show(true);
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
            GameCommand command = turn.getValue().getTurnCharacter().interact(target, client, mouseClickPos);
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
        window.closeWindow();
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

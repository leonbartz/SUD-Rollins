import core.character.GameCharacter;
import core.client.Client;
import core.game.Game;
import core.playingfield.map.GameMap;
import core.playingfield.map.GameMapView;
import core.window.Window;
import helpers.charactergenerator.CharacterGenerator;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.image.ImageController;
import helpers.keyboard.KeyboardHandler;
import helpers.mouse.MapMouseInputHandler;

import java.io.IOException;

public class DesktopLauncher {
    public static void main(String[] args) {
        Client client = new Client(1);
        KeyboardHandler keyHandler = new KeyboardHandler();
        CommandManager commandManager = new CommandManager();
        commandManager.setClient(client);
        CharacterGenerator characterGenerator = new CharacterGenerator();
        RingList<GameCharacter> characters = characterGenerator.generateCharacters(client);
        GameMap gameMap = new GameMap(20, 10);
        gameMap.add(characters.toList());
        GameMapView mapView = new GameMapView();
        mapView.setMap(gameMap);
        MapMouseInputHandler mouseHandler = new MapMouseInputHandler();
        mapView.addMouseListener(mouseHandler);
        mouseHandler.setMapPos(mapView.getMapPos());
        mouseHandler.setMapZoom(mapView.getMapZoom());
        mouseHandler.setBaseTileSize(mapView.getBaseTileSize());
        Window window = new Window();
        window.addKeyListener(keyHandler);
        window.getWindowPanel().add(mapView);
        Game game = new Game(
                60,
                gameMap,
                characters,
                mapView,
                commandManager,
                mouseHandler,
                keyHandler
        );
        ImageController.loadImages();
        window.show();
        game.start();
        window.closeWindow();
    }
}

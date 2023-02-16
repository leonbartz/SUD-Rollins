import core.character.GameCharacter;
import core.client.Client;
import core.game.Game;
import core.game.GameView;
import core.playingfield.map.GameMap;
import core.playingfield.map.GameMapView;
import core.window.Window;
import helpers.charactergenerator.CharacterGenerator;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.image.ImageController;
import helpers.keyboard.KeyboardHandler;
import helpers.mouse.MapMouseInputHandler;

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
        GameView gameView = new GameView();
        MapMouseInputHandler mouseHandler = new MapMouseInputHandler();
        gameView.addMouseListener(mouseHandler);
        gameView.setGameMap(gameMap);
        Window window = new Window();
        window.addKeyListener(keyHandler);
        window.getWindowPanel().add(gameView);
        Game game = new Game(
                60,
                gameMap,
                characters,
                gameView,
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

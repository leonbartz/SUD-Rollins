import backend.character.GameCharacter;
import backend.network.client.Client;
import backend.game.Game;
import frontend.view.GameView;
import backend.game_map.GameMap;
import frontend.GameWindow;
import helpers.charactergenerator.CharacterGenerator;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.image.ImageController;
import helpers.keyboard.KeyboardHandler;
import helpers.mapgenerator.GameMapGenerator;
import helpers.mouse.MapMouseInputHandler;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class DesktopLauncher {
    public static void main(String[] args) {
        Client client = new Client(1);
        KeyboardHandler keyHandler = new KeyboardHandler();
        CommandManager commandManager = new CommandManager();
        commandManager.setClient(client);
        CharacterGenerator characterGenerator = new CharacterGenerator();
        RingList<GameCharacter> characters = characterGenerator.generateCharacters(client);
        GameMapGenerator gameMapGenerator = new GameMapGenerator();
        GameMap gameMap = gameMapGenerator.generate(characters.toList());


        GameView gameView = new GameView();
        MapMouseInputHandler mouseHandler = new MapMouseInputHandler();
        gameView.addMouseListener(mouseHandler);
        gameView.setGameMap(gameMap);
        GameWindow window = new GameWindow();
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

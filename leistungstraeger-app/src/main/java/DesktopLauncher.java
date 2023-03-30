import backend.character.GameCharacter;
import backend.game.Game;
import backend.item.Inventory;
import frontend.renderbehaviour.InventoryRenderBehaviour;
import frontend.renderbehaviour.MapRenderBehaviour;
import frontend.renderbehaviour.RenderBehaviourManager;
import frontend.view.GameView;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.network.client.Client;
import frontend.GameWindow;
import frontend.view.GameView;
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
        GameWindow window = new GameWindow(500, 500);
        window.addKeyListener(keyHandler);
        window.getWindowPanel().add(gameView);
        MapRenderBehaviour mapRenderBehaviour = new MapRenderBehaviour(gameMap);
        InventoryRenderBehaviour inventoryRenderBehaviour = new InventoryRenderBehaviour(new Inventory());
        RenderBehaviourManager renderBehaviourManager = new RenderBehaviourManager(mapRenderBehaviour, inventoryRenderBehaviour);
        Game game = new Game(
                60,
                gameMap,
                characters,
                gameView,
                commandManager,
                mouseHandler,
                keyHandler,
                renderBehaviourManager
        );
        gameView.setGame(game);
        ImageController.loadImages();
        window.show();
        game.start();
        window.closeWindow();
    }
}

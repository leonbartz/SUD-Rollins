import backend.character.GameCharacter;
import backend.network.client.Client;
import backend.game.Game;
import frontend.renderbehaviour.MapRenderBehaviour;
import frontend.renderbehaviour.RenderBehaviourManager;
import frontend.view.GameView;
import backend.game_map.Door;
import backend.game_map.GameMap;
import backend.game_map.Room;
import frontend.GameWindow;
import helpers.charactergenerator.CharacterGenerator;
import helpers.collections.RingList;
import helpers.command.CommandManager;
import helpers.coordinate.CardinalDirection;
import helpers.coordinate.Coordinate;
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
        GameMap gameMap = new GameMap();

        Door door1 = new Door("character", new Coordinate(5, 5), CardinalDirection.NORTH);
        Door door2 = new Door("character", new Coordinate(0, 1), CardinalDirection.EAST);
        Door.linkDoors(door1, door2);
        Room room1 = new Room(10, 10);
        room1.add(characters.toList());
        room1.add(door1);
        Room room2 = new Room(10, 3);
        room2.add(door2);
        gameMap.add(room1, room2);
        gameMap.setActiveRoom(room1);

        GameView gameView = new GameView();
        MapMouseInputHandler mouseHandler = new MapMouseInputHandler();
        gameView.addMouseListener(mouseHandler);
        gameView.setGameMap(gameMap);
        GameWindow window = new GameWindow();
        window.addKeyListener(keyHandler);
        window.getWindowPanel().add(gameView);
        MapRenderBehaviour mapRenderBehaviour = new MapRenderBehaviour(gameMap);
        RenderBehaviourManager renderBehaviourManager = new RenderBehaviourManager(mapRenderBehaviour);
        Game game = new Game(
                60,
                gameMap,
                characters,
                gameView,
                commandManager,
                mouseHandler,
                keyHandler,
                renderBehaviourManager);
        ImageController.loadImages();
        window.show();
        game.start();
        window.closeWindow();
    }
}

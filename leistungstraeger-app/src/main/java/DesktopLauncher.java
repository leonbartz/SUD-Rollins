import core.client.Client;
import core.game.Game;
import helpers.image.ImageController;

public class DesktopLauncher {
    public static void main(String[] args) {
        Client client = new Client(1);
        Game game = new Game(client, 60);
        ImageController.loadImages();
        game.start();
    }
}

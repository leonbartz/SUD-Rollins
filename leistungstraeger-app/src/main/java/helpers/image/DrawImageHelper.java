package helpers.image;

import helpers.view.ViewTransformation;

import java.awt.*;

public class DrawImageHelper {
    public static void drawPictureOnPosition(Graphics2D g2D, ViewTransformation viewTransformation, int x, int y, String pictureName) {
        int tile_size = viewTransformation.getTileSize();
        int xPos = x * tile_size + viewTransformation.getXPos();
        int yPos = y * tile_size + viewTransformation.getYPos();
        Image image = ImageController.getImage(pictureName, tile_size, tile_size);
        g2D.drawImage(image, xPos, yPos, null);
    }
}

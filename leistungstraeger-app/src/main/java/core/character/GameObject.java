package core.character;

import helpers.coordinate.Coordinate;
import helpers.image.ImageController;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class GameObject {

    @Getter
    @Setter
    private Coordinate position;
    private final String spriteName;
    @Setter
    private boolean isHighlighted;

    public GameObject(String spriteName, Coordinate position) {
        this.spriteName = spriteName;
        this.position = position;
    }

    public void render(Graphics2D g2D, int mapXPos, int mapYPos, int tile_size) {
        Image image = ImageController.getImage(spriteName, tile_size, tile_size);
        g2D.drawImage(
                image,
                position.getXPos() * tile_size + mapXPos,
                position.getYPos() * tile_size + mapYPos,
                null
        );
        if (isHighlighted) {
            g2D.setColor(new Color(255, 0, 194, 239));
            g2D.setStroke(new BasicStroke(2));
            g2D.drawRect(
                    position.getXPos() * tile_size + mapXPos,
                    position.getYPos() * tile_size + mapYPos,
                    tile_size,
                    tile_size
            );
        }
    }
}

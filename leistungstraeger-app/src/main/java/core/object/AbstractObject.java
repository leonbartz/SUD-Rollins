package core.object;

import helpers.coordinate.Coordinate;
import helpers.image.ImageController;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.UUID;

/**
 * Used for all objects which represent a map-object (like a chess piece). Blocks LOS for players.
 */
public abstract class AbstractObject {

    @Getter
    private final UUID id;

    @Getter
    private final String name;

    // UUID of only player who can move the object
    @Getter
    private final UUID owner;

    private final String spriteIdentifier;

    @Setter
    private boolean isHighlighted;

    @Getter
    @Setter
    private Coordinate position;

    protected AbstractObject(final String name,
                             final UUID owner,
                             final String sprite,
                             final Coordinate startingPosition) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null.");
        id = UUID.randomUUID();
        this.owner = owner;
        this.name = name;
        this.spriteIdentifier = sprite;
        this.position = startingPosition;
    }

    public void render(Graphics2D g2D, int mapXPos, int mapYPos, int tile_size) {
        Image image = ImageController.getImage(spriteIdentifier, tile_size, tile_size);
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

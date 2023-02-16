package core.character;

import helpers.coordinate.Coordinate;
import helpers.image.ImageController;
import helpers.view.Renderable;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class GameObject implements Renderable {

    @Getter
    @Setter
    private Coordinate position;
    @Getter
    private final String spriteName;
    @Getter
    @Setter
    private boolean isHighlighted;

    public GameObject(String spriteName, Coordinate position) {
        this.spriteName = spriteName;
        this.position = position;
    }

}

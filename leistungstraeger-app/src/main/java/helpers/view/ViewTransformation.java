package helpers.view;

import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

public class ViewTransformation {

    @Getter
    private final Coordinate mapPos;
    @Getter
    @Setter
    private Integer tileSize;

    @Getter
    private final int baseTileSize = 30;

    public ViewTransformation(Coordinate mapPos, int tile_size) {
        this.mapPos = mapPos;
        this.tileSize = tile_size;
    }

    public void setXPos(int xPos) {
        mapPos.setXPos(xPos);
    }

    public void setYPos(int yPos) {
        mapPos.setYPos(yPos);
    }

    public int getXPos() {
        return mapPos.getXPos();
    }

    public int getYPos() {
        return mapPos.getYPos();
    }
}

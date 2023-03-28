package backend.game_map.room;

import lombok.Getter;
import lombok.Setter;
import backend.game_map.room.TilesWeightedRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public abstract class RoomStyle {

    @Getter
    @Setter
    private HashMap<Integer, RowStyle> rowStyles = new HashMap<>();

    @Getter
    private String leftSideStyle;

    @Getter
    private String rightSideStyle;

    @Setter
    private ArrayList<String> floorPictureNames;

    @Getter
    private String[][] tileNameArray;

    /**
     * should be changed to a global random -> injected from top to bottom to be testable
     */
    private final Random random;

    public RoomStyle() {
        this.random = new Random(123456789);
        setFloorPictureNames(fillFloorPictureNames());
    }

    private void setTileNameArray(int width, int height) {
        TilesWeightedRendering tiles = new TilesWeightedRendering();
        this.tileNameArray = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tileNameArray[i][j] = tiles.tilesWeighted.getRandom();
            }
        }
    }

    public void fitStyleToRoom(int width, int height){
        setTileNameArray(width, height);
       setRowStyles(fillRowStyles(height));
    }

    protected abstract HashMap<Integer, RowStyle> fillRowStyles(int roomHeight);

    protected abstract ArrayList<String>fillFloorPictureNames();

    public void setSideStyles(String leftSideStyle, String rightSideStyle){
        this.leftSideStyle = leftSideStyle;
        this.rightSideStyle = rightSideStyle;
    }
}

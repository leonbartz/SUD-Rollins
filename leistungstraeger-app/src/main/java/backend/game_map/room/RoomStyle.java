package backend.game_map.room;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class RoomStyle {

    @Getter
    private final HashMap<Integer, RowStyle> rowStyles = new HashMap<>();

    @Getter
    private String leftSiteStyle;

    @Getter
    private String rightSiteStyle;

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
    }

    private void setTileNameArray(int width, int height) {
        this.tileNameArray = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rand = random.nextInt(floorPictureNames.size());
                tileNameArray[i][j] = floorPictureNames.get(rand);
            }
        }
    }

    public void fitStyleToRoom(int width, int height){
        setFloorPictureNames(fillFloorPictureNames());
        setTileNameArray(width, height);
        setRowStyles(height);
    }

    protected abstract void setRowStyles(int roomHeight);

    protected abstract ArrayList<String>fillFloorPictureNames();

    public void setSideStyles(String leftSiteStyle, String rightSiteStyle){
        this.leftSiteStyle = leftSiteStyle;
        this.rightSiteStyle = rightSiteStyle;
    }

    public void addRowStyle(int row, RowStyle style){
        rowStyles.put(row, style);
    }
}

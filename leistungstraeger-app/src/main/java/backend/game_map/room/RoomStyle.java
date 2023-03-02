package backend.game_map.room;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public abstract class RoomStyle {

    @Setter
    private ArrayList<String> floorPictureNames;

    @Getter
    private String[][] tileNameArray;

    /**
     * should be changed to a global random -> injected from top to bottom to be testable
     */
    private Random random;

    public RoomStyle() {
        this.random = new Random(123456789);
    }

    public void setTileNameArray(int width, int height) {
        this.tileNameArray = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rand = random.nextInt(floorPictureNames.size());
                tileNameArray[i][j] = floorPictureNames.get(rand);
            }
        }
    }
}

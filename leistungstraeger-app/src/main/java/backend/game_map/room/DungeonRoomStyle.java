package backend.game_map.room;

import java.util.ArrayList;
import java.util.HashMap;

public class DungeonRoomStyle extends RoomStyle {
    public DungeonRoomStyle() {
        super();
        setSideStyles(
                "wall_side_mid_right.png",
                "wall_side_mid_left.png");
    }

    @Override
    protected HashMap<Integer, RowStyle> fillRowStyles(int roomHeight) {
        HashMap<Integer, RowStyle> rowStyles = new HashMap<>();
        rowStyles.put(-2, new RowStyle(
                "wall_top_left.png",
                "wall_top_right.png",
                "wall_top_mid.png"));
        rowStyles.put(-1, new RowStyle(
                "wall_corner_left.png",
                "wall_corner_right.png",
                "wall_mid.png"));
        rowStyles.put(roomHeight - 1, new RowStyle(
                "wall_corner_bottom_left.png",
                "wall_corner_bottom_right.png",
                "wall_top_mid.png"));
        rowStyles.put(roomHeight, new RowStyle(
                "wall_left.png",
                "wall_right.png",
                "wall_mid.png"));
    return rowStyles;
    }

    @Override
    protected ArrayList<String> fillFloorPictureNames() {
        ArrayList<String> floorPictureNames = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            floorPictureNames.add("floor_" + i + ".png");
        }
        return floorPictureNames;
    }
}


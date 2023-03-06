package backend.game_map.room;

import java.util.ArrayList;

public class DungeonRoomStyle extends RoomStyle {
    public DungeonRoomStyle() {
        super();
        setSideStyles(
                "wall_side_mid_right.png",
                "wall_side_mid_left.png");
    }

    @Override
    protected void setRowStyles(int roomHeight) {
        addRowStyle(-2, new RowStyle(
                "wall_top_left.png",
                "wall_top_right.png",
                "wall_top_mid.png"));
        addRowStyle(-1, new RowStyle(
                "wall_corner_left.png",
                "wall_corner_right.png",
                "wall_mid.png"));
        addRowStyle(roomHeight - 1, new RowStyle(
                "wall_corner_bottom_left.png",
                "wall_corner_bottom_right.png",
                "wall_top_mid.png"));
        addRowStyle(roomHeight, new RowStyle(
                "wall_left.png",
                "wall_right.png",
                "wall_mid.png"));
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


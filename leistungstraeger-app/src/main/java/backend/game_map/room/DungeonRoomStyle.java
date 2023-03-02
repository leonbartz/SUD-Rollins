package backend.game_map.room;

import java.util.ArrayList;

public class DungeonRoomStyle extends RoomStyle{
    public DungeonRoomStyle() {
        super();
        ArrayList<String> floorPictureNames = new ArrayList<>();
        for(int i=1; i<=8; i++){
            floorPictureNames.add("floor_" + i + ".png");
        }
        setFloorPictureNames(floorPictureNames);
    }
}

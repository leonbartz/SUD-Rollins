package backend.game_map.room;

import java.util.ArrayList;

public class TestRoomStyle extends RoomStyle {

    public TestRoomStyle(){
        super();
        ArrayList<String> floorPictureNames = new ArrayList<>();
       floorPictureNames.add("character.png");
        setFloorPictureNames(floorPictureNames);
    }
}

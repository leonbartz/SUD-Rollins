package backend.game_map.room;

import lombok.Getter;

@Getter
public abstract class DoorStyle {
    String sideSprite;
    String topDownSprite;
    String middleSprite;

    public DoorStyle(String sideSprite, String topDownSprite, String middleSprite) {
        this.sideSprite = sideSprite;
        this.topDownSprite = topDownSprite;
        this.middleSprite = middleSprite;
    }
}

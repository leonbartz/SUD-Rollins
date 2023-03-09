package backend.game_map.room;

import lombok.Getter;

@Getter
public class RowStyle {

    public RowStyle(String leftCorner, String rightCorner, String midRow) {
        this.leftCorner = leftCorner;
        this.rightCorner = rightCorner;
        this.midRow = midRow;
    }

    String leftCorner;
    String rightCorner;
    String midRow;
}

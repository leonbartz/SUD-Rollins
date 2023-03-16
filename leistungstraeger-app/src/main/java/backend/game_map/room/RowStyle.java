package backend.game_map.room;

import lombok.Getter;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
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

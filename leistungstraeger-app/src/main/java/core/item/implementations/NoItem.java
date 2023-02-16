package core.item.implementations;

import core.item.Item;

public class NoItem implements Item {
    @Override
    public int getDmgMod() {
        return 0;
    }
}

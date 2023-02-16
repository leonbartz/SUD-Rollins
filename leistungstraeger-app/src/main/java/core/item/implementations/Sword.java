package core.item.implementations;

import core.item.Item;

public class Sword implements Item {
    @Override
    public int getDmgMod() {
        return +1;
    }
}

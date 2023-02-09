package object.interaction;

import item.AbstractItem;
import item.ItemStash;

import java.util.List;
import java.util.UUID;

public interface HasInventory {
    ItemStash getInventory();
}

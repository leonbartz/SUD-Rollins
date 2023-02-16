package object.interaction;

import item.AbstractItem;

import java.util.List;
import java.util.UUID;

public interface HasInventory {

    List<AbstractItem> getInventory();

    AbstractItem removeItem(UUID id);

    List<AbstractItem> removeAllItems();

    void putItem(AbstractItem item);

    void putItems(List<AbstractItem> items);


}

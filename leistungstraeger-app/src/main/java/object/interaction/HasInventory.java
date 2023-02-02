package object.interaction;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface HasInventory {

    List<AbstractItem> getInventory();

    AbstractItem removeItem(UUID id);

    void putItem(AbstractItem object);
}

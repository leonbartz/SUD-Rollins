package backend.item;

import lombok.Getter;

import java.util.UUID;

/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */

public class AbstractItem {

    @Getter
    protected final UUID id;

    @Getter
    protected final String displayName;

    public AbstractItem(final String name) {
        id = UUID.randomUUID();
        displayName = name;
    }

}

package backend.item;

import lombok.Getter;

import java.util.UUID;


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

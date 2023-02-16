package object;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Used for all objects which represent a map-object (like a chess piece). Blocks LOS for players.
 */
@Getter
@Setter
public abstract class AbstractObject {

    private final UUID id;

    private final String name;

    // UUID of only player who can move the object
    private final UUID owner;

    protected AbstractObject(String name, UUID owner) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null.");
        id = UUID.randomUUID();
        this.owner = owner;
        this.name = name;
    }
}

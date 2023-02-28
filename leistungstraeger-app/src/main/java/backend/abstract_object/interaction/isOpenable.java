package backend.abstract_object.interaction;

import java.util.UUID;

/**
 * For doors; object has a UUID for a key, which opens a door.
 */
public interface isOpenable {

    void setKeyId(UUID id);

    boolean isOpen();

    /**
     * opens door, if keyId matches id of assigned Item.
     * @param keyId
     */
    void tryKey(UUID keyId);
}

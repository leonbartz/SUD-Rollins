package object.interaction;

import java.util.UUID;

public interface isOpenable {

    void setKeyId();

    boolean isOpen();

    /**
     * opens door, if keyId matches id of assigned Item.
     * @param keyId
     */
    void tryKey(UUID keyId);
}

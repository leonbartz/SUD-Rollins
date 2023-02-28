package backend.abstract_object.interaction;

import java.util.UUID;

public interface canAttack {

    /**
     * attacks the object which uses this UUID.
     * @param id
     */
    void attack(UUID id);
}

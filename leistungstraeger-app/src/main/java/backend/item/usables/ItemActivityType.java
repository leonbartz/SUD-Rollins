package backend.item.usables;

/**
 * This enum represents, how a {@link AbstractUsableItem} behaves after activation, how many rounds it stays active.
 */
public enum ItemActivityType {

    // Activates and stays active forever
    PERMANENT,

    // Is active for a set number of rounds, then deactivates
    DEACTIVATES_AFTER_COUNT,

    // Used and has effect, deactivates immediately
    IMMEDIATE_EFFECT
}

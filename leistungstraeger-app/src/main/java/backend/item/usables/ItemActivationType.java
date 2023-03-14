package backend.item.usables;

/**
 * This represents the behaviour of activation for a {@link AbstractUsableItem}, meaning how often an item activates
 * and how it can be replenished, if possible. Does not change, HOW the item behaves while active or how it deactivates,
 * only the way it activates.
 */
public enum ItemActivationType {
    // Activate once, then its empty and discarded
    SINGLE_USE,

    // Activate, automatically refills after a set amount of turns
    COUNT_ROUNDS,

    // Activate, refills as soon as triggered (e. g. healing, resting, etc.)
    ON_REACTIVATION
}

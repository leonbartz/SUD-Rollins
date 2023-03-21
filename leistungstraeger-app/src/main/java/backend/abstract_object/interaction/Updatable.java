package backend.abstract_object.interaction;

/**
 * For all POJOs with ActiveEffectList -> GameLoop in {@class Game#update()} can access the updateMethod of
 * every ActiveEffectList currently in game.
 */
public interface Updatable {

    void updateInventory();

}

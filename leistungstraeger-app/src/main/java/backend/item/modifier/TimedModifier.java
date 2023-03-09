package backend.item.modifier;
/**
 * Used to keep track of how many turns a modifier should be active
 */
public record TimedModifier(Modifier modifier, int turns) {
}

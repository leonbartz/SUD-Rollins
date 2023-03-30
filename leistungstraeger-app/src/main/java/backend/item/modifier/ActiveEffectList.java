package backend.item.modifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all effects an object is experiencing in a given turn. Manages updating the
 * effects and aggregating multiple simultaneous effects for the same identifier.
 * <p>
 * Should only be used for actual, active effects on an actual object.
 * <p>
 * Not for just holding effects that are not active! The effects in this list will be
 * counted down and removed!
 */
public class ActiveEffectList {

    private final ArrayList<TimedModifier> activeModifiers = new ArrayList<>();

    public ActiveEffectList(TimedModifier... modifiers) {
        activeModifiers.addAll(List.of(modifiers));
    }

    public ActiveEffectList(List<TimedModifier> modifiers) {
        activeModifiers.addAll(modifiers);
    }

    public void add(TimedModifier effect) {
        activeModifiers.add(effect);
    }

    public void addAll(TimedModifier... effects) {
        activeModifiers.addAll(List.of(effects));
    }

    /**
     * Returns the calculated modification to stats for all currently possessed objects.
     *
     * @param identifier - {@link ModifierIdentifier}
     * @return - {@class double} value of overall modification
     */
    public double getValueForIdentifier(final ModifierIdentifier identifier) {
        final List<TimedModifier> allWithIdentifier = activeModifiers.stream()
                                                                     .filter(timedModifier -> timedModifier
                                                                             .getModifier()
                                                                             .identifier()
                                                                             .equals(identifier))
                                                                     .toList();

        final List<TimedModifier> identifierAndStillTurns = allWithIdentifier.stream()
                                                                             .filter(timedModifier -> timedModifier.getTurns() > 0)
                                                                             .toList();

        return identifierAndStillTurns.stream()
                                      .map(timedModifier -> timedModifier.getModifier().value())
                                      .reduce(Double::sum)
                                      .orElse(0.0);
    }

    public void update() {
        activeModifiers.forEach(TimedModifier::update);
        // Remove all modifiers where time is up
        activeModifiers.removeAll(activeModifiers.stream()
                                                 .filter(modifier -> modifier.getTurns() == 0)
                                                 .toList());
    }
}

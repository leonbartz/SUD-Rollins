package backend.item.modifier;

import java.util.ArrayList;
import java.util.List;

public class ActiveEffectList {

    private final ArrayList<TimedModifier> activeModifiers = new ArrayList<>();

    public ActiveEffectList(TimedModifier... modifiers) {
    activeModifiers.addAll(List.of(modifiers));
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
     * @return - {@link double} value of overall modification
     */
    public double getValueForModifier(final ModifierIdentifier identifier) {
        final List<TimedModifier> result = activeModifiers.stream()
                                                          .filter(timedModifier -> timedModifier
                                                                  .modifier()
                                                                  .identifier()
                                                                  .equals(identifier))
                                                          .filter(timedModifier -> timedModifier.turns() > 0)
                                                          .toList();

        return result.stream()
                     .map(timedModifier -> timedModifier.modifier().value())
                     .reduce(Double::sum)
                     .orElse(0.0);
    }

    private void updateValues() {
        // Remove all modifiers where time is up
        activeModifiers.removeAll(activeModifiers.stream()
                                                 .filter(modifier -> modifier.turns() == 0)
                                                 .toList());
    }

    public void update() {
        updateValues();
    }
}

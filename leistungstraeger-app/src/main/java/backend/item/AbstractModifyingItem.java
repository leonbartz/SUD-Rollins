package backend.item;

import backend.item.modifier.ModifierIdentifier;
import backend.item.modifier.TimedModifier;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AbstractItem with actual values, basically any item in game.
 * <p>
 * Separation due to items like Keys, which do not have any modifiers. These would be AbstractItems.
 */
public class AbstractModifyingItem extends AbstractItem {

    @Getter
    protected final ArrayList<TimedModifier> activeModifiers = new ArrayList<>();

    public AbstractModifyingItem(String name) {
        super(name);
    }

    public AbstractModifyingItem(final String name, final TimedModifier... modifiers) {
        super(name);
        //Add all modifiers
        activeModifiers.addAll(Arrays.asList(modifiers));
    }

    //TODO iwie muss das strukturell anders gehen, dass ItemStash und Item diesen Vorgang von einer Überklasse bekommen kp
    public double getModifierByIdentifier(final ModifierIdentifier identifier) {
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
}

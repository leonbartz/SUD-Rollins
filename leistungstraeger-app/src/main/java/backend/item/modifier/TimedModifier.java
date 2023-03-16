package backend.item.modifier;

import lombok.Getter;

/**
 * Used to keep track of how many turns a modifier should be active
 */
public class TimedModifier {

    @Getter
    private final Modifier modifier;

    @Getter
    private int turns;

    public TimedModifier(final Modifier modifier, final int turns) {
        this.modifier = modifier;
        this.turns = turns;
    }

    public void update() {
        turns--;
    }
}

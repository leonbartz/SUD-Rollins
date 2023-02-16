package core.object.implementation;

import core.item.AbstractItem;
import core.item.implementations.NoItem;
import core.client.Client;
import core.item.modifier.ModifierIdentifier;
import core.object.AbstractObject;
import helpers.command.AttackCommand;
import helpers.command.GameCommand;
import helpers.command.MoveCommand;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

public class GameCharacter extends Combatable {

    @Getter
    private final Client client;

    @Getter
    @Setter
    private AbstractItem item;

    public GameCharacter(final Client client,
                         final String name,
                         final Coordinate position,
                         final String spriteName,
                         final int maxMovingRange,
                         final double maxHitpoints,
                         final double baseDamage) {
        super(name, spriteName, position, maxHitpoints, baseDamage, maxMovingRange);
        this.client = client;
        setItem(new NoItem("Markus"));
    }

    @Override
    public double getDamage() {
        //TODO all values as double
        return baseDamage + item.getModifierByIdentifier(ModifierIdentifier.ATTACK);
    }

    public GameCommand interact(AbstractObject target, Client source, Coordinate mousePos) {
        if (!isAlive()) {
            return null;
        }

        if (target == null) {
            if (Coordinate.inRange(mousePos, getPosition(), 1)) {
                return new MoveCommand(source, this, mousePos);
            }
        } else if (!target.equals(this) && target instanceof Combatable combatObject) {
            if (Coordinate.inRange(mousePos, getPosition(), 1)) {
                return new AttackCommand(source, this, combatObject);
            }
        }

        return null;
    }
}

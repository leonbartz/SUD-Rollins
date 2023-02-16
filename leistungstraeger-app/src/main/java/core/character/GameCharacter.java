package core.character;

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

    public GameCharacter(Client client,
                         String name,
                         Coordinate position,
                         String spriteName,
                         int maxHitpoints,
                         int baseDamage) {
        super(name, spriteName, position, maxHitpoints, baseDamage);
        this.client = client;
        setItem(new NoItem("Markus"));
    }

    @Override
    public int getDamage() {
        //TODO all values as double
        return (int) Math.round(baseDamage + item.getModifierByIdentifier(ModifierIdentifier.ATTACK));
    }

    public GameCommand interact(AbstractObject target, Client source, Coordinate mousePos) {
        if (isAlive()) {
            if (target == null) {
                if (Coordinate.inRange(mousePos, getPosition(), 1)) {
                    return new MoveCommand(source, this, mousePos);
                }
            } else if (!target.equals(this) && target instanceof Combatable combatObject) {
                if (Coordinate.inRange(mousePos, getPosition(), 1)) {
                    return new AttackCommand(source, this, combatObject);
                }
            }
        }
        return null;
    }
}

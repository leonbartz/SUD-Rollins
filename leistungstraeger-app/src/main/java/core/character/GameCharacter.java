package core.character;

import core.Item.Item;
import core.Item.NoItem;
import core.client.Client;
import helpers.command.AttackCommand;
import helpers.command.GameCommand;
import helpers.command.MoveCommand;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

public class GameCharacter extends Combatable {

    @Getter
    private final long clientId;

    @Getter
    @Setter
    private Item item;

    public GameCharacter(long clientId, Coordinate position, String spriteName, int maxHitpoints, int baseDamage) {
        super(spriteName, position, maxHitpoints, baseDamage);
        this.clientId = clientId;
        setItem(new NoItem());
    }

    @Override
    public int getDamage() {
        return baseDamage + item.getDmgMod();
    }


    public GameCommand interact(GameObject target, Client source, Coordinate mousePos) {
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

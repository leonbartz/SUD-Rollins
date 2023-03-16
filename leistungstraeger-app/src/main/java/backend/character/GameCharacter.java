package backend.character;

import backend.abstract_object.interaction.Interactable;
import backend.network.client.Client;
import backend.item.AbstractModifyingItem;
import backend.item.implementations.NoItem;
import backend.item.modifier.ModifierIdentifier;
import backend.abstract_object.Combatable;
import helpers.command.CommandInfoDto;
import helpers.command.GameCommand;
import helpers.command.MoveCommand;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public class GameCharacter extends Combatable {

    @Getter
    private final Client client;

    @Getter
    @Setter
    private AbstractModifyingItem item;

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
        return baseDamage + item.getModifierByIdentifier(ModifierIdentifier.DAMAGE);
    }

    /**
     * Removes hitpoints based on incoming attack, after removing defence points from incoming value
     * @param damage - enemy damage value
     */
    @Override
    public void defend(final double damage) {
        double hpWithDefence = hitpoints + item.getModifierByIdentifier(ModifierIdentifier.DEFENCE);
        hitpoints = Math.max(0, hpWithDefence - damage);
    }

    public GameCommand checkInteractions(CommandInfoDto dto) {
        Coordinate targetPos = dto.getTarget() != null ? dto.getTarget().getPosition() : dto.getMouseClickPos();
        if (isAlive() && Coordinate.inRange(getPosition(), targetPos, 1)) {
            if (dto.getTarget() == null) {
                return new MoveCommand(dto.getClient(), this, dto.getMap().getActiveRoom(), dto.getMouseClickPos());
            } else if (dto.getTarget() instanceof Interactable interactable) {
                return interactable.interact(dto);
            }
        }
        return null;
    }
}

package backend.abstract_object;

import backend.abstract_object.interaction.Interactable;
import backend.item.ItemStash;
import backend.item.modifier.ActiveEffectList;
import backend.item.modifier.TimedModifier;
import helpers.command.AttackCommand;
import helpers.command.CommandInfoDto;
import helpers.command.GameCommand;
import helpers.coordinate.Coordinate;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public abstract class Combatable extends MovingAbstractObject implements Interactable {

    @Getter
    @Setter
    protected double hitpoints;

    @Getter
    @Setter
    protected double maxHitpoints;

    protected double baseDamage;

    private final ItemStash inventory = new ItemStash();

    protected final ActiveEffectList activeModifiers = new ActiveEffectList();

    public Combatable(final String name,
                      final String spriteName,
                      final Coordinate position,
                      final double baseDamage,
                      final int maxMovingDistance) {
        super(name, UUID.randomUUID(), maxMovingDistance, spriteName, position);
        this.baseDamage = baseDamage;
    }

    /**
     * Removes hitpoints based on incoming attack.
     *
     * @param damage - enemy damage value
     */
    public abstract void defend(final double damage);

    public abstract double getDamage();

    public boolean isAlive() {
        return hitpoints > 0;
    }

    @Override
    public GameCommand interact(CommandInfoDto dto) {
        if (dto.getSource() != this) {
            return new AttackCommand(dto.getClient(), dto.getSource(), this);
        }
        return null;
    }

    public void applyEffect(TimedModifier effect) {
        activeModifiers.add(effect);
    }
}

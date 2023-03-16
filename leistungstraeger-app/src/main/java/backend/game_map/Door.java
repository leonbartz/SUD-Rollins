package backend.game_map;

import backend.abstract_object.interaction.Interactable;
import backend.abstract_object.AbstractObject;
import helpers.command.ChangeRoomCommand;
import helpers.command.CommandInfoDto;
import helpers.command.GameCommand;
import helpers.coordinate.CardinalDirection;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Door extends AbstractObject implements Interactable, Renderable {

    private Door linkedDoor;
    private final Coordinate exitPosition;

    public Door(String spriteName, Coordinate position, CardinalDirection exitDirection) {
        super("door", UUID.randomUUID(), spriteName, position);
        this.exitPosition = new Coordinate(
                position.getXPos() + exitDirection.getXMod(),
                position.getYPos() + exitDirection.getYMod());
    }

    public static void linkDoors(Door door1, Door door2) {
        door1.setLinkedDoor(door2);
        door2.setLinkedDoor(door1);
    }

    @Override
    public GameCommand interact(CommandInfoDto dto) {
        GameMap gameMap = dto.getMap();
        return new ChangeRoomCommand(
                dto.getClient(),
                dto.getSource(),
                gameMap,
                gameMap.getObjectRoom(this),
                gameMap.getObjectRoom(linkedDoor),
                linkedDoor.getExitPosition()
        );
    }
}

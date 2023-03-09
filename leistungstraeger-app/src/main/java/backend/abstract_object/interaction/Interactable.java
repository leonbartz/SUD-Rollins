package backend.abstract_object.interaction;

import helpers.command.CommandInfoDto;
import helpers.command.GameCommand;

public interface Interactable {
    GameCommand interact(CommandInfoDto dto);
}

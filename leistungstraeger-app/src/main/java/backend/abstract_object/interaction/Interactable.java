package backend.abstract_object.interaction;

import helpers.command.CommandInfoDto;
import helpers.command.GameCommand;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public interface Interactable {
    GameCommand interact(CommandInfoDto dto);
}

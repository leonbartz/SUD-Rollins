package helpers.command;

import backend.abstract_object.AbstractObject;
import backend.character.GameCharacter;
import backend.game.Turn;
import backend.game_map.GameMap;
import backend.network.client.Client;
import helpers.coordinate.Coordinate;
import lombok.Getter;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
@Getter
public class CommandInfoDto {
    private final GameCharacter source;
    private final Turn turn;
    private final AbstractObject target;
    private final Client client;
    private final GameMap map;
    private final Coordinate mouseClickPos;

    public CommandInfoDto(GameCharacter source, Turn turn, AbstractObject target, Client client, GameMap map, Coordinate mouseClickPos) {
        this.source = source;
        this.turn = turn;
        this.target = target;
        this.client = client;
        this.map = map;
        this.mouseClickPos = mouseClickPos;
    }
}

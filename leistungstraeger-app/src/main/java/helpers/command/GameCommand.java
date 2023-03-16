package helpers.command;

import backend.network.client.Client;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public abstract class GameCommand {

    private final Client source;

    public abstract void doCommand();

    public GameCommand(Client source) {
        this.source = source;
    }

    public long getSourceId() {
        return source.getId();
    }
}

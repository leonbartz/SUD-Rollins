package helpers.command;

import core.client.Client;
import core.client.ClientSocket;

public class CommandManager {

    private final GameCommandSocket commandSocket;
    private final Client client;

    public CommandManager(Client client) {
        this.client = client;
        this.commandSocket = new GameCommandSocket();
    }

    public void consumeCommand() {
        commandSocket.consumeCommand();
    }

    public void receiveCommand(GameCommand command) {
        if (command != null && command.getSourceId() == client.getId()) {
            commandSocket.setCommand(command);
        }
    }

    private void distributeCommand() {
        //
    }
}

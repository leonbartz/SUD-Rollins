package helpers.command;

import backend.network.client.Client;
import lombok.Setter;

public class CommandManager {

    private final GameCommandSocket commandSocket;
    @Setter
    private Client client;

    public CommandManager() {
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

package helpers.command;

public class GameCommandSocket {
    private GameCommand gameCommand;

    public void setCommand(GameCommand command) {
        this.gameCommand = command;
    }

    public void consumeCommand() {
        if (gameCommand != null) {
            gameCommand.doCommand();
            gameCommand = null;
        }
    }
}

package helpers.command;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
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

package frontend.renderbehaviour;

import backend.game.Game;
import backend.item.Inventory;
import backend.network.client.Client;
import helpers.command.EndTurnCommand;
import helpers.coordinate.Coordinate;
import helpers.view.Renderable;

import java.awt.event.KeyEvent;

public class InventoryRenderBehaviour extends RenderBehaviour {

    private final Inventory inventory;

    public InventoryRenderBehaviour(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Renderable getRenderable() {
        return inventory;
    }

    @Override
    public void handleMouseClick(Game game) {}

    @Override
    public void handleKeyboard(Game game) {
        if (game.getKeyHandler().isKeyPressed(KeyEvent.VK_ESCAPE)) {
            game.endGame();
        }
        if (game.getKeyHandler().isKeyPressed(KeyEvent.VK_M)) {
            game.getRenderBehaviourManager().setRenderBehaviour(RenderBehaviourManager.RenderMode.MAP);
        }
    }
}

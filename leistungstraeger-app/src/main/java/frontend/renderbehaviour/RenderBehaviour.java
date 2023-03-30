package frontend.renderbehaviour;

import backend.game.Game;
import helpers.view.Renderable;

public abstract class RenderBehaviour {
    public abstract Renderable getRenderable();
    public abstract void handleMouseClick(Game game);
    public abstract void handleKeyboard(Game game);
}

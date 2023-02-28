package frontend.view;

import helpers.view.Renderable;
import helpers.view.ViewTransformation;

import java.awt.*;

public interface View {
    void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable);
}

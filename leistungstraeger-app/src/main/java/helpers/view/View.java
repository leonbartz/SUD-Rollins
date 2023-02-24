package helpers.view;

import java.awt.*;

public interface View {
    void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable);
}

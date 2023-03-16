package frontend.view;

import helpers.view.Renderable;
import helpers.view.ViewTransformation;

import java.awt.*;
/*
@author: Carl, Eric, Jacob, Jasper, Leon, Sven
 */
public interface View {
    void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable);
}

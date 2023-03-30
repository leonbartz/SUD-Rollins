package frontend.view;


import backend.item.Inventory;
import helpers.view.Renderable;
import helpers.view.ViewTransformation;

import javax.swing.*;
import java.awt.*;

public class InventoryView implements View {
    @Override
    public void render(Graphics2D g2D, ViewTransformation viewTransformation, Renderable renderable) {
        Inventory inventory = (Inventory) renderable;
        g2D.setColor(Color.PINK);
        g2D.fillRect(0, 0, 500, 500);
    }
}

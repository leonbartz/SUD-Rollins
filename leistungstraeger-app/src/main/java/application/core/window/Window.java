package application.core.window;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Window {
    @Getter
    private final JPanel windowPanel;

    public Window(int x, int y) {
        windowPanel = new JPanel();
        windowPanel.setPreferredSize(new Dimension(x, y));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(windowPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

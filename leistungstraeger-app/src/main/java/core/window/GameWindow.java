package core.window;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameWindow {

    @Getter
    private final JPanel windowPanel;
    private final JFrame gameFrame;

    public GameWindow(int width, int height) {
        windowPanel = new JPanel();
        windowPanel.setBackground(Color.BLACK);
        windowPanel.setLayout(new BorderLayout());
        gameFrame = new JFrame();
        gameFrame.setResizable(false);
        gameFrame.setSize(new Dimension(width, height));
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.add(windowPanel);
    }

    public GameWindow() {
        windowPanel = new JPanel();
        windowPanel.setBackground(Color.BLACK);
        windowPanel.setLayout(new BorderLayout());
        gameFrame = new JFrame();
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.add(windowPanel);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setUndecorated(true);
    }

    public Dimension getSize() {
        return gameFrame.getSize();
    }

    public void show() {
        gameFrame.setVisible(true);
    }

    public void addKeyListener(KeyListener keyListener) {
        gameFrame.addKeyListener(keyListener);
    }

    public void closeWindow() {
        gameFrame.dispose();
    }
}

package helpers.keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class KeyboardHandler extends KeyAdapter {

    private final HashSet<KeyEvent> pressedKeys;

    public KeyboardHandler() {
        this.pressedKeys = new HashSet<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e);
    }

    public boolean isKeyPressed(int key) {
        if (!pressedKeys.isEmpty()) {
            for (KeyEvent keyEvent : pressedKeys) {
                if (keyEvent.getKeyCode() == key) {
                    pressedKeys.remove(keyEvent);
                    return true;
                }
            }
        }
        return false;
    }
}

package star.odyssey.ui.swing;

import star.odyssey.ui.swing.callbacks.CallBackString;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class KeyEventListener implements KeyListener {
    private CallBackString consoleCallbackString;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        consoleCallbackString.callback("go north");
                    case KeyEvent.VK_DOWN:
                        consoleCallbackString.callback("go south");
                    case KeyEvent.VK_LEFT:
                        consoleCallbackString.callback("go west");
                    case KeyEvent.VK_RIGHT:
                        consoleCallbackString.callback("go east");
                    case KeyEvent.VK_ENTER:
                        consoleCallbackString.callback("");
                }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
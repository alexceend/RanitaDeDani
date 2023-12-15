package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] keys = new boolean[256];
    public static boolean SA1;
    public static boolean KILL;

    public Keyboard(){
        SA1 = false;
        KILL = false;
    }

    public void update(){
        SA1 = keys[KeyEvent.VK_Q];
        KILL = keys[KeyEvent.VK_P];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}

package input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    public static boolean CLICKING;

    public Mouse() {
        CLICKING = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        CLICKING = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        CLICKING = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        CLICKING = false;
    }

    public static Point getPos() {
        //return e.getX();
        //return x;
        return MouseInfo.getPointerInfo().getLocation().getLocation();
    }

}

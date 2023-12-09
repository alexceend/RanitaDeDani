package input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    public static boolean CLICK;
    public static boolean CLICKED;
    protected static double x, y;

    public Mouse() {
        CLICK = false;
    }

    public void update() {
        CLICK = CLICKED;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        CLICKED = true;
        //x = e.getX();
        //y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        CLICKED = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        CLICKED = false;
    }

    public static double getPosX() {
        //return e.getX();
        //return x;
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    public static double getPosY() {
        //return e.getY();
        //return y;
        return MouseInfo.getPointerInfo().getLocation().y;
    }
}

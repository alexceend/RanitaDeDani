package input;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

    public static boolean CLICK;
    public static boolean CLICKED;
    public static double x, y;

    public Mouse() {
        CLICK = false;
    }

    public void update() {
        x = getPosX();
        y = getPosY();
        CLICK = CLICKED;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        CLICKED = true;
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

    public double getPosX(){
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    public double getPosY(){
        return MouseInfo.getPointerInfo().getLocation().y;
    }
}

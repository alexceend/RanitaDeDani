package gameObjects;

import clases.Window;
import graphics.Assets;
import input.Mouse;
import math.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject {

    final Canvas canvas = Window.instance.getCanvas();
    public Player(Point center, BufferedImage texture) {
        super(center, new Vector2D(0, 1), texture);
    }

    @Override
    public void update() {
        Point mousePoint = new Point((int) Mouse.getPosX(), (int) Mouse.getPosY());
        SwingUtilities.convertPointFromScreen(mousePoint, canvas); // Converts the screen position to the actual relative position of canvas
        // NOT USE window because the white stuff in top of the window is also taken into account, so there will be a small "bug"
        direction = Vector2D.fromLocations(center, mousePoint);

        //angle = direction.getAngle();

        //System.out.println("ANGLE: "+angle);


        if (Mouse.CLICK) {
            //position.setX(position.getX() + 1);
        }
    }

    @Override
    public void draw(Graphics g) {
        Point mousePoint = new Point((int) Mouse.getPosX(), (int) Mouse.getPosY());
        SwingUtilities.convertPointFromScreen(mousePoint, canvas);

        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) Assets.player.getWidth() / 2, center.getY() - (double) Assets.player.getHeight() / 2);
        double angle = mousePoint.getY() > 400 ?
                direction.getAngle() + (Math.PI/2) :
                -1 * (direction.getAngle() + (1.5*Math.PI));
        at.rotate(angle,(double) Assets.player.getWidth() / 2, (double) Assets.player.getWidth() / 2);

        g.setColor(Color.white);
        //g.drawLine((int) position.getX(), (int) position.getY(), (int) Mouse.getPosX(), (int) Mouse.getPosY());
        ((Graphics2D) g).drawImage(Assets.player, at, null);
        //g2d.drawImage(texture, (int) position.getX(), (int) position.getY(), null);
    }
}

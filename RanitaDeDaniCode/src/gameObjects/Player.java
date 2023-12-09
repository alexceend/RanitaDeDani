package gameObjects;

import graphics.Assets;
import input.Mouse;
import math.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject {

    private Vector2D heading;
    private Vector2D direction;

    public Player(Vector2D position, Vector2D velocity, BufferedImage texture) {
        super(position, velocity, texture);
        heading = new Vector2D(0, 1);
        direction = new Vector2D(0, 1);
    }

    @Override
    public void update() {
        direction.setX(Mouse.getPosX() - (position.getX() + (double) Assets.player.getWidth() / 2));
        direction.setY(Mouse.getPosY() - (position.getY() + (double) Assets.player.getHeight() / 2));

        //angle = direction.getAngle();

        angle = Math.asin((400 - Mouse.getPosY() - this.getPosition().getY()) / (Math.sqrt(Math.pow(400 - Mouse.getPosY() - this.getPosition().getY(), 2)
                + Math.pow(Mouse.getPosX() - this.position.getX() - 400, 2))));

        heading = heading.setDirection(angle);

        if (Mouse.CLICK) {
            //position.setX(position.getX() + 1);
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
        at.rotate(angle, Assets.player.getWidth() / 2, Assets.player.getHeight() / 2);
        g.setColor(Color.white);
        g.drawLine((int) position.getX(), (int) position.getY(), (int) direction.getX(), (int) direction.getY());
        g2d.drawImage(Assets.player, at, null);
        //g2d.drawImage(texture, (int) position.getX(), (int) position.getY(), null);
    }
}

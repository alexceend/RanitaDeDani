package gameObjects;

import graphics.Assets;
import input.Mouse;
import math.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject {

    private Vector2D heading = new Vector2D();
    private Vector2D direction = new Vector2D();

    public Player(Vector2D position, Vector2D velocity, BufferedImage texture) {
        super(position, velocity, texture);
    }

    @Override
    public void update() {
        direction.setX(Mouse.x - (position.getX() + Assets.player.getWidth() / 2));
        direction.setY(Mouse.y - (position.getY() + Assets.player.getHeight() / 2));

        angle = direction.getAngle();

        heading = heading.setDirection(angle - Math.PI/2);

        if (Mouse.CLICK) {
            position.setX(position.getX() + 1);
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

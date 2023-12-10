package gameObjects;

import input.Mouse;
import math.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tongue extends MovingObject {
    public Tongue(Point position, Vector2D unitaryDirection, BufferedImage texture) {
            super(position, unitaryDirection.multiply(10), texture);
    }

    @Override
    public void update() {
        this.center.translate((int) direction.getX(), (int) direction.getY());
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) texture.getWidth() / 2, center.getY() - (double) texture.getHeight() / 2);
        double angle = direction.getActualAngle(Mouse.getPos());
        at.rotate(angle, (double) texture.getWidth() / 2, (double) texture.getWidth() / 2);
        g2d.drawImage(texture, at, null);
    }
}

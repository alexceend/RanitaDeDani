package gameObjects;

import math.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tongue extends MovingObject {
    private int velocity;
    private AffineTransform at;
    private Vector2D pos;


    public Tongue(Point center, Vector2D direction, BufferedImage texture, int velocity, AffineTransform at) {
        super(center, direction, texture);
        this.velocity = velocity;
        this.at = at;
        this.pos = new Vector2D(center.getX(), center.getY());
    }

    @Override
    public void update() {
        this.pos = pos.add(velocity);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        at = AffineTransform.getTranslateInstance(pos.getX(), pos.getY());

        g2d.drawImage(texture, at, null);

    }
}

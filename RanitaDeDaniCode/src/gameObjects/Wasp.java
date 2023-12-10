package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Wasp extends MovingObject{

    private double angle;

    public Wasp(Point center, Vector2D UnitaryDirection, BufferedImage texture, GameState gameState) {
        super(center, UnitaryDirection.multiply(Constants.WASP_VEL), texture, gameState);
        angle = direction.getActualAngle(center);
    }

    @Override
    public void update() {
        this.center.translate((int) direction.getX(), (int) direction.getY());

        collidesWith();
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) texture.getWidth() / 2, center.getY() - (double) texture.getHeight() / 2);
        at.rotate((angle), (double) texture.getWidth() / 2, (double) texture.getWidth() / 2);

        g2d.drawImage(texture, at, null);
    }
}

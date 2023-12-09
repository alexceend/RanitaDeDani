package gameObjects;

import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MovingObject extends GameObject {
    protected Vector2D direction;
    public MovingObject(Point center, Vector2D direction, BufferedImage texture) {
        super(center, texture);
        this.direction = direction;
    }
}

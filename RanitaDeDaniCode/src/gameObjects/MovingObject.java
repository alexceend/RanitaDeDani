package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public abstract class MovingObject extends GameObject {
    protected Vector2D direction;
    protected GameState gameState;
    public int width;
    public int height;

    public MovingObject(Point center, Vector2D direction, BufferedImage texture, GameState gameState) {
        super(center, texture);
        this.direction = direction;
        this.gameState = gameState;
        height = texture.getHeight();
        width = texture.getWidth();
    }
}

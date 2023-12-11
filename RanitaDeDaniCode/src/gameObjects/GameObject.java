package gameObjects;

import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    protected BufferedImage texture;
    protected Point center;

    public GameObject(Point center, BufferedImage texture){
        this.center = center;
        this.texture = texture;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void remove(){
        GameState.movingObjects.remove(this);
    }
}

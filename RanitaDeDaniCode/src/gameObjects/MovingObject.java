package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public abstract class MovingObject extends GameObject {
    protected Vector2D direction;
    protected GameState gameState;
    protected int width;
    protected int height;

    public MovingObject(Point center, Vector2D direction, BufferedImage texture, GameState gameState) {
        super(center, texture);
        this.direction = direction;
        this.gameState = gameState;
        height = texture.getHeight();
        width = texture.getWidth();
    }

    protected void collidesWith() {
        HashSet<MovingObject> movingObjects = gameState.getMovingObjects();
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            MovingObject m = mo;
            if (mo.equals(this)) continue;

            double distance = Math.sqrt(Math.pow(mo.getCenter().getX() - this.getCenter().getX(), 2) +
                    Math.pow(mo.getCenter().getY() - this.getCenter().getY(), 2));

            if(distance < mo.width/2 + mo.height/2 && movingObjects.contains(this)){
                objectCollision(m,this);
            }
        }
    }

    private void objectCollision(MovingObject a, MovingObject b){
        if(!(a instanceof Fly && b instanceof Fly)){
            a.Destroy();
            b.Destroy();
        }/* else if (!((a instanceof Player || a instanceof Fly) && (a instanceof Player || a instanceof Fly))) {
            a.Destroy();
            b.Destroy();
        }*/
    }

    protected void Destroy(){
        gameState.getMovingObjects().remove(this);
    }


}

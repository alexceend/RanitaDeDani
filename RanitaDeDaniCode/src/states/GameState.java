package states;

import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class GameState {

    private final HashSet<MovingObject> movingObjects = new HashSet<>();

    public GameState(){
        movingObjects.add(
                new Player(new Point(400, 400), Assets.player, this));
    }

    public void update(){
        for(MovingObject mo: new HashSet<>(movingObjects)) mo.update();
    }

    public void draw(Graphics g){
        for(MovingObject mo: new HashSet<>(movingObjects)) mo.draw(g);
    }

    public HashSet<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}

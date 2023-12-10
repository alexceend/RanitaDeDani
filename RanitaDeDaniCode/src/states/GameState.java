package states;

import gameObjects.MovingObject;
import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    private final Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();

    public GameState(){
        player = new Player(new Point(400, 400), Assets.player, this);
        movingObjects.add(player);
    }

    public void update(){
        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
    }

    public void draw(Graphics g){
        for(int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).draw(g);
        }
    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}

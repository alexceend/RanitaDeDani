package states;

import clases.Window;
import gameObjects.MovingObject;
import gameObjects.Player;
import gameObjects.Tongue;
import graphics.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class GameState {
    private final Player player
            = new Player(new Point(400, 400), Assets.player, this);
    private final HashSet<MovingObject> movingObjects = new HashSet<>();
    
    public void update(){
        player.update();
        for(MovingObject mo: new HashSet<>(movingObjects)) {
            mo.update();
            if(mo instanceof Tongue && isPosOutsideComponent(mo.getCenter())) {
                movingObjects.remove(mo);
            }
        }
    }

    public void draw(Graphics g){
        player.draw(g);
        for(MovingObject mo: new HashSet<>(movingObjects)) mo.draw(g);
    }

    private boolean isPosOutsideComponent(Point pos) {
        return pos.getX() > 800 || pos.getX() < 0 || pos.getY() > 800 || pos.getY() < 0;
    }

    public HashSet<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}

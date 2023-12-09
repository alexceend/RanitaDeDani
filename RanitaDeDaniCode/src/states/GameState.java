package states;

import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;

public class GameState {

    private final Player player;

    public GameState(){
        player = new Player(new Point(400, 400), Assets.player);
    }

    public void update(){
        player.update();
    }

    public void draw(Graphics g){
        player.draw(g);
    }

}

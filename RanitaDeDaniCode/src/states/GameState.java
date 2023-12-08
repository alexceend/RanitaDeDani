package states;

import gameObjects.Player;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;

public class GameState {

    private Player player;

    public GameState(){
        player = new Player(new Vector2D(400,400), new Vector2D(), Assets.player);
    }

    public void update(){
        player.update();
    }

    public void draw(Graphics g){
        player.draw(g);
    }

}

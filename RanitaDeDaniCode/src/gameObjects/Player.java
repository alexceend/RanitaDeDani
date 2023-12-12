package gameObjects;

import clases.Window;
import graphics.Assets;
import input.Mouse;
import math.Vector2D;
import states.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashSet;

public class Player extends MovingObject {

    private final Canvas canvas = Window.instance.getCanvas();
    private long lastTime = System.currentTimeMillis();
    public static int numBullets = 5;
    public static int numVidas = 3;
    private long lastRecharge = System.currentTimeMillis();

    public Player(Point center, BufferedImage texture, GameState gameState) {
        super(center, new Vector2D(0, 1), texture, gameState);
    }

    @Override
    public void update() {
        Point mousePoint = Mouse.getPos();
        SwingUtilities.convertPointFromScreen(mousePoint, canvas); // Converts the screen position to the actual relative position of canvas
        // NOT USE window because the white stuff in top of the window is also taken into account, so there will be a small "bug"
        direction = Vector2D.fromLocations(center, mousePoint);

        //angle = direction.getAngle();

        //System.out.println("ANGLE: "+angle);

        if (Mouse.CLICKING && (System.currentTimeMillis() - lastTime) > Constants.FIRERATE) {
            if (numBullets > 0) {
                gameState.getMovingObjects().add(new Ball(this.center.getLocation(), this.direction.toUnitary(), Assets.ball, this.gameState));
                lastTime = System.currentTimeMillis();
                numBullets--;
            }
        }

        if((System.currentTimeMillis() - lastRecharge) > 3000){
            lastRecharge = System.currentTimeMillis();
            numBullets++;
        }

    }

    @Override
    public void draw(Graphics g) {
        Point mousePoint = Mouse.getPos();
        SwingUtilities.convertPointFromScreen(mousePoint, canvas);

        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) texture.getWidth() / 2, center.getY() - (double) texture.getHeight() / 2);
        at.rotate(direction.getActualAngle(mousePoint),
                (double) texture.getWidth() / 2, (double) texture.getWidth() / 2);

        //g.drawLine((int) position.getX(), (int) position.getY(), (int) Mouse.getPosX(), (int) Mouse.getPosY());
        if(gameState.getMovingObjects().contains(this)) ((Graphics2D) g).drawImage(texture, at, null);
        //g2d.drawImage(texture, (int) position.getX(), (int) position.getY(), null);

    }
}

package gameObjects;

import clases.Window;
import graphics.Assets;
import input.Keyboard;
import input.Mouse;
import math.Vector2D;
import states.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject {

    private final Canvas canvas = Window.instance.getCanvas();
    private long lastTime = System.currentTimeMillis();
    public static int numBullets = 5;
    public static int numVidas = 3;
    private long lastRecharge = System.currentTimeMillis();
    private long lastTimeSA1 = System.currentTimeMillis();

    public static boolean invincible, visible, trullyInvincible;
    private Chronometer invincibleTime, flickerTime;

    private Sound shoot;

    public Player(Point center, BufferedImage texture, GameState gameState) {
        super(center, new Vector2D(0, 1), texture, gameState);
        invincibleTime = new Chronometer();
        flickerTime = new Chronometer();
        shoot = new Sound(Assets.playerShoot);
    }

    public boolean isSA1Available(){
        return ((System.currentTimeMillis() - lastTimeSA1) > Constants.SA1FIRERATE);
    }
    @Override
    public void update() {
        if(Player.invincible) {
            invincibleTime.run(Constants.INVINCIBLE_TIME);
            Player.invincible = false;
        }

        if(!invincibleTime.isRunning()){
            Player.invincible = false;
            Player.visible = true;
            Player.trullyInvincible = false;
        }

        if(invincibleTime.isRunning()){
            if(!flickerTime.isRunning()){
                flickerTime.run(Constants.FLICKER_TIME);
                    Player.visible = !Player.visible;
            }
            Player.trullyInvincible = true;
        }

        Point mousePoint = Mouse.getPos();
        SwingUtilities.convertPointFromScreen(mousePoint, canvas); // Converts the screen position to the actual relative position of canvas
        // NOT USE window because the white stuff in top of the window is also taken into account, so there will be a small "bug"
        direction = Vector2D.fromLocations(center, mousePoint);

        //angle = direction.getAngle();

        //System.out.println("ANGLE: "+angle);

        if (Mouse.CLICKING && (System.currentTimeMillis() - lastTime) > Constants.FIRERATE && numVidas > 0 && !trullyInvincible) {
            if (numBullets > 0) {
                gameState.getMovingObjects().add(new Ball(this.center.getLocation(), this.direction.toUnitary(), Assets.ball, this.gameState));
                lastTime = System.currentTimeMillis();
                numBullets--;
                shoot.play();
            }
        }

        if(shoot.getFramePosition() > 8000) shoot.stop();

        if(Keyboard.SA1 && (System.currentTimeMillis() - lastTimeSA1) > Constants.SA1FIRERATE
                && numVidas > 0 && numBullets >= 4){
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(1,0), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(-1,0), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(0,1), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(0,-1), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(1,1), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(-1,-1), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(1,-1), Assets.ball, this.gameState));
            gameState.getMovingObjects().add(new Ball(this.center.getLocation(), new Vector2D(-1,1), Assets.ball, this.gameState));
            lastTimeSA1 = System.currentTimeMillis();
            numBullets-=4;
            shoot.play();
        }

        if((System.currentTimeMillis() - lastRecharge) > 3000){
            lastRecharge = System.currentTimeMillis();
            numBullets++;
        }

        invincibleTime.update();
        flickerTime.update();
    }

    @Override
    public void draw(Graphics g) {

        if(!visible) return;
        Point mousePoint = Mouse.getPos();
        SwingUtilities.convertPointFromScreen(mousePoint, canvas);

        AffineTransform at = new AffineTransform();
        at.translate(center.getX() - (double) texture.getWidth() / 2, center.getY() - (double) texture.getHeight() / 2);
        at.rotate(direction.getActualAngle(mousePoint),
                (double) texture.getWidth() / 2, (double) texture.getWidth() / 2);

        if(gameState.getMovingObjects().contains(this)) ((Graphics2D) g).drawImage(texture, at, null);

    }
}

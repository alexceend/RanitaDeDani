package states;

import gameObjects.*;
import graphics.Animation;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GameState {
    private final Player player;
    public static final HashSet<MovingObject> movingObjects = new HashSet<>();
    private ArrayList<Animation> animations = new ArrayList<Animation>();

    private int score = 0;
    private int flys;

    public GameState() {
        player = new Player(new Point(400, 400), Assets.player, this);
        movingObjects.add(player);
        flys = 1;
        startWave();
    }

    public void addScore(int value) {
        score += value;
    }

    private void startWave() {
        int numRand = (int) (Math.random() * flys);
        for (int numWasps = 0; numWasps < numRand; numWasps++) {
            double x = 400, y = 400;
            int lado = new Random().nextInt(0, 3);
            switch (lado) {
                case 0: //Por arriba
                    x = Math.random() * 800;
                    y = 0;
                    break;
                case 1: //Por la izda
                    x = 0;
                    y = Math.random() * 800;
                    break;
                case 2: //Por la derecha
                    x = 800;
                    y = Math.random() * 800;
                    break;
                case 3: //Por abajo
                    x = Math.random() * 800;
                    y = 800;
                    break;
            }

            movingObjects.add(new Wasp(
                    new Point((int) x, (int) y),
                    new Vector2D(player.getCenter().getX() - x, player.getCenter().getY() - y).toUnitary(),
                    Assets.wasp,
                    this
            ));
        }

        for (int i = 0; i < flys; i++) {
            double x = 400, y = 400;
            int lado = new Random().nextInt(0, 3);
            switch (lado) {
                case 0: //Por arriba
                    x = Math.random() * 800;
                    y = 0;
                    break;
                case 1: //Por la izda
                    x = 0;
                    y = Math.random() * 800;
                    break;
                case 2: //Por la derecha
                    x = 800;
                    y = Math.random() * 800;
                    break;
                case 3: //Por abajo
                    x = Math.random() * 800;
                    y = 800;
                    break;
            }

            movingObjects.add(new Fly(
                    new Point((int) x, (int) y),
                    new Vector2D(player.getCenter().getX() - x, player.getCenter().getY() - y).toUnitary(),
                    Assets.fly,
                    this
            ));
        }
        flys++;
    }

    //TODO: USAR EL HASH PARA ELEGIR ANIMACIÓN EN VEZ DE DOS FUNCIONES;
    public void playAnimation(Point center, BufferedImage[] img) {
        animations.add(new Animation(
                img,
                50,
                center
        ));
    }

    public void update() {
        player.update();
        collidesWith();
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            mo.update();
            if ((mo instanceof Ball || mo instanceof Fly || mo instanceof Wasp) && isPosOutsideComponent(mo.getCenter())) {
                movingObjects.remove(mo);
            }
        }

        for (int i = 0; i < animations.size(); i++) {
            Animation anim = animations.get(i);
            anim.update();
            if (!anim.isRunning()) animations.remove(i);
        }

        for (MovingObject mo : new HashSet<>(movingObjects))
            if (mo instanceof Fly || mo instanceof Wasp) return;
        startWave();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g);
        for (MovingObject mo : new HashSet<>(movingObjects)) mo.draw(g);
        for (Animation anim : animations) {
            g2d.drawImage(anim.getCurrentFrame(), (int) anim.getCenter().getX(), (int) anim.getCenter().getY(), null);
        }

        drawScore(g);
        drawLifes(g);
        drawNumBullets(g);
        drawSA1(g);
    }

    private void drawScore(Graphics g) {
        Point p = new Point(700, 25);
        String scoreToString = Integer.toString(score);
        for (int i = 0; i < scoreToString.length(); i++) {
            g.drawImage(Assets.numbersImg[Integer.parseInt(scoreToString.substring(i, i + 1))],
                    (int) p.getX(), (int) p.getY(), null);
            p.setLocation(p.getX() + 20, p.getY());
        }
    }

    private void drawNumBullets(Graphics g) {
        Point p = new Point(10, 50);
        String bulletsToString = Integer.toString(Player.numBullets);
        for (int i = 0; i < bulletsToString.length(); i++) {
            g.drawImage(Assets.numbersImg[Integer.parseInt(bulletsToString.substring(i, i + 1))],
                    (int) p.getX(), (int) p.getY(), null);
            p.setLocation(p.getX() + 20, p.getY());
        }
        g.drawImage(Assets.xImg, (int) p.getX()+5, (int) p.getY(), null);
        g.drawImage(Assets.ball, (int) p.getX()+35 - Assets.ball.getWidth()/2, (int) p.getY() - Assets.ball.getHeight()/4, null);
    }

    private void drawLifes(Graphics g) {
        Point p = new Point(10, 25);
        String lifesToString = Integer.toString(Player.numVidas);
        if(Player.numVidas < 0) lifesToString = "0"; //Pone vidas a 0 si baja de 0 para evitar errores
        g.drawImage(Assets.numbersImg[Integer.parseInt(lifesToString)],
                (int) p.getX(), (int) p.getY(), null);
        p.setLocation(p.getX() + 20, p.getY());
        g.drawImage(Assets.xImg, (int) p.getX(), (int) p.getY(), null);
        p.setLocation(p.getX() + 20, p.getY());
        g.drawImage(Assets.lifeIco, (int) p.getX(), (int) p.getY(), null);
    }

    private void drawSA1(Graphics g){
            Point p = new Point(200,25);
            if(player.isSA1Available()){
                g.drawImage(Assets.numbersImg[Integer.parseInt("1")],
                        (int) p.getX(), (int) p.getY(), null);
            }else {
                g.drawImage(Assets.numbersImg[Integer.parseInt("0")],
                        (int) p.getX(), (int) p.getY(), null);
            }
    }

    private boolean isPosOutsideComponent(Point pos) {
        return pos.getX() > 800 || pos.getX() < 0 || pos.getY() > 800 || pos.getY() < 0;
    }

    public HashSet<MovingObject> getMovingObjects() {
        return movingObjects;
    }

    protected void collidesWith() {
        HashSet<MovingObject> movingObjects = this.getMovingObjects();
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            for (MovingObject mo1 : new HashSet<>(movingObjects)) {
                if (mo.equals(mo1)) continue;

                double distance = Math.sqrt(Math.pow(mo.getCenter().getX() - mo1.getCenter().getX(), 2) +
                        Math.pow(mo.getCenter().getY() - mo1.getCenter().getY(), 2));

                if (distance < (double) mo.width / 2 + (double) mo.height / 2) {
                    objectCollision(mo, mo1);
                }
            }
        }
    }

    private void objectCollision(MovingObject a, MovingObject b) {
        if ((a instanceof Ball && (b instanceof Fly || b instanceof Wasp))
                || (b instanceof Ball && (a instanceof Fly || a instanceof Wasp))) {
            this.playAnimation(a.getCenter(), Assets.exp);
            a.remove();
            b.remove();
        } else if ((a instanceof Player && b instanceof Wasp) || (b instanceof Player && a instanceof Wasp)) {
            //TODO Quitar la vida
            if(Player.numVidas != 0) Player.numVidas--;
            if ((Player.numVidas > 0)) {
                if (a instanceof Player) {
                    this.playAnimation(a.getCenter(), Assets.exp); //TODO Cambiar animación a golpe
                    b.remove();
                } else {
                    a.remove();
                    this.playAnimation(b.getCenter(), Assets.exp); //TODO Cambiar animación a golpe
                }
            } else {
                if (a instanceof Player) {
                    this.playAnimation(a.getCenter(), Assets.death);
                } else {
                    this.playAnimation(b.getCenter(), Assets.death);
                }
                a.remove();
                b.remove();
            }
        }
    }


}

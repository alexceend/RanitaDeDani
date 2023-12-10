package states;

import gameObjects.*;
import graphics.Animation;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class GameState {
    private final Player player;
    private final HashSet<MovingObject> movingObjects = new HashSet<>();
    private ArrayList<Animation> animations = new ArrayList<Animation>();

    private int flys;

    public GameState() {
        player = new Player(new Point(400, 400), Assets.player, this);
        movingObjects.add(player);
        flys = 1;
        startWave();
    }


    private void startWave() {
        int numRand = (int) (Math.random() * flys);
        for (int numWasps = 0; numWasps < numRand; numWasps++){
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

    public void playAnimation(Point center) {
        animations.add(new Animation(
                Assets.exp,
                50,
                center
        ));
    }

    public void update() {
        player.update();
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

    }

    private boolean isPosOutsideComponent(Point pos) {
        return pos.getX() > 800 || pos.getX() < 0 || pos.getY() > 800 || pos.getY() < 0;
    }

    public HashSet<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}

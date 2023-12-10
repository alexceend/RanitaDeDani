package states;

import gameObjects.*;
import graphics.Assets;
import math.Vector2D;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class GameState {
    private final Player player;
    private final HashSet<MovingObject> movingObjects = new HashSet<>();

    private int flys;

    public GameState() {
        player = new Player(new Point(400, 400), Assets.player, this);
        flys = 1;
        startWave();
    }


    private void startWave() {
        for (int i = 0; i < flys; i++) {
            double x = 400, y = 400;
            int lado = new Random().nextInt(0, 3);
            System.out.println(lado);
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

    public void update() {
        player.update();
        for (MovingObject mo : new HashSet<>(movingObjects)) {
            mo.update();
            if (mo instanceof Ball && isPosOutsideComponent(mo.getCenter())) {
                movingObjects.remove(mo);
            }

            if (mo instanceof Fly && isPosOutsideComponent(mo.getCenter())) {
                movingObjects.remove(mo);
            }
        }

        for (MovingObject mo : new HashSet<>(movingObjects))
            if(mo instanceof Fly) return;
        startWave();
    }

    public void draw(Graphics g) {
        player.draw(g);
        for (MovingObject mo : new HashSet<>(movingObjects)) mo.draw(g);
    }

    private boolean isPosOutsideComponent(Point pos) {
        return pos.getX() > 800 || pos.getX() < 0 || pos.getY() > 800 || pos.getY() < 0;
    }

    public HashSet<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}

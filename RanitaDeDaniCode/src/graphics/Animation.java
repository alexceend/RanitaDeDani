package graphics;

import math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int velocity;
    private int index;
    private boolean running;
    private Point center;
    private long time, lastTime;

    public Animation(BufferedImage[] frames, int velocity, Point center) {
        this.frames = frames;
        this.velocity = velocity;
        this.center = center;
        index = 0;
        running = true;
        time = 0;
        lastTime = System.currentTimeMillis();
    }

    public void update() {
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (time > velocity) {
            time = 0;
            index++;
            if (index >= frames.length) running = false;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Point getCenter() {
        return center;
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

}

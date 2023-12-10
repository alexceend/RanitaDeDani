package clases;

import graphics.Assets;
import input.Mouse;
import states.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable {
    public static Window instance;
    public static final int WIDTH = 800, HEIGHT = 800;
    private final Canvas canvas = new Canvas();
    private Thread thread;
    private boolean running = false;

    private final int FPS = 144;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    private GameState gameState;

    public Window() {
        setTitle("Ranita Loca");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); //Se despliega en el centro
        setVisible(true);

        final Dimension d = new Dimension(WIDTH, HEIGHT);
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);
        canvas.setFocusable(true); //Reciben enrtadas por parte del teclado

        super.add(canvas);
        canvas.addMouseListener(new Mouse());
    }

    public static void main(String[] args) {
        instance = new Window();
        instance.start();
    }

    private void update() {
        gameState.update();
    }

    private void draw() {
        BufferStrategy bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //-------------Dibujar---------
        g.fillRect(0, 0, WIDTH, HEIGHT); //Limpiar pantalla a cada frame
        gameState.draw(g);

        //g.drawImage(Assets.player, WIDTH / 2, HEIGHT / 2, null);
        g.setColor(Color.black);
        g.drawString("" + AVERAGEFPS, 10, 20);

        /*g.setColor(Color.ORANGE);
        g.drawLine(-100, 0, 100, 0);
        g.drawLine(0, -100, 0, 100);*/
        //-----------------------------
        g.dispose();
        bs.show();
    }

    private void init() {
        Assets.init();
        gameState = new GameState();
    }

    @Override
    public void run() {
        long now;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / ((double) 1000000000 / FPS);
            time += (now - lastTime);
            lastTime = now;


            if (delta >= 1) {
                update();
                draw();
                delta--;
                frames++;
                //System.out.println(frames);
            }

            if (time >= 1000000000) {
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
        }

        stop();
    }

    private void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }
}

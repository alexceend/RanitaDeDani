package clases;

import graphics.Assets;
import input.Mouse;
import states.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

@SuppressWarnings("serial")
public class Window extends JFrame implements Runnable {
    public static final int WIDTH = 800, HEIGHT = 800;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    private final int FPS = 144;
    private double TARGETTIME = 1000000000 / FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;

    private GameState gameState;
    private Mouse mouse;

    public Window() {
        setTitle("Ranita Loca");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); //Se despliega en el centro
        setVisible(true);

        canvas = new Canvas();
        mouse = new Mouse();

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(true); //Reciben enrtadas por parte del teclado

        add(canvas);
        canvas.addMouseListener(mouse);
    }

    public static void main(String[] args) {
        new Window().start();
    }

    private void update() {
        mouse.update();
        gameState.update();
    }

    private void draw() {
        bs = canvas.getBufferStrategy();

        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        //-------------Dibujar---------
        g.fillRect(0, 0, WIDTH, HEIGHT); //Limpiar pantalla a cada frame

        gameState.draw(g);

        //g.drawImage(Assets.player, WIDTH / 2, HEIGHT / 2, null);
        g.setColor(Color.black);
        g.drawString("" + AVERAGEFPS, 10, 20);

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

        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;

        init();

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;
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
}

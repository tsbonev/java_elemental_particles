package com.tsb.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 2L;

    public static final int WIDTH = 1080, HEIGHT = (WIDTH / 12) * 9;

    private Thread thread;
    private Thread thread1;
    private boolean running = false;

    private Random r;

    private HUD hud;

    private Handler handler;


    public Game(){
        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));

        new Window(WIDTH, HEIGHT, "Let's build a game", this);

        r = new Random();

        hud = new HUD();


        //handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));

        handler.addObject(new FlameBall(WIDTH / 2 - 16, HEIGHT / 2 - 16 , ID.BasicEnemy, handler,
                8, 8));
        handler.addObject(new FlameBall(WIDTH / 2 - 16, HEIGHT / 2 - 16 , ID.BasicEnemy, handler,
                7, 9));
        handler.addObject(new FlameBall(WIDTH / 2 - 16, HEIGHT / 2 - 16 , ID.BasicEnemy, handler,
                9, 7));
        handler.addObject(new FlameBall(WIDTH / 2 - 16, HEIGHT / 2 - 16 , ID.BasicEnemy, handler,
                6, 10));
        handler.addObject(new FlameBall(WIDTH / 2 - 16, HEIGHT / 2 - 16 , ID.BasicEnemy, handler,
                10, 6));


    }


    public synchronized void start(){

        thread = new Thread(this);
        thread1 = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop(){

        try{

            thread.join();
            running = false;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();


    }

    public void tick(){
        handler.tick();
        hud.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        hud.render(g);

        g.dispose();
        bs.show();

    }

    public static int clamp(int var, int min, int max){
        if(var >= max) return var = max;
        else if(var <= min) return var = min;
        else return var;
    }


    public static void main(String args[]){

        new Game();

    }

}

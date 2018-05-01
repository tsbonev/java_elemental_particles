package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    private Random r = new Random();
    private Handler handler;


    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

    }


    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {

        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH -32);
        y = Game.clamp(y, 0, Game.HEIGHT - 32);

        collision();

    }

    private void collision(){
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);

        g.fillRect(x, y, 32, 32);

    }
}

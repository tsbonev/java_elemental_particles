package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject {


    private Handler handler;

    Random random = new Random();

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 4 + random.nextInt(8);
        velY = 4 + random.nextInt(8);

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 1, 1);
    }

    public void tick() {

        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT) velY *= -1;

        if(x <= 0 || x >= Game.WIDTH) velX *= -1;

        handler.addObject(new ScatteredTrail(x, y, ID.Trail, handler, new Color( 255, 83, 48),16, 16, 0.02f, true));

    }

    public void render(Graphics g) {

        //g.setColor(new Color(255, 83, 48));
        //g.fillRect(x, y, 16, 16);

    }
}

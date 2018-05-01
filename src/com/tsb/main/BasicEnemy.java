package com.tsb.main;

import java.awt.*;

public class BasicEnemy extends GameObject {


    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        velX = 5;
        velY = 5;

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT) velY *= -1;

        if(x <= 0 || x >= Game.WIDTH) velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, handler, Color.red,16, 16, 0.02f));

    }

    public void render(Graphics g) {

        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);

    }
}

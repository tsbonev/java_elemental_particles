package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class Trail extends GameObject {

    protected float alpha = 1;
    protected Handler handler;
    protected Color color;
    private int width, height;
    float life;

    public Trail(int x, int y, ID id, Handler handler, Color color, int width, int height, float life){
        super(x , y, id);

        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    @Override
    public void tick() {
        if(alpha > life){
            alpha -= life - 0.001f;
        }
        else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);

        g2d.setComposite(makeTransparent(1));


    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}

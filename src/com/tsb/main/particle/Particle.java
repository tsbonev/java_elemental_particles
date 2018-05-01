package com.tsb.main.particle;

import java.awt.*;

public class Particle {

    private Color color;
    private int x;
    private int y;
    private int velX;
    private int velY;


    public Particle(Color color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getVelY() {
        return velY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

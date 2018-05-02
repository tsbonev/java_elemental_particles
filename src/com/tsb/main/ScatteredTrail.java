package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class ScatteredTrail extends Trail {


    static Random random = new Random();
    static int r1 =random.nextInt(10);
    static int r2 =random.nextInt(10);
    private int width;
    private int height;
    private boolean allowedTrail;

    public ScatteredTrail(int x, int y, ID id,
                          Handler handler, Color color,
                          int width, int height,
                          float life, boolean allowedTrail) {
        super(x + r1, y + r2, id, handler, color, width, height, life);
        this.allowedTrail = allowedTrail;
        this.width = width;
        this.height = height;
        r1 = random.nextInt(10);
        r2 = random.nextInt(10);
    }

    @Override
    public void tick(){

        if(alpha < 0.85){
            this.color = new Color(67, 46, 47).brighter();
            this.setVelX(this.getVelX() - 1);
            this.setVelY(this.getVelY() - 1);
        }
        else if(alpha < 0.90){
            this.color = new Color(208, 154, 50);
        }
        else if(alpha < 0.93){
            this.color = new Color(255, 157, 75);
        }
        else if(alpha < 0.95){
            this.color = new Color(255, 122, 74);
        }

        if(alpha < 0.55){
            this.color = this.color.darker();
        }
        if ( alpha < 0.70 && allowedTrail){
            allowedTrail = false;
            handler.addObject(new ScatteredTrail(this.x, this.y, this.id,
                    handler, new Color(83, 21, 24),
                    width / 4 + random.nextInt(width / 4),
                    height / 4 + random.nextInt(height / 4),
                    0.03f, false));
        }

        super.tick();
    }
}

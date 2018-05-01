package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class ScatteredTrail extends Trail {

    static Random random = new Random();
    static int r1 =random.nextInt(10);
    static int r2 =random.nextInt(10);

    public ScatteredTrail(int x, int y, ID id, Handler handler, Color color, int width, int height, float life) {
        super(x + r1, y + r2, id, handler, color, width, height, life);
        r1 = random.nextInt(10);
        r2 = random.nextInt(10);
    }

    @Override
    public void tick(){
        
        if(alpha < 0.65){
            this.color = Color.gray;
            this.setVelX(this.getVelX() - 1);
            this.setVelY(this.getVelY() - 1);
        }
        else if(alpha < 0.80){
            this.color = Color.orange;
        }
        else if(alpha < 0.98){
            this.color = Color.pink;
        }

        if(alpha < 0.55){
            this.color = this.color.darker();
        }

        super.tick();
    }
}

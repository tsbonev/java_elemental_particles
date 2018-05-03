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

    //region Color, Range, Life
    private static float headTail = 0.95f;
    private static Color headColor = new Color(208, 169, 66);

    private static float midTail = 0.93f;
    private static Color midColor = new Color(255, 157, 75);

    private static float endTail = 0.88f;
    private static Color endColor = new Color(255, 122, 74);

    private static float smokeTail = 0.85f;
    private static Color smokeColor = new Color(67, 48, 50);

    private static float trailingTail = 0.70f;
    private static Color trailingColor = new Color(83, 21, 24);
    private static float trailingLife = 0.03f;

    private static float getDarker = 0.55f;
    //endregion

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

        if(alpha < smokeTail){
            this.color = smokeColor.brighter();
            this.setVelX(this.getVelX() - 1);
            this.setVelY(this.getVelY() - 1);
        }
        else if(alpha < endTail){
            this.color = endColor;
        }
        else if(alpha < midTail){
            this.color = midColor;
        }
        else if(alpha < headTail){
            this.color = headColor;
        }

        if(alpha < getDarker){
            this.color = this.color.darker();
        }
        if ( alpha < trailingTail && allowedTrail){
            allowedTrail = false;
            handler.addObject(new ScatteredTrail(this.x, this.y, this.id,
                    handler, trailingColor,
                    width / 4 + random.nextInt(width / 4),
                    height / 4 + random.nextInt(height / 4),
                    trailingLife, false));
        }

        super.tick();
    }
}

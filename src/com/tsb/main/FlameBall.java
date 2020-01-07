package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class FlameBall extends GameObject {

    private Handler handler;

    Random random = new Random();

    private static int size = 16;
    private static int standardVelocity = 16;

    private static float life = 0.02f;
    //private static Color baseColor = new Color( 255, 83, 48); OG color
    private static Color baseColor = new Color( 255, 84, 45);

    public FlameBall(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        int[] playerPos = getPlayerPosition();

        int xPos = 0;
        int yPos = 0;

        if(x - playerPos[0] > 0){
            xPos = 1;
        }
        else if(x - playerPos[0] < 0){
            xPos = -1;
        }

        if(y - playerPos[1] > 0){
            yPos = 1;
        }
        else if(x - playerPos[1] < 0){
            yPos = -1;
        }

        playerPos[0] = Math.abs(playerPos[0]);
        playerPos[1] = Math.abs(playerPos[1]);

        float xMult = x >= y ? 1 : Math.round(1f * y / x);
        float yMult = y >= x ? 1 : Math.round(1f * x / y);

        velX = xPos * standardVelocity;
        velY = yPos * standardVelocity;

    }

    public FlameBall(int x, int y, ID id, Handler handler, int velX, int velY){

        super(x, y, id);

        this.handler = handler;

        this.velX = velX;
        this.velY = velY;

    }

    public int[] getPlayerPosition(){

        int[] pos = new int[2];

        for (GameObject obj : handler.object ) {
            if(obj.id == ID.Player){
                pos[0] = obj.getX() + 16;
                pos[1] = obj.getY();
                break;
            }
        }

        return pos;
    }


    public Rectangle getBounds(){
        return new Rectangle(x, y, 1, 1);
    }

    public void tick() {

        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - size) velY *= -1;

        if(x <= 0 || x >= Game.WIDTH - size) velX *= -1;

        handler.addObject(new ScatteredTrail(x - size / 2, y - size / 2, ID.Trail, handler,
                baseColor,
                size, size,
                life, true));

    }

    public void render(Graphics g) {

        //g.setColor(new Color(255, 33, 33));
        //g.fillRect(x + 8, y + 8, 8, 8);

    }
}

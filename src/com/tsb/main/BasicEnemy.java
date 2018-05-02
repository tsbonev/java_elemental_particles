package com.tsb.main;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject {


    private Handler handler;

    Random random = new Random();

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);

        this.handler = handler;

        int[] playerPos = getPlayerPosition();

        float standardVelocity = 8;

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

        float xMult = x >= y ? 1 : Math.round((float)1 * (float)y / (float)x);
        float yMult = y >= x ? 1 : Math.round((float)1 * (float)x / (float)y);

        velX = xPos * standardVelocity;
        velY = yPos * standardVelocity;

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

        if(y <= 0 || y >= Game.HEIGHT) velY *= -1;

        if(x <= 0 || x >= Game.WIDTH) velX *= -1;

        handler.addObject(new ScatteredTrail(x, y, ID.Trail, handler, new Color( 255, 83, 48),16, 16, 0.02f, true));

    }

    public void render(Graphics g) {

        //g.setColor(new Color(255, 83, 48));
        //g.fillRect(x, y, 16, 16);

    }
}

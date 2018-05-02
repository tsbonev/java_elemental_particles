package com.tsb.main;

import java.awt.*;
import java.io.Console;
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

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 32);

        handler.addObject(new Trail(this.x, this.y, ID.Trail,
                handler, Color.white, 32, 32, 0.1f));

        collision();

    }

    private void collision(){
        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 5;
                    if(HUD.HEALTH < 1) System.out.println("Game over");
                }
            }

        }
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.white);

        g.fillRect(x, y, 32, 32);

    }
}

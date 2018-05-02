package com.tsb.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;

    public MouseInput (Handler handler){
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e){

        if(e.getButton() == 1){

            System.out.println(e.getX() + " " + e.getY());

            handler.addObject(
                    new BasicEnemy(e.getX(), e.getY(), ID.BasicEnemy, handler)
            );
        }

    }

}

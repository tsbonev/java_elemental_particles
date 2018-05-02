package com.tsb.main;

import java.util.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public Map keysDown = new HashMap<String, Boolean>();


    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            keysDown.put("W", true);
            movePlayer();
        }
        if (key == KeyEvent.VK_A) {
            keysDown.put("A", true);
            movePlayer();
        }
        if (key == KeyEvent.VK_S) {
            keysDown.put("S", true);
            movePlayer();
        }
        if (key == KeyEvent.VK_D) {
            keysDown.put("D", true);
            movePlayer();
        }


        if (key == KeyEvent.VK_ESCAPE) System.exit(1);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            keysDown.put("W", false);
        }
        if (key == KeyEvent.VK_A) {
            keysDown.put("A", false);
        }
        if (key == KeyEvent.VK_S) {
            keysDown.put("S", false);
        }
        if (key == KeyEvent.VK_D) {
            keysDown.put("D", false);
        }

        movePlayer();

    }

    public void movePlayer(){

        for (GameObject player: handler.object) {
            if(player.id == ID.Player){

                if((boolean)keysDown.get("W")){
                    player.setVelY(-8);
                }
                else if(!(boolean)keysDown.get("S")){
                    player.setVelY(0);
                }

                if((boolean)keysDown.get("S")){
                    player.setVelY(8);
                }
                else if(!(boolean)keysDown.get("W")){
                    player.setVelY(0);
                }

                if((boolean)keysDown.get("D")){
                    player.setVelX(8);
                }
                else if(!(boolean)keysDown.get("A")){
                    player.setVelX(0);
                }

                if((boolean)keysDown.get("A")){
                    player.setVelX(-8);
                }
                else if(!(boolean)keysDown.get("D")){
                    player.setVelX(0);
                }

            }
        }

    }
}

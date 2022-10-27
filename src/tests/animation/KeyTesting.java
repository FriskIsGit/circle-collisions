package tests.animation;

import gfx.Display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTesting{

    public static void main(String[] args){
        KeyListener listener = new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyPressed(KeyEvent e){}
            public void keyReleased(KeyEvent e){
                System.out.println(e.getKeyCode());
            }
        };

        Display display = new Display(1400, 800){
            @Override
            public void draw(Graphics2D gfx){
            }
        };
        display.addKeyListener(listener);
    }
}

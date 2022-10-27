package tests.animation;

import gfx.CircleAnimation;
import gfx.Display;
import utilities.structs.Extreme;
import utilities.structs.Line;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AnimationTest2{
    public static void main(String[] args){
        final int W = 500, H = 300;
        CircleAnimation.initAnimation(W, H);
        List<Line> lines = new ArrayList<>(Arrays.asList(new Line(22,33), new Line(7,2), new Line(-23,6)));
        Random rand = new Random();
        Display display = new Display(W , H){
            @Override
            public void draw(Graphics2D gfx){
                for (Line line : lines){
                    Color c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                    gfx.setColor(c);
                    Extreme p = CircleAnimation.findMaxAndMin(W, H, line);
                    gfx.drawLine(W/2 + (int) p.min, H/2 -(int) line.forX(p.min), W/2 + (int) p.max, H/2 - (int) line.forX(p.max));
                }
            }
        };
        display.renderFrame();
    }
}

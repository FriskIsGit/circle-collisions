package input;

import collide.Circle;
import collide.CircleMovement;
import gfx.CircleAnimation;
import gfx.Display;
import utilities.structs.DoublePoint;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyboardHandler implements KeyListener{

    private final Display display;
    private List<Circle> circleList;
    private List<Circle> listCopy = new ArrayList<>();
    private final boolean[] isRunningState;
    public KeyboardHandler(Display display, List<Circle> circleList, boolean[] isRunningState){
        this.display = display;
        this.circleList = circleList;
        this.isRunningState = isRunningState;
    }

    public void keyTyped(KeyEvent e){
    }
    public void keyPressed(KeyEvent e){
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()){
            //left
            case 37:
                for(Circle c: circleList){
                    DoublePoint previousPoint = CircleAnimation.findNextPoint(c.movement.line, new DoublePoint(c.x, c.y), c.movement.distance, !c.movement.increasingX);
                    if(previousPoint.isNaN()){
                        CircleAnimation.recalculateLine(c);
                        previousPoint = CircleAnimation.findNextPoint(c.movement.line, new DoublePoint(c.x, c.y), c.movement.distance, !c.movement.increasingX);
                    }
                    c.x = previousPoint.x;
                    c.y = previousPoint.y;
                }

                CircleAnimation.doCollisionChecks(circleList);
                display.renderFrame();
                break;
            //right
            case 39:
                for(Circle c: circleList){
                    DoublePoint next = CircleAnimation.findNextPoint(c.movement.line, new DoublePoint(c.x, c.y), c.movement.distance, c.movement.increasingX);
                    if(next.isNaN()){
                        CircleAnimation.recalculateLine(c);
                        next = CircleAnimation.findNextPoint(c.movement.line, new DoublePoint(c.x, c.y), c.movement.distance, c.movement.increasingX);
                    }
                    c.x = next.x;
                    c.y = next.y;
                }
                CircleAnimation.doCollisionChecks(circleList);
                display.renderFrame();
                break;
            //space
            case 32:
                isRunningState[0] = !isRunningState[0];
                break;
            case 80:
                for(Circle c: circleList){
                    System.out.println("[" + c.x +", " + c.y + "]");
                }
                break;
            //i
            case 73:
                final String MESSAGE =
                        "Press space to stop/resume animation\n" +
                                "Use left/right arrows to reverse/advance animation\n" +
                                "Drag and drop circles to change their position\n" +
                                "Press 'd' to turn lines on and off\n" +
                                "Press '[' to save state to memory\n" +
                                "Press ']' to load state from memory\n" +
                                "Program arguments:\n" +
                                "-r circle radius (int)\n" +
                                "-c number of circles (int)\n" +
                                "-w display width (int)\n" +
                                "-h display height (int)\n" +
                                "-draw draw lines";
                JOptionPane.showMessageDialog(display, MESSAGE);
                break;
            //d
            case 68:
                CircleAnimation.DRAW_LINES = !CircleAnimation.DRAW_LINES;
                break;
            //[
            case 91:
                listCopy.clear();
                int size = circleList.size();
                for (int i = 0; i < size; i++){
                    Circle c = circleList.get(i).copy();
                    listCopy.add(c);
                }
                break;
            //]
            case 93:
                if(listCopy.isEmpty()){
                    return;
                }
                size = circleList.size();
                for (int i = 0; i < size; i++){
                    Circle c = circleList.get(i);
                    Circle copied = listCopy.get(i);
                    c.x = copied.x;
                    c.y = copied.y;
                    c.movement = copied.movement.copy();
                    c.color = copied.color;

                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
    }
}

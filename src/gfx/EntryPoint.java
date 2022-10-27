package gfx;

import collide.Circle;
import input.KeyboardHandler;
import input.MouseHandler;
import utilities.structs.DoublePoint;
import utilities.structs.Extreme;
import utilities.structs.Line;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class EntryPoint{
    public static int displayWidth = 1400;
    public static int displayHeight = 800;
    public static void main(String[] args){
        //-c 100 -r 20 -w 1700 -h 900 -draw false
        if(args.length > 1){
            Arguments arguments = new Arguments(args);
            arguments.parseAndApplyArguments();
        }

        System.out.println("CIRCLES: " + CircleAnimation.CIRCLES);
        System.out.println("RADIUS: " + CircleAnimation.RADIUS);
        System.out.println("Width x Height: " + EntryPoint.displayWidth + " x " + EntryPoint.displayHeight);
        String title = "This label will disappear shortly..";
        List<Circle> circleList = new ArrayList<>();

        //Display display = new Display(720, 540, title){
        Display display = new Display(displayWidth, displayHeight, title){
            @Override
            public void draw(Graphics2D gfx){
                for (Circle circle : circleList){
                    gfx.setColor(circle.color);
                    int radius = (int) circle.r;
                    gfx.fillOval((int) circle.x-radius, (int) circle.y-radius, 2*radius, 2*radius);
                    if(CircleAnimation.DRAW_LINES){
                        Line line = circle.movement.line;
                        Extreme p = CircleAnimation.findMaxAndMin(this.getWidth(), this.getHeight(), line);
                        gfx.drawLine((int) p.min, (int) line.forX(p.min), (int) p.max, (int) line.forX(p.max));
                    }
                }
            }
        };

        final boolean[] running = {true};
        display.addKeyListener(new KeyboardHandler(display, circleList, running));

        display.addMouseListener(new MouseHandler(display, circleList));

        CircleAnimation.initAnimation(circleList, display);

        final int FPS = 30;
        final int msPerFrame = 1000 / FPS;
        Thread paintingThread = new Thread(new Runnable(){
            @Override
            public void run(){
                long last = 0;
                int updateIntervalMs = 5;
                while(true){
                    sleep(50);
                    while (running[0]){
                        long now = System.currentTimeMillis();
                        if(now - last >= updateIntervalMs){
                            last = System.currentTimeMillis();
                            long collisionSt = System.nanoTime();
                            CircleAnimation.doCollisionChecks(circleList);
                            long collisionEn = System.nanoTime();
                            long delta = collisionEn - collisionSt;
                            //System.out.println("Nano time for collisions: " + delta + " ; Ms: " + delta/1_000_000D);
                            CircleAnimation.moveCircles(circleList);
                        }
                        display.renderFrame();
                    }
                }
            }

            private void displayLineDefinitions(){
                for(Circle c : circleList) {
                    System.out.println(c.movement.line);
                }
                System.out.println("----------------------------------------");
            }
        }, "paintingThread");
        paintingThread.setDaemon(true);
        paintingThread.start();

        Thread statesThread = new Thread(new Runnable(){
            @Override
            public void run(){
                int lastFrames = 0, current;
                while (true){
                    sleep(1000);
                    current = display.getFrames();
                    System.out.println("FPS: " + (current - lastFrames));
                    lastFrames = current;
                }
            }
        });
        statesThread.setDaemon(true);
        statesThread.start();
    }


    private static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException exc){
            exc.printStackTrace();
        }
    }

}

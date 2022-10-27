package collide;

import utilities.structs.IntPoint;
import utilities.structs.Line;

import java.util.Random;

public class CircleMovement{
    public final static Random random = new Random();

    public Line line;
    public boolean increasingX;
    public double distance;
    public double mass;

    public CircleMovement(Line line, boolean increasingX, double distance){
        this.line = line;
        this.increasingX = increasingX;
        this.distance = distance;
    }
    public CircleMovement(Line line, double distance, double mass){
        this(line, random.nextBoolean(), distance, mass);
    }
    public CircleMovement(Line line, boolean increasingX, double distance, double mass){
        this.line = line;
        this.increasingX = increasingX;
        this.distance = distance;
        this.mass = mass;
    }

    public static CircleMovement generateCircleMovement(int width,  int height){
        return generateCircleMovement(width, height, 1D, 1D);
    }

    //generates random line which goes through given box(WxH)
    public static CircleMovement generateCircleMovement(int width, int height, double speedCap, double mass){
        IntPoint p1 = genPoint(width, height);
        IntPoint p2 = genPoint(width, height);
        if(p1.equals(p2)){
            return generateCircleMovement(width, height, speedCap, mass);
        }
        Line line = Line.linearFunc(p1,p2);
        double speed;
        do{
            speed = random.nextDouble()*speedCap;
        }while(speed == 0);
        return new CircleMovement(line, speed, mass);
    }

    private static IntPoint genPoint(int upperX, int upperY){
        int x = random.nextInt(upperX), y = random.nextInt(upperY);
        return new IntPoint(x, y);
    }
    public CircleMovement copy(){
        return new CircleMovement(this.line, this.increasingX, this.distance, this.mass);
    }
}

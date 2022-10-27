package collide;

import java.awt.*;

public class Circle extends Figure{
    public CircleMovement movement;
    public Color color;
    public Circle(double a, double b, double r){
        this.x = a;
        this.y = b;
        this.r = r;
    }
    public Circle(int a, int b, int r){
        this.x = a;
        this.y = b;
        this.r = r;
    }
    public double r;

    public double centerX(){
        return x;
    }
    public double centerY(){
        return y;
    }
    public Circle copy(){
        Circle c = new Circle(this.x, this.y, this.r);
        c.movement = this.movement.copy();
        c.color = this.color;
        return c;
    }
}
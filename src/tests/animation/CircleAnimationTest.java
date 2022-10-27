package tests.animation;

import collide.Circle;
import collide.CircleMovement;
import gfx.CircleAnimation;
import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.Line;
import utilities.Maths;
import utilities.structs.TwoDoublesList;

import java.util.ArrayList;
import java.util.List;

public class CircleAnimationTest{
    @Test
    public void animationTest(){
        List<Circle> circleList = new ArrayList<>();
        CircleMovement movement = new CircleMovement(new Line(0.3, 40), true, 3);
        Circle c = new Circle(90,67, 10);
        c.movement = movement;
        DoublePoint next = CircleAnimation.findNextPoint(new DoublePoint(c.x, c.y), movement);
        CircleAnimation.initAnimation(100, 100);
        circleList.add(c);
        CircleAnimation.doCollisionChecks(circleList);
        CircleAnimation.moveCircles(circleList);
    }
    @Test
    public void actualIssue(){
        CircleAnimation.initAnimation(1400, 800);
        List<Circle> circleList = new ArrayList<>();
        Line line = new Line(0.304, 506.512);
        CircleMovement movement = new CircleMovement(line, true, 0.62776467);
        Circle c = new Circle(24.785,548.977, 25);
        c.movement = movement;
        circleList.add(c);

        DoublePoint current = new DoublePoint(c.x, c.y);

        DoublePoint next = CircleAnimation.findNextPoint(current, movement);
        if(Double.isNaN(next.x)){
            System.out.println("LOL");
        }
        double N = current.x;
        double P = current.y;
        double b = line.b;
        TwoDoublesList solutions = Maths.solveQuadratic(
                1+line.a*line.a,
                -2*N - 2*P*line.a + 2*line.a*b,
                -2*P*b + b*b - movement.distance*movement.distance + P*P + N*N);
        System.out.println("Num of solutions: " + solutions.size());
        System.out.println(solutions);

        /*Animation.doCollisionChecks(circleList);
        Animation.moveCircles(circleList);*/
    }
    @Test
    public void dirtyPlay(){
        CircleAnimation.initAnimation(200, 200);
        List<Circle> circleList = new ArrayList<>();
        Line line = new Line(0.5);
        CircleMovement movement = new CircleMovement(line, true, 2);
        Circle c = new Circle(188,94, 12);
        c.movement = movement;
        circleList.add(c);

        DoublePoint current = new DoublePoint(c.x, c.y);
        DoublePoint next = CircleAnimation.findNextPoint(current, movement);
        DoublePoint nextNext = CircleAnimation.findNextPoint(next, movement);
        System.out.println("current: "+ current);
        System.out.println("next: "+ next);
        System.out.println("nextNext: "+ nextNext);
    }
    @Test
    public void collisions(){
        CircleAnimation.initAnimation(200, 200);
        Line line1 = new Line(0.05,3);
        Line line2 = new Line(10,10);
        System.out.println(line1);
        System.out.println(line2);
        Line ref1 = line1.reflectOff(line2);
        Line ref2 = line2.reflectOff(line1);
        System.out.println(ref1);
        System.out.println(ref2);
    }

}

package gfx;

import collide.Circle;
import collide.CircleMovement;
import collide.Shapes;
import utilities.structs.*;
import utilities.Maths;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class CircleAnimation{
    public static boolean MANY_COLLISIONS = true;
    public static boolean GUARANTEE_SEPARATE = true;
    public static final Random rand = new Random();
    public static int CIRCLES = 50;
    public static int width;
    public static int height;
    public static int RADIUS = 25;
    public static boolean DRAW_LINES = false;
    public static boolean DISPLAY_CHECKS = false;

    public static void initAnimation(List<Circle> circleList, Display display){
        initAnimation(circleList, display.getWidth(), display.getHeight());
    }
    public static void initAnimation(List<Circle> circleList, int width, int height){
        initAnimation(width, height);
        for (int i = 0; i<CIRCLES; i++){
            CircleMovement movement =  CircleMovement.generateCircleMovement(width, height);
            //choose circle coordinates based on frame dimensions, movement line (optionally: radius)
            DoublePoint center = generateCircleCoordinates(width, height, movement.line);
            Circle circle = new Circle(center.x, center.y, RADIUS);
            if(GUARANTEE_SEPARATE){
                while(collidingWithAny(circleList, circle)){
                    center = generateCircleCoordinates(width, height, movement.line);
                    circle.x = center.x;
                    circle.y = center.y;
                }
            }
            circle.movement = movement;
            circle.color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            circleList.add(circle);
        }
    }

    private static boolean collidingWithAny(List<Circle> circleList, Circle circle){
        for (Circle c : circleList){
            boolean possible = Shapes.isCollisionPossible(c, circle);
            if(possible){
                if(Shapes.collision(c, circle)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void initAnimation(int width, int height){
        CircleAnimation.width = width;
        CircleAnimation.height = height;
        System.out.println("Resolution: " + width + " x " + height);
    }
    public static void doCollisionChecks(List<Circle> circleList){
        doSideCollisionChecks(circleList);
        doCircleWithCircleCollisionChecks(circleList);
    }

    private static void doCircleWithCircleCollisionChecks(List<Circle> circleList){
        if(!MANY_COLLISIONS){
            return;
        }
        int collisions = 0;
        int preciseChecks = 0;
        final int allCircles = circleList.size();
        for (int i = 0; i < allCircles; i++){
            for (int j = i+1; j < allCircles; j++){
                Circle c1 = circleList.get(i);
                Circle c2 = circleList.get(j);
                boolean possible = Shapes.isCollisionPossible(c1, c2);
                if(possible){
                    preciseChecks++;
                    boolean collision = Shapes.collision(c1, c2);
                    if(collision){
                        resolveCollisionWithoutTrig(c1, c2);
                        collisions++;
                        //MANY_COLLISIONS = false;
                    }
                }
            }
        }
        if(DISPLAY_CHECKS){
            System.out.println("Collisions: " + collisions);
            System.out.println("Estimated checks run: " + minBruteForceCollisionChecks(CircleAnimation.CIRCLES) + " for this number of circles: " + CircleAnimation.CIRCLES);
            System.out.println("Precise checks run: " + preciseChecks);
        }
    }

    private static void doSideCollisionChecks(List<Circle> circleList){
        for (Circle c : circleList){
            CircleMovement movement = c.movement;
            boolean increasingX = movement.increasingX;
            Line line = movement.line;
            double slope = line.a;
            if(c.x - c.r <= 0){
                if(!increasingX){
                    Line reflected = line.reflectOffYAxisAt(0);
                    reflected.b = c.y - reflected.a*c.x;
                    //slope flips and so should the increasing flag
                    movement.increasingX = true;
                    movement.line = reflected;
                }
            }
            else if(CircleAnimation.width <= c.x + c.r){
                if(increasingX){
                    Line reflected = line.reflectOffYAxisAt(CircleAnimation.width);
                    reflected.b = c.y - reflected.a*c.x;
                    movement.increasingX = false;
                    movement.line = reflected;
                }
            }
            else if(CircleAnimation.height <= c.y + c.r){
                if((increasingX && slope>0) || (!increasingX && slope<0)){
                    Line reflected = line.reflectOffXAxisAt(CircleAnimation.height);
                    reflected.b = c.y - reflected.a*c.x;
                    movement.line = reflected;
                }
            }
            else if(c.y - c.r <= 0){
                if((!increasingX && slope>0) || (increasingX && slope<0)){
                    Line reflected = line.reflectOffXAxisAt(0);
                    reflected.b = c.y - reflected.a*c.x;
                    movement.line = reflected;
                }
            }
        }
    }
    private static void resolveCollisionLazy(Circle c1, Circle c2){
        c1.movement.increasingX = !c1.movement.increasingX;
        c2.movement.increasingX = !c2.movement.increasingX;
    }

    private static void resolveCollisionLineBased(Circle c1, Circle c2){
        Line origLine1 = c1.movement.line;
        Line origLine2 = c2.movement.line;
        if(origLine1.NaF && origLine2.NaF){
            return;
        }
        Line firstReflected = origLine1.reflectOff(origLine2);
        Line secondReflected = origLine2.reflectOff(origLine1);
        firstReflected.b = c1.y - firstReflected.a*c1.x;
        secondReflected.b = c2.y - secondReflected.a*c2.x;
        c1.movement.line = firstReflected;
        c2.movement.line = secondReflected;

    }
    private static void resolveCollisionCenterBased(Circle c1, Circle c2){
        DoublePoint center1 = new DoublePoint(c1.x, c1.y);
        DoublePoint center2 = new DoublePoint(c2.x, c2.y);
        DoublePoint finalVelocity1 = calculateVelocities(c1, c2);
        DoublePoint finalVelocity2 = calculateVelocities(c2, c1);

        c1.movement.increasingX = 0 < finalVelocity1.x;
        c2.movement.increasingX = 0 < finalVelocity2.x;

        Line newLine1 = Line.linearFunc(center1.x, center1.y, center1.x + finalVelocity1.x, center1.y + finalVelocity1.y);
        Line newLine2 = Line.linearFunc(center2.x, center2.y, center2.x + finalVelocity2.x, center2.y + finalVelocity2.y);
        c1.movement.line = newLine1;
        c2.movement.line = newLine2;
    }
    //Vobarian method (https://www.vobarian.com/collisions/2dcollisions2.pdf)
    private static void resolveCollisionWithoutTrig(Circle c1, Circle c2){

        Vector2D normal = new Vector2D(c2.x - c1.x,c2.y - c1.y);  //n - normal vector
        Vector2D un = normal.divide(normal.magnitude());  //un - unit normal vector
        Vector2D ut = new Vector2D(-un.y, un.x); //ut - unit tangent vector

        DoublePoint center1 = new DoublePoint(c1.x, c1.y);
        DoublePoint center2 = new DoublePoint(c2.x, c2.y);
        DoublePoint np1 = findNextPoint(center1, c1.movement);
        DoublePoint np2 = findNextPoint(center2, c2.movement);
        Vector2D v1 = new Vector2D(np1.x - center1.x, np1.y - center1.y);
        Vector2D v2 = new Vector2D(np2.x - center2.x, np2.y - center2.y);
        double v1n = un.multiply(v1);
        double v1t = ut.multiply(v1);
        double v2n = un.multiply(v2);
        double v2t = ut.multiply(v2);
        //tangential scalars don't change (v1t, v2t)
        double m1 = c1.movement.mass;
        double m2 = c2.movement.mass;
        double m_sum = m1+m2;
        double v1n_after = (v1n*(m1-m2) + 2*m2*v2n)/m_sum;
        double v2n_after = (v2n*(m2-m1) + 2*m1*v1n)/m_sum;
        //convert scalars to vectors
        Vector2D v1n_after_vec = un.multiply(v1n_after);
        Vector2D v1t_after_vec = ut.multiply(v1t);
        Vector2D v2n_after_vec = un.multiply(v2n_after);
        Vector2D v2t_after_vec = ut.multiply(v2t);
        Vector2D finalVector1 = v1n_after_vec.add(v1t_after_vec);
        Vector2D finalVector2 = v2n_after_vec.add(v2t_after_vec);
        //apply final vectors
        Line newLine1 = Line.linearFunc(center1.x, center1.y, center1.x + finalVector1.x, center1.y + finalVector1.y);
        Line newLine2 = Line.linearFunc(center2.x, center2.y, center2.x + finalVector2.x, center2.y + finalVector2.y);
        c1.movement.increasingX = 0 < finalVector1.x;
        c2.movement.increasingX = 0 < finalVector2.x;
        c1.movement.line = newLine1;
        c2.movement.line = newLine2;
        c1.movement.distance = finalVector1.magnitude();
        c2.movement.distance = finalVector2.magnitude();
    }

    //Two-dimensional collision with two moving objects (wikipedia method)
    private static DoublePoint calculateVelocities(Circle c1, Circle c2){
        Line centerLine = Line.linearFunc(c1.x, c1.y, c2.x, c2.y);
        CircleMovement move1 = c1.movement;
        CircleMovement move2 = c2.movement;
        Line y0Line = new Line(0);
        double m1 = move1.mass;
        double m2 = move2.mass;
        //contact angle
        double phi = centerLine.getAngRad(y0Line);
        //movement angles
        double theta1 = move1.line.getAngRad(y0Line);
        double theta2 = move2.line.getAngRad(y0Line);

        double v1 = move1.distance;
        double v2 = move2.distance;

        final double PI_HALF = Math.PI/2;
        double fraction = (v1 * Math.cos(theta1 - phi)*(m1-m2) + 2*m2*v2*Math.cos(theta2-phi))/(m1+m2);
        double commonPart = v1*Math.sin(theta1 - phi);
        double v1x = fraction * Math.cos(phi) + commonPart * Math.cos(phi+PI_HALF);
        double v1y = fraction * Math.sin(phi) + commonPart * Math.sin(phi+PI_HALF);
        return new DoublePoint(v1x, v1y);
    }

    public static void repositionCircle(Circle c){
        c.y = c.movement.line.forX(c.x);
    }
    public static void recalculateLine(Circle c){
        Line line = c.movement.line;
        c.movement.line = new Line(line.a, c.y - line.a*c.x);
    }

    public static void moveCircles(List<Circle> circleList){
        for (Circle c : circleList){
            moveCircle(c);
        }
    }

    private static void moveCircle(Circle c){
        CircleMovement movement = c.movement;
        DoublePoint next = CircleAnimation.findNextPoint(movement.line, new DoublePoint(c.x, c.y), movement.distance, movement.increasingX);
        if(next.isNaN()){
            recalculateLine(c);
            next = CircleAnimation.findNextPoint(movement.line, new DoublePoint(c.x, c.y), movement.distance, movement.increasingX);
        }
        c.x = next.x;
        c.y = next.y;
    }

    private static int minBruteForceCollisionChecks(int circles){
        return (circles-1)*circles/2;
    }
    public static DoublePoint generateCircleCoordinates(int width, int height, Line line){
        Extreme minMax = findMaxAndMin(width, height, line);
        double x = randomIntWithinRange((int) minMax.min, (int) minMax.max);
        return new DoublePoint(x, line.forX(x));
    }

    public static Extreme findMaxAndMin(int width, int height, Line line){
        //add crossing points (only opposite sides should allow 45 deg lines)
        TwoDoublesList list = new TwoDoublesList();
        double xTop = line.forY(height);
        double xBot = line.forY(0);
        double yLeft = line.forX(0);
        double yRight = line.forX(width);

        if(0 <= xTop && xTop <= width){
            list.add(xTop);
        }
        if(0 <= xBot && xBot <= width){
            list.add(xBot);
        }
        if(0 < yLeft && yLeft < height){
            list.add(0);
        }
        if(0 < yRight && yRight < height){
            list.add(width);
        }

        Extreme extreme = new Extreme(list.first(), list.second());
        list = null;
        return extreme;
    }
    public static DoublePoint findNextPoint(DoublePoint knownPoint, CircleMovement movement){
        return findNextPoint(movement.line, knownPoint, movement.distance, movement.increasingX);
    }

    public static DoublePoint findNextPoint(Line line, DoublePoint knownPoint, double distance, boolean increasingX){
        //NP known point
        //KL unknown
        //L = a*K + b
        double N = knownPoint.x;
        double P = knownPoint.y;
        double slope = line.a;
        double b = line.b;
        double solution = Maths.solveQuadraticForDelta(
                1+slope*slope,
                -2*N - 2*P*slope + 2*slope*b,
                -2*P*b + b*b - distance*distance + P*P + N*N,
                increasingX);
        return new DoublePoint(solution, line.forX(solution));
    }
    private static int randomIntWithinRange(int min, int max){
        int bound = max - min;
        if(bound <= 0){
            return max;
        }
        //bound must be positive
        return rand.nextInt(bound) + min;
    }

    private static void displayMovementLines(List<Circle> list){
        for(Circle circle : list){
            System.out.println(circle.movement.line);
        }
    }

}

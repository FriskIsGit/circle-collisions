package collide;

import static java.lang.Math.*;

//if rectangle is rotated use formula for distance between a line and a point
public class Shapes{
    public static double distance(int x1, int y1, int x2, int y2){
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }
    //sqrt and double pow
    public static double distance(double x1, double y1, double x2, double y2){
        return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
    }

    public static boolean isCollisionPossible(Circle c1, Circle c2){
        double diff = abs(c1.x-c2.x);
        return !(c1.r + c2.r < diff);
    }

    public static boolean collision(Circle circle1, Circle circle2){
        double x_diff = circle2.x - circle1.x;
        double y_diff = circle2.y - circle1.y;
        double r_sum  = circle1.r + circle2.r;
        return x_diff*x_diff + y_diff*y_diff <= r_sum*r_sum;
    }
    public static boolean collision(Rectangle rectangle1, Rectangle rectangle2){
        if(rectangle1.x + rectangle1.width < rectangle2.x || rectangle2.x + rectangle2.width < rectangle1.x){
            return false;
        }
        if(rectangle1.y + rectangle1.height < rectangle2.y || rectangle2.y + rectangle2.height < rectangle1.y){
            return false;
        }
        return true;
    }
    /**
     * Performs accurate collision check
     * Touching shapes count as a collision
     * Solution doesn't account for rotation of the rectangle
     * @param rectangle, circle
     * @return true if collision between shapes occurred, false otherwise
     */
    public static boolean collision(Rectangle rectangle, Circle circle){
        byte xRelative, yRelative;

        //left x
        if(circle.x < rectangle.x) xRelative = 0;
        //right x
        else if(rectangle.x + rectangle.width < circle.x){
            xRelative = 2;
        }
        //center x (inclusive)
        else xRelative = 1;

        //bottom y
        if(circle.y < rectangle.y) yRelative = 0;
        //top y
        else if(rectangle.y + rectangle.height < circle.y){
            yRelative = 2;
        }
        //center y (inclusive)
        else yRelative = 1;

        //9 possibilities
        if(xRelative == 1 && xRelative == yRelative){
            //circle's center is within the rectangle
            return true;
        }
        double distance;
        //bottom left corner
        if(xRelative == 0 && yRelative == 0){
            distance = distance(rectangle.x, rectangle.y, circle.x, circle.y);
        }
        //bottom edge
        else if(xRelative == 1 && yRelative == 0){
            distance = abs(rectangle.y - circle.y);
        }
        //bottom right corner
        else if(xRelative == 2 && yRelative == 0){
            distance = distance(rectangle.x + rectangle.width, rectangle.y, circle.x, circle.y);
        }
        //left edge
        else if(xRelative == 0 && yRelative == 1){
            distance = abs(rectangle.x - circle.x);
        }
        //right edge
        else if(xRelative == 2 && yRelative == 1){
            distance = abs(rectangle.x + rectangle.width - circle.x);
        }
        //yRelative is known to be 2 now
        //top left corner
        else if(xRelative == 0){
            distance = distance(rectangle.x, rectangle.y + rectangle.height, circle.x, circle.y);
        }
        //top edge
        else if(xRelative == 1){
            distance = abs(rectangle.y + rectangle.height - circle.y);
        }
        //top right corner (atp all other possibilities have been checked)
        else{
            distance = distance(rectangle.x + rectangle.width, rectangle.y + rectangle.height, circle.x, circle.y);
        }

        return distance <= circle.r;
    }
}
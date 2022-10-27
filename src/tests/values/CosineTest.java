package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.TriplePointAngle;

public class CosineTest{
    @Test
    public void test1(){
        DoublePoint p1 = new DoublePoint(2,3);
        DoublePoint p2 = new DoublePoint(-3, -4);
        DoublePoint p3 = new DoublePoint(4, -2);
        double angle = toCosineAngle(p1,p2,p3);
        System.out.println(angle);
        System.out.println("To angle: " + Math.toDegrees(angle));
    }
    @Test
    public void test60_30(){
        DoublePoint p1 = new DoublePoint(0,0);
        DoublePoint p2 = new DoublePoint(0, 1);
        DoublePoint p3 = new DoublePoint(Math.sqrt(3), 0);
        double angle = toCosineAngle(p1,p2,p3);
        System.out.println(angle);
        System.out.println("In deg: " + Math.toDegrees(angle));
    }
    public double toCosineAngle(DoublePoint p1, DoublePoint p2, DoublePoint p3){
        double a = p1.distance(p2);
        double b = p2.distance(p3);
        double c = p1.distance(p3);
        return TriplePointAngle.cosineLawForRadAngle(a,b,c);
    }
}

package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.Line;

public class PointDistanceToLineTest{
    @Test
    public void test1(){
        DoublePoint dp = new DoublePoint(23, 5);
        Line line = new Line(1,3);
        System.out.println(line.distanceFromPoint(dp));
        DoublePoint maybe = new DoublePoint(12.1, 15.1);
        System.out.println(maybe.distance(dp));
    }
}

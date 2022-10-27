package tests.values;

import gfx.CircleAnimation;
import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.Line;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NextPointOnLineTests{
    @Test
    public void test1(){
        Line line = new Line(1);
        DoublePoint knownPoint = new DoublePoint(0,0);
        DoublePoint expected = new DoublePoint(2,2);
        double distance = 2*Math.sqrt(2);
        DoublePoint unknownPoint = CircleAnimation.findNextPoint(line, knownPoint, distance, true);
        System.out.println(unknownPoint);
        assertTrue(expected.equals(unknownPoint));
    }
    @Test
    public void test2(){
        Line line = new Line(0.7, -4);
        DoublePoint knownPoint = new DoublePoint(0,-4);
        DoublePoint secondPoint = new DoublePoint(8.55, 1.985);
        double distance = knownPoint.distance(secondPoint);
        DoublePoint unknownPoint = CircleAnimation.findNextPoint(line, knownPoint, distance, true);
        assertEquals(secondPoint.x, unknownPoint.x, 0.01);
        assertEquals(secondPoint.y, unknownPoint.y, 0.01);
    }
    @Test
    public void test3(){
        Line line = new Line(6, -13);
        DoublePoint knownPoint = new DoublePoint(0,-4);
        DoublePoint secondPoint = new DoublePoint(8.55, line.forX(8.55));
        double distance = knownPoint.distance(secondPoint);
        DoublePoint unknownPoint = CircleAnimation.findNextPoint(line, knownPoint, distance, true);
        assertTrue(secondPoint.equals(unknownPoint));
    }
}

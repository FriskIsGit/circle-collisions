package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.Line;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointLocationTest{
    @Test
    public void pointLocation1(){
        Line line = new Line(-1);
        DoublePoint p2 = new DoublePoint(-3,5);
        assertFalse(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation2(){
        Line line = new Line(1);
        DoublePoint p2 = new DoublePoint(-3,5);
        assertTrue(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation3(){
        Line line = new Line(3,20);
        DoublePoint p2 = new DoublePoint(-3,5);
        assertFalse(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation4(){
        Line line = new Line(-2,-16);
        DoublePoint p2 = new DoublePoint(-3,5);
        assertFalse(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation5(){
        Line line = new Line(-2,16);
        DoublePoint p2 = new DoublePoint(-3,5);
        assertTrue(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation6(){
        Line line = new Line(0.3, 25);
        DoublePoint p2 = new DoublePoint(5,28);
        assertTrue(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation7(){
        Line line = new Line(0.3, 25);
        DoublePoint p2 = new DoublePoint(5,26);
        assertFalse(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointLocation8(){
        Line line = new Line(0.3, 25);
        DoublePoint p2 = new DoublePoint(5,26);
        assertFalse(line.isPointToTheLeft(p2));
    }
    @Test
    public void pointOnLine(){
        Line line = new Line(4, 3);
        DoublePoint p2 = new DoublePoint(2,11);
        assertFalse(line.isPointToTheLeft(p2));
    }
}

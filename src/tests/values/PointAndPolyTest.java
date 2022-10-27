package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.Polygon;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PointAndPolyTest{
    @Test
    public void test1(){
        DoublePoint p1 = new DoublePoint(-5, 8);
        DoublePoint p2 = new DoublePoint(2, 3);
        DoublePoint p3 = new DoublePoint(10, 10);
        DoublePoint p4 = new DoublePoint(7, -8);
        DoublePoint p5 = new DoublePoint(-11, -13);
        DoublePoint p6 = new DoublePoint(-2, -3);
        Polygon poly = new Polygon(p1, p2, p3, p4, p5, p6);

        assertTrue(poly.isInsidePoly(new DoublePoint(2, -5)));
        assertFalse(poly.isInsidePoly(new DoublePoint(-2.8, -1.2)));
        assertFalse(poly.isInsidePoly(new DoublePoint(13, -5)));
        assertTrue(poly.isInsidePoly(new DoublePoint(-5, 8)));
        assertFalse(poly.isInsidePoly(new DoublePoint(-5.5, 9.834)));
    }
    @Test
    public void test2(){
        DoublePoint p1 = new DoublePoint(-5,8);
        DoublePoint p2 = new DoublePoint(2,3);
        DoublePoint p3 = new DoublePoint(10,10);
        DoublePoint p4 = new DoublePoint(7,-8);
        DoublePoint p5 = new DoublePoint(-11,-13);
        DoublePoint p6 = new DoublePoint(-2,-3);
        Polygon poly = new Polygon(p1,p2,p3,p4,p5,p6);
        assertFalse(poly.isInsidePoly(new DoublePoint(12.1,-4.11)));
        assertFalse(poly.isInsidePoly(new DoublePoint(-2.55,-0.981)));
        assertFalse(poly.isInsidePoly(new DoublePoint(10,-7.169)));

    }
    @Test
    public void test3(){
        DoublePoint p1 = new DoublePoint(-5,8);
        DoublePoint p2 = new DoublePoint(2,3);
        DoublePoint p3 = new DoublePoint(10,10);
        DoublePoint p4 = new DoublePoint(7,-8);
        DoublePoint p5 = new DoublePoint(-11,-13);
        DoublePoint p6 = new DoublePoint(-2,-3);
        Polygon poly = new Polygon(p1,p2,p3,p4,p5,p6);
        assertTrue(poly.isInsidePoly(new DoublePoint(-3,2.2)));
    }
}

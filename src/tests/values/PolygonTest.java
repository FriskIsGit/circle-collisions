package tests.values;

import org.junit.Test;
import utilities.structs.*;

import static org.junit.Assert.*;

public class PolygonTest{
    @Test
    public void test1(){
        Polygon poly = new Polygon(new DoublePoint(23,3), new DoublePoint(-2, 2), new DoublePoint(7,-3));
        System.out.println(poly.centroid());
    }

    @Test
    public void test2(){
        DoublePoint p1 = new DoublePoint(4,7);
        DoublePoint p2 = new DoublePoint(-5,5);
        DoublePoint p3 = new DoublePoint(-5,3);
        DoublePoint p4 = new DoublePoint(-2,-3);
        Polygon poly = new Polygon(p1,p2,p3,p4);
    }

    @Test
    public void convexPolyCentroidTest(){
        DoublePoint p1 = new DoublePoint(4,7);
        DoublePoint p2 = new DoublePoint(-5,5);
        DoublePoint p3 = new DoublePoint(-5,3);
        DoublePoint p4 = new DoublePoint(-2,-3);
        Polygon poly = new Polygon(p1,p2,p3,p4);
        System.out.println("Centroid: " + poly.centroid());
    }
    @Test
    public void concave(){
        DoublePoint p1 = new DoublePoint(-3,7);
        DoublePoint p2 = new DoublePoint(0,2);
        DoublePoint p3 = new DoublePoint(3,7);
        DoublePoint p4 = new DoublePoint(0,-4);
        Polygon poly = new Polygon(p1, p2, p3, p4);
        System.out.println(poly);
        assertFalse(poly.isInsidePoly(new DoublePoint(-2, -4)));
    }
    @Test
    public void includedPointTest(){
        DoublePoint p1 = new DoublePoint(2,3);
        DoublePoint p2 = new DoublePoint(-5,2);
        DoublePoint p3 = new DoublePoint(-5,6);
        DoublePoint p4 = new DoublePoint(-1.5,4.5);
        Polygon poly = new Polygon(p1, p2, p3, p4);
        System.out.println(poly);
        assertTrue(poly.isInsidePoly(new DoublePoint(-1, 4)));
        assertFalse(poly.isInsidePoly(new DoublePoint(-2, 6)));
        assertFalse(poly.isInsidePoly(new DoublePoint(-6, 2)));
        assertTrue(poly.isInsidePoly(new DoublePoint(-4, 4)));

    }
    @Test
    public void _6points(){
        DoublePoint p1 = new DoublePoint(-5,8);
        DoublePoint p2 = new DoublePoint(2,3);
        DoublePoint p3 = new DoublePoint(10,10);
        DoublePoint p4 = new DoublePoint(7,-8);
        DoublePoint p5 = new DoublePoint(-11,-13);
        DoublePoint p6 = new DoublePoint(-2,-3);
        Polygon poly = new Polygon(p1, p2, p3, p4, p5, p6);
        System.out.println(poly.lineSegments);
        assertTrue(poly.isInsidePoly(new DoublePoint(-2.5, 2.5)));
        assertTrue(poly.isInsidePoly(new DoublePoint(2.5, -2.5)));
        assertFalse(poly.isInsidePoly(new DoublePoint(-7.5, 2.5)));
        assertTrue(poly.isInsidePoly(new DoublePoint(-5, 8)));
        System.out.println(poly);
    }
}

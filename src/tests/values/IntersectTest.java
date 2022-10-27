package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.Line;

import static org.junit.Assert.*;

public class IntersectTest{
    @Test
    public void test1(){
        Line l = Line.notLine(4);
        Line line = new Line(0.3, 12);
        DoublePoint expected = new DoublePoint(4, 13.2);
        assertTrue(expected.equals(line.intersect(l)));
        assertTrue(expected.equals(l.intersect(line)));
    }
    @Test
    public void test2(){
        Line l = Line.notLine(-3);
        Line line = new Line(-2);
        DoublePoint expected = new DoublePoint(-3, 6);
        assertTrue(expected.equals(line.intersect(l)));
        assertTrue(expected.equals(l.intersect(line)));
    }
    @Test
    public void test3(){
        Line line1 = new Line(-0.5, 9);
        Line line2 = new Line(-2);
        DoublePoint expected = new DoublePoint(-6, 12);
        assertTrue(expected.equals(line1.intersect(line2)));
        assertTrue(expected.equals(line2.intersect(line1)));
    }
    @Test
    public void test4(){
        Line naf1 = Line.notLine(4);
        Line naf2 = Line.notLine(-1);
        DoublePoint dp = naf1.intersect(naf2);
        assertTrue(dp.isNaN());
        System.out.println("NAF NaN point: " + dp);
    }
    @Test
    public void a0Intersect(){
        Line line1 = new Line(0, 9);
        Line line2 = new Line(0, 3);
        DoublePoint dp = line1.intersect(line2);
        assertTrue(dp.isNaN());
        System.out.println("NAF NaN point: " + dp);
    }
    @Test
    public void a0b0Intersect(){
        Line line1 = new Line(0, 0);
        Line line2 = new Line(0, 0);
        DoublePoint dp = line1.intersect(line2);
        assertTrue(dp.isNaN());
        System.out.println("NAF NaN point: " + dp);
    }
}

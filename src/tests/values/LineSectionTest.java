package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;
import utilities.structs.LineSegment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LineSectionTest{
    @Test
    public void test1(){
        DoublePoint dp1 = new DoublePoint(2,23);
        DoublePoint dp2 = new DoublePoint(-3,-3);
        LineSegment section1 = new LineSegment(dp1, dp2);
        System.out.println(section1);

        DoublePoint p1 = new DoublePoint(-2, 30);
        DoublePoint p2 = new DoublePoint(4, 0);
        LineSegment section2 = new LineSegment(p1, p2);
        System.out.println(section2);

        assertTrue(section1.crosses(section2));
    }
    @Test
    public void test2(){
        DoublePoint dp1 = new DoublePoint(2,23);
        DoublePoint dp2 = new DoublePoint(-3,-3);
        LineSegment section1 = new LineSegment(dp1, dp2);
        System.out.println(section1);

        DoublePoint p1 = new DoublePoint(0, -20);
        DoublePoint p2 = new DoublePoint(-4, 0);
        LineSegment section2 = new LineSegment(p1, p2);
        System.out.println(section2);

        assertFalse(section1.crosses(section2));
    }
}

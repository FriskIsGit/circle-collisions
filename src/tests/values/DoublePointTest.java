package tests.values;

import org.junit.Test;
import utilities.structs.DoublePoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DoublePointTest{
    @Test
    public void test1(){
        DoublePoint p1 = new DoublePoint(23,6);
        DoublePoint p2 = new DoublePoint(47,-335);
        double expectedDistance = 341.8435;
        double actualDistance = p1.distance(p2);
        assertEquals(expectedDistance, actualDistance, 0.001);
    }
    @Test
public void betweenPointTest(){
        DoublePoint p1 = new DoublePoint(2,3);
        DoublePoint p2 = new DoublePoint(-5,6);
        DoublePoint between = p1.pointBetween(p2);
        assertTrue(new DoublePoint(-1.5, 4.5).equals(between));
    }
}

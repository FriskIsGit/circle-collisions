package tests.values;

import gfx.CircleAnimation;
import org.junit.Test;
import utilities.structs.Extreme;
import utilities.structs.Line;
import utilities.Maths;

import static org.junit.Assert.assertTrue;

public class LinesCrossPointTests{
    @Test
    public void test1(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(-2,120));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(10,60)));
    }
    @Test
    public void test2(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(1,-150));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(150,200)));
    }
    @Test
    public void test3(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(-0.55,61.8));
        range.max = Maths.to1DecPlace(range.max);
        System.out.println(range);
        assertTrue(range.equals(new Extreme(0,112.3)));
    }
    @Test
    public void test4(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(-0.2,80));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(0,200)));
    }
    @Test
    public void test5(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(0.85, -58));
        range.min = Maths.to3DecPlaces(range.min);
        range.max = Maths.to3DecPlaces(range.max);
        System.out.println(range);
        assertTrue(range.equals(new Extreme(68.235,185.882)));
    }
    @Test
    public void test6(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(0.35,18));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(0,200)));
    }
    @Test
    public void angle45TL(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(-1, 100));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(0,100)));
    }
    @Test
    public void angle45TR(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(1, -100));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(100,200)));
    }
    @Test
    public void angle45BL(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(1));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(0,100)));
    }
    @Test
    public void angle45BR(){
        Extreme range = CircleAnimation.findMaxAndMin(200, 100, new Line(-1, 200));
        System.out.println(range);
        assertTrue(range.equals(new Extreme(100,200)));
    }
}

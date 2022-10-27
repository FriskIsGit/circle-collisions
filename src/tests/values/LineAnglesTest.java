package tests.values;

import org.junit.Test;
import utilities.structs.Line;

import static org.junit.Assert.assertEquals;

public class LineAnglesTest{
    @Test
    public void tan15Deg(){
        //I quarter
        Line line1 = new Line(1);
        Line line2 = new Line(0.577);
        double deg1 = line1.getAngDeg(line2);
        double deg2 = line2.getAngDeg(line1);
        assertEquals(15, deg1, 0.1);
        assertEquals(-15, deg2, 0.1);
        System.out.println(deg1);
        System.out.println(deg2);
    }
    @Test
    public void tan30Deg(){
        Line line1 = new Line(1.732);
        Line line2 = new Line(0.577);
        double deg1 = line1.getAngDeg(line2);
        double deg2 = line2.getAngDeg(line1);
        assertEquals(30, deg1, 0.1);
        assertEquals(-30, deg2, 0.1);
        System.out.println(deg1);
        System.out.println(deg2);
    }
    @Test
    public void oppositeSigns(){
        //-0,0087268677907587893345361980612
        //0,0087268677907587893345361980612
        Line line1 = new Line(-0.5);
        Line line2 = new Line(0.5);
        double deg1 = line1.getAngDeg(line2);
        double deg2 = line2.getAngDeg(line1);
        System.out.println(deg1);
        System.out.println(deg2);
        assertEquals(-53.13, deg1, 0.1);
        assertEquals(53.13, deg2, 0.1);
    }
    @Test
    public void negativeSlopes(){
        Line line1 = new Line(-1.732);
        Line line2 = new Line(-0.577);
        double deg1 = line1.getAngDeg(line2);
        double deg2 = line2.getAngDeg(line1);
        System.out.println(deg1);
        System.out.println(deg2);
        assertEquals(-30, deg1, 0.1);
        assertEquals(30, deg2, 0.1);
    }
    //structure test
    @Test
    public void lineAngle1(){
        Line line1 = new Line(-1.732);
        Line line2 = new Line(-0.577);
        System.out.println();
    }
}

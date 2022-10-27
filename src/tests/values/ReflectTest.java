package tests.values;

import org.junit.Test;
import utilities.structs.Line;

import static org.junit.Assert.assertTrue;

public class ReflectTest{
    @Test
    public void test1(){
        Line expected = new Line(-0.5, 12);
        Line nafLine = Line.notLine(3);
        Line line = new Line(0.5, 9);
        Line reflected1 = line.reflectOff(nafLine);
        Line reflected2 = nafLine.reflectOff(line);
        System.out.println(reflected1);
        System.out.println(reflected2);
        assertTrue(expected.equals(reflected1));
        assertTrue(expected.equals(reflected2));
    }
    @Test
    public void test2(){
        Line expected = new Line(2, 34);
        Line nafLine = Line.notLine(-8);
        Line line = new Line(-2, 2);
        assertTrue(expected.equals(line.reflectOff(nafLine)));
    }
    @Test
    public void test3(){
        Line expected = new Line(0);
        Line nafLine = Line.notLine(-1);
        Line line = new Line(0);
        assertTrue(expected.equals(line.reflectOff(nafLine)));
        assertTrue(expected.equals(nafLine.reflectOff(line)));
    }
}

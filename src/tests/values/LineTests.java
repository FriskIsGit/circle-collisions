package tests.values;

import org.junit.*;
import utilities.structs.IntPoint;
import utilities.structs.Line;
import utilities.Maths;

import static org.junit.Assert.*;

public class LineTests{
    @Test
    public void test1(){
        Line line1 = Line.linearFunc(1);
        Line line2 = Line.linearFunc(Math.sqrt(3)/3);

        Line reflectedLine = line1.copy().reflectOff(line2);
        reflectedLine.a = Maths.to3DecPlaces(reflectedLine.a);

        printLineInfo(line1, line2, reflectedLine);
        Line expectedLine = new Line(0.267);
        assertTrue(reflectedLine.equals(expectedLine));
    }
    @Test
    public void test2(){
        Line line1 = Line.linearFunc(Math.sqrt(3)/3);
        Line line2 = Line.linearFunc(1);

        Line reflectedLine = line1.copy().reflectOff(line2);
        reflectedLine.a = Maths.to3DecPlaces(reflectedLine.a);

        printLineInfo(line1, line2, reflectedLine);
        Line expectedLine = new Line(1.732);
        assertTrue(reflectedLine.equals(expectedLine));
    }
    @Test
    public void test3(){
        Line line1 = Line.linearFunc(-0.3);
        Line line2 = Line.linearFunc(-0.22);

        Line reflectedLine = line1.copy().reflectOff(line2);
        reflectedLine.a = Maths.to3DecPlaces(reflectedLine.a);

        printLineInfo(line1, line2, reflectedLine);
        Line expectedLine = new Line(-0.142, 0);
        assertTrue(reflectedLine.equals(expectedLine));
    }
    @Test
    public void test4(){
        Line line1 = Line.linearFunc(-1, 23);
        Line line2 = Line.linearFunc(0, 5);

        Line reflectedLine = line1.copy().reflectOff(line2);

        printLineInfo(line1, line2, reflectedLine);
        Line expectedLine = new Line(1, -13);
        assertTrue(reflectedLine.equals(expectedLine));
    }
    @Test
    public void mutualReflect1(){
        Line origLine1 = new Line(1,20);
        Line origLine2 = new Line(-1,35);
        Line tempLine  = origLine1.copy();

        tempLine.reflectOff(origLine2);
        origLine2.reflectOff(origLine1);
        origLine1 = tempLine;

        assertTrue(origLine1.equals(tempLine));
    }
    @Test
    public void mutualReflect2(){
        Line origLine1 = new Line(0.6,20);
        Line origLine2 = new Line(-1,35);
        Line reflect1 = origLine1.reflectOff(origLine2);
        reflect1.a = Maths.to3DecPlaces(reflect1.a);
        assertTrue(reflect1.equals(new Line(1.666, 10)));
    }

    @Test
    public void reflectOffYAxis1(){
        Line line = new Line(1, 6);
        Line reflected = line.reflectOffYAxisAt(4);
        Line expectedLine = new Line(-1, 14);
        assertTrue(expectedLine.equals(reflected));
    }
    @Test
    public void reflectOffYAxis2(){
        Line line = new Line(2, 9);
        Line reflected = line.reflectOffYAxisAt(-8);
        Line expectedLine = new Line(-2, -23);
        assertTrue(expectedLine.equals(reflected));
    }
    @Test
    public void reflectOffYAxis3(){
        Line line = new Line(-4, 120);
        Line reflected = line.reflectOffYAxisAt(20);
        Line expectedLine = new Line(4, -40);
        assertTrue(expectedLine.equals(reflected));
    }
    @Test
    public void reflectTopX(){
        Line line = new Line(-3, 4.5);
        Line reflected = line.reflectOffXAxisAt(25);
        Line expectedLine = new Line(3, 45.5);
        assertTrue(expectedLine.equals(reflected));
    }
    @Test
    public void reflectBottomX(){

        Line line = new Line(1);
        Line reflected = line.reflectOffXAxisAt(0);
        Line expectedLine = new Line(-1);
        assertTrue(expectedLine.equals(reflected));
    }
    @Test
    public void perpendicular(){
        Line line = new Line(0.5,20);
        Line perpendicular = line.perpendicular();
        System.out.println(perpendicular);
        assertTrue(new Line(-2, 20).equals(perpendicular));
    }
    @Test
    public void samePoints(){
        IntPoint p1 = new IntPoint(2,5);
        IntPoint p2 = new IntPoint(2,5);
        Line line = Line.linearFunc(p1, p2);
        assertTrue(Line.notLine(2).equals(line));
        System.out.println(line);
    }
    @Test
    public void sameXDiffY(){
        IntPoint p1 = new IntPoint(2,5);
        IntPoint p2 = new IntPoint(2,1);
        Line line = Line.linearFunc(p1, p2);
        assertTrue(Line.notLine(2).equals(line));
        System.out.println(line);
    }
    @Test
    public void sameYDiffX(){
        IntPoint p1 = new IntPoint(2,3);
        IntPoint p2 = new IntPoint(1,3);
        Line line = Line.linearFunc(p1, p2);
        assertTrue(new Line(0, 3).equals(line));
        System.out.println(line);
    }

    private void printLineInfo(Line line1, Line line2, Line reflectedLine){
        System.out.println("Intersect: " + line1.intersect(line2));
        System.out.println("Angle between lines: " + line1.getAngDeg(line2));
        System.out.println("Reflected: " + reflectedLine);
    }

    @Test
    public void otherTest(){
        Line line1 = Line.linearFunc(new IntPoint(4,7), new IntPoint(2,-7));
        Line line2 = Line.linearFunc(new IntPoint(-3,5), new IntPoint(6,-4));
        System.out.println(line1);
        System.out.println(line2);
        Line reflectedLine = line1.reflectOff(line2);

        reflectedLine.a = Maths.to3DecPlaces(reflectedLine.a);
        reflectedLine.b = Maths.to3DecPlaces(reflectedLine.b);

        printLineInfo(line1, line2, reflectedLine);
        Line expectedLine = new Line(0.142, -1.285);
        assertTrue(reflectedLine.equals(expectedLine));
    }

}

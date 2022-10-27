package tests.values;

import collide.Circle;
import org.junit.Test;
import utilities.structs.Line;

import static org.junit.Assert.assertTrue;

public class LineTranslationTests{
    @Test
    public void test1(){
        Line line1 = new Line(-2, 8);
        Line line2 = new Line(2, -4);
        Circle circle = new Circle(3,2, 2);
        int y = 0;
        Line reflected1 = line1.copy().reflectOffXAxisAt(0);
        Line reflected2 = line2.copy().reflectOffXAxisAt(0);
        System.out.println(reflected1);
        System.out.println(reflected2);
        assertTrue(new Line(2, -8).equals(reflected1));
        assertTrue(new Line(-2, 4).equals(reflected2));
        //slope<0 -> reflect -> move b - up
        reflected1.b = reflected1.b + circle.r*2;
        assertTrue(line2.equals(reflected1));

        //slope>0 -> reflect -> move b - up
        reflected2.b = reflected2.b + circle.r*2;
        assertTrue(line1.equals(reflected2));
    }
    @Test
    public void leftReflect(){
        Line line1 = new Line(1, 7.5);
        Line line2 = new Line(-1, 7.5);
        Circle circle = new Circle(2,5.5, 2);
        int x = 0;
        Line reflected1 = line1.copy().reflectOffYAxisAt(0);
        Line reflected2 = line2.copy().reflectOffYAxisAt(0);
        System.out.println(reflected1);
        System.out.println(reflected2);
        assertTrue(reflected1.equals(line2));
        assertTrue(reflected2.equals(line1));
        //slope<0 -> reflect -> move b - down
        reflected2.b = reflected2.b - circle.r*2;
        assertTrue(reflected2.equals(new Line(1, 3.5)));

        //slope>0 -> reflect -> move b - up
    }
}

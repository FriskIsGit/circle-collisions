package tests.values;

import org.junit.Test;
import utilities.structs.Line;

public class LineRadianTest{
    @Test
    public void test1(){
        Line line = new Line(1);
        Line y0Line = new Line(0);
        double rad1 = line.getAngRad(y0Line);
        double rad2 = y0Line.getAngRad(line);
        System.out.println(rad1);
        System.out.println(rad2);
    }
}

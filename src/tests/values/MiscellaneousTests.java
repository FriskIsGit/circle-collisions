package tests.values;

import org.junit.Test;
import utilities.Maths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MiscellaneousTests{
    @Test
    public void floatProblem1(){
        double x = 37.9999990009;
        double expected = 38;
        x = Maths.remFloatError(x);
        System.out.println(x);
        assertEquals(expected, x, 0);
    }
    @Test
    public void floatProblem2(){
        double x = -37.9999990009;
        double expected = -38;
        x = Maths.remFloatError(x);
        System.out.println(x);
        assertEquals(expected, x, 0);
    }
    @Test
    public void floatProblem3(){
        double res = 1.11D + 2.22D;
        double multiply = res*3;
        System.out.println(res + 1.1);
        System.out.println(multiply);
    }

    @Test
    public void isCollisionElastic(){
        assertTrue(isElasticHeadOn(2,1, 1,1,1/3D, 5/3D));
        assertTrue(isElasticHeadOn(1,1, 1,0,0, 1));
        assertTrue(isElasticHeadOn(1,1, 1,0.5,0.5, 1));
    }
    private static boolean isElasticHeadOn(double m1, double m2, double bv1, double bv2, double av1, double av2){
        double momBefore = m1*bv1 - m2*bv2;
        double momAfter  = m2*av2 - m1*av1;
        System.out.println(momBefore);
        System.out.println(momAfter);
        return momBefore == momAfter;
    }

}

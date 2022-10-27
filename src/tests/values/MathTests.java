package tests.values;

import org.junit.Test;
import utilities.Maths;
import utilities.structs.TwoDoublesList;

import static org.junit.Assert.assertEquals;

public class MathTests{
    @Test
    public void test1(){
        TwoDoublesList list = Maths.solveQuadratic(2,5,3.13);
        assertEquals(0, list.size());
    }
    @Test
    public void test2(){
        TwoDoublesList list = Maths.solveQuadratic(2,5,3.1);
        assertEquals(2, list.size());
    }
    @Test
    public void test3(){
        TwoDoublesList list = Maths.solveQuadratic(1,-3,1);
        assertEquals(2, list.size());
        assertEquals( 0.382D, Maths.to3DecPlaces(list.first()),0.01);
        assertEquals( 2.618D, Maths.to3DecPlaces(list.second()), 0.01);
    }
    @Test
    public void test4(){
        TwoDoublesList list = Maths.solveQuadratic(-0.5,+3,-3);
        assertEquals(2, list.size());
        assertEquals( 1.268, list.second(),0.01);
        assertEquals( 4.732, list.first(), 0.01);
    }
    @Test
    public void test5(){
        TwoDoublesList list = Maths.solveQuadratic(0.5,+3,-3);
        assertEquals(2, list.size());
        assertEquals(-6.873,  list.first(),0.01);
        assertEquals(0.873,  list.second(), 0.01);
    }
    @Test
    public void test6(){
        double solutionNegative = Maths.solveQuadraticForDelta(0.5,3,-3, false);
        double solutionPositive = Maths.solveQuadraticForDelta(0.5,3,-3, true);
        assertEquals(-6.873,  solutionNegative, 0.01);
        assertEquals(0.873,  solutionPositive, 0.01);
    }
    @Test
    public void test7(){
        double solutionX = Maths.solveQuadraticForDelta(2,-2,-7, false);
        System.out.println(solutionX);
        assertEquals( -1.436,  solutionX, 0.01);
    }
    @Test
    public void test8(){
        TwoDoublesList list = Maths.solveQuadratic(4,-6,9);
        System.out.println(list);
    }
    @Test
    public void oneAnswer(){
        TwoDoublesList list = new TwoDoublesList();
        list.add(-0.9);
        assertEquals(1, list.size());
        assertEquals("[-0.9]", list.toString());
    }
    @Test
    public void shiftDivision(){
        int xInt = 16;
        double xDouble = 16.5;
        assertEquals(xInt>>1, (int)xDouble>>1);
    }
    @Test
    public void shiftMultiplication(){
        int xInt = 16;
        assertEquals(32, xInt<<1);
        assertEquals(64, xInt<<2);
        assertEquals(128, xInt<<3);
        int power = 5;
        assertEquals(32, 2<<(power-1));
    }
    @Test
    public void uuid(){
        Double x = new Double(0);
        double res = x/0D;
        System.out.println(res);
    }
}

package utilities;

import utilities.structs.TwoDoublesList;

public final class Maths{
    private Maths(){
    }
    public static double to3DecPlaces(double d){
        return (int)(d*1000)/1000.0;
    }
    public static double to2DecPlaces(double d){
        return (int)(d*100)/100.0;
    }
    public static double to1DecPlace(double d){
        return (int)(d*10)/10.0;
    }
    public static double remFloatError(double d){
        double mod = d%1;
        if(mod >= 0.999){
            return Math.ceil(d);
        }else if(mod <= -0.999){
            return Math.floor(d);
        }
        return d;
    }
    public static TwoDoublesList solveQuadratic(double a, double b, double c){
        TwoDoublesList solutions = new TwoDoublesList();
        double delta = b*b - 4*a*c;
        if(delta < 0){
            return solutions;
        }
        if(delta == 0){
            double x = -b/2*a;
            solutions.add(x);
            return solutions;
        }
        double sqRootDelta = Math.sqrt(delta);
        double twoA = 2*a;
        solutions.add((-b - sqRootDelta)/twoA);
        solutions.add((-b + sqRootDelta)/twoA);
        return solutions;
    }
    public static double solveQuadraticForDelta(double a, double b, double c, boolean deltaPositive){
        double delta = b*b - 4*a*c;
        double sqRootDelta = Math.sqrt(delta);
        return deltaPositive ? (-b + sqRootDelta)/(2*a) : (-b - sqRootDelta)/(2*a);
    }
    //returns angle in degrees
    public static double tan(double degrees){
        return Math.tan(Math.toRadians(degrees));
    }
    //returns a value in degrees
    public static double arcTan(double value){
        return Math.toDegrees(Math.atan(value));
    }
}

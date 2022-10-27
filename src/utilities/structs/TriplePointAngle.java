package utilities.structs;

public class TriplePointAngle{
    public double angle1, angle2;
    public TriplePointAngle(DoublePoint p1, DoublePoint p2, DoublePoint p3){
        double a = p1.distance(p2);
        double b = p2.distance(p3);
        double c = p3.distance(p1);
        angle1 = Math.toDegrees(cosineLawForRadAngle(a,b,c));
        if(Double.isNaN(angle1)){
            angle1 = 180;
        }
        angle2 = 360 - angle1;
    }
    public double at(int ind){
        return ind == 0 ? angle1 : angle2;
    }

    public static double cosineLawForRadAngle(double a, double b, double c){
        double fraction_top = c*c - a*a - b*b;
        double fraction_bot = -2*a*b;
        double cos_alpha = fraction_top/fraction_bot;
        return Math.acos(cos_alpha);
    }

    @Override
    public String toString(){
        return "[" + angle1 + "°, " + angle2 + "°]";
    }
}

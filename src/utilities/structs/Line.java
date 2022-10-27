package utilities.structs;

import utilities.Maths;

public class Line{
    //not a function
    public boolean NaF = false;
    public double a,b,x;
    public Line(double a, double b){
        this.a = a;
        this.b = b;
    }
    private Line(){
        NaF = true;
    }
    public Line(double a){
        this.a = a;
    }
    public double forX(double x){
        return a*x + b;
    }
    public double forY(double y){
        return (y-b)/a;
    }
    public Line copy(){
        if(NaF){
            return notLine(x);
        }
        return new Line(this.a, this.b);
    }
    public static Line notLine(double x){
        Line l = new Line();
        l.x = x;
        return l;
    }
    public boolean equals(Line anotherLine){
        if(NaF != anotherLine.NaF){
            return false;
        }
        if(NaF){
            return this.x == anotherLine.x;
        }
        return this.a == anotherLine.a && this.b == anotherLine.b;
    }

    public DoublePoint intersect(Line line){
        if(NaF && line.NaF){
            return new DoublePoint(Double.NaN, Double.NaN);
        }
        if(a == 0 && line.a == 0){
            return new DoublePoint(Double.NaN, Double.NaN);
        }
        if(NaF != line.NaF){
            if(NaF){
                return intersectNaF(line, this);
            }
            return intersectNaF(this, line);
        }

        double x = (line.b-b)/(a-line.a);
        double y = a*x + b;
        return new DoublePoint(x, y);
    }

    private static DoublePoint intersectNaF(Line line, Line nafLine){
        return new DoublePoint(nafLine.x, line.forX(nafLine.x));
    }

    public Line reflectOff(Line secondLine){
        if(NaF && secondLine.NaF){
            throw new IllegalStateException("Two NaF's cannot be reflected: " + this + ", " + secondLine);
        }
        if(NaF != secondLine.NaF){
            if(NaF){
                return reflectNaF(secondLine, this);
            }
            return reflectNaF(this, secondLine);
        }
        if(-1/this.a == secondLine.a){
            return this;
        }
        DoublePoint intersect = this.intersect(secondLine);
        double firstSlope = this.a, secondSlope = secondLine.a;
        double tang = (secondSlope - firstSlope)/(1 + (secondSlope * firstSlope));
        tang *= -1;
        double a = (secondSlope - tang)/(1 + (tang * secondSlope));
        double b = intersect.y - a*intersect.x;
        return new Line(a,b);
    }

    private Line reflectNaF(Line line, Line naf){
        DoublePoint intersect = intersectNaF(line, naf);
        Line reflected = new Line(-line.a);
        reflected.b = intersect.y - reflected.a*intersect.x;
        return reflected;
    }
    public double distanceFromPoint(DoublePoint dp){
        if(NaF){
            return Math.abs(dp.x - x);
        }
        return Math.abs(a*dp.x - dp.y + b)/Math.sqrt(a*a + 1);
    }

    public Line reflectOffYAxisAt(double x){
        if(NaF){
            return this;
        }
        double y = this.forX(x);
        Line reflected = new Line(-this.a);
        reflected.b = y - reflected.a*x;
        return reflected;
    }
    public Line reflectOffXAxisAt(double y){
        if(NaF){
            return this;
        }
        double x = this.forY(y);
        Line reflected = new Line(-this.a);
        reflected.b = y - reflected.a*x;
        return reflected;
    }

    public Line perpendicular(){
        if(NaF){
            return new Line(0);
        }
        return new Line(-1/a,b);
    }

    public boolean isPointToTheLeft(DoublePoint point){
        if(NaF){
            return point.x < x;
        }
        double lineY = this.forX(point.x);
        boolean positiveSlope = a > 0;
        if(positiveSlope){
            return lineY < point.y;
        }
        else{
            return point.y < lineY;
        }
    }
    public boolean isPointContained(DoublePoint point){
        if(NaF){
            return point.x == x;
        }
        double lineY = this.forX(point.x);
        return lineY == point.y;
    }
    public static Line linearFunc(double a, double b){
        return new Line(a, b);
    }
    public static Line linearFunc(double a){
        return new Line(a);
    }
    public static Line linearFunc(IntPoint p1, IntPoint p2){
        return linearFunc(p1.x, p1.y, p2.x, p2.y);
    }
    public static Line linearFunc(DoublePoint p1, DoublePoint p2){
        return linearFunc(p1.x, p1.y, p2.x, p2.y);
    }
    public static Line linearFunc(int x, int y, int x2, int y2){
        if(x == x2){
            return Line.notLine(x);
        }
        double a = (y - y2)/(double)(x - x2);
        a = Maths.to3DecPlaces(a);
        double b = y - a*x;
        b = Maths.to3DecPlaces(b);
        return new Line(a,b);
    }
    public static Line linearFunc(double x, double y, double x2, double y2){
        if(x == x2){
            return Line.notLine(x);
        }
        double a = (y - y2)/(x - x2);
        a = Maths.to3DecPlaces(a);
        double b = y - a*x;
        b = Maths.to3DecPlaces(b);
        return new Line(a,b);
    }
    public double getAngDeg(Line otherLine){
        return getAngDeg(a, otherLine.a);
    }
    public double getAngRad(Line otherLine){
        return getAngRad(a, otherLine.a);
    }
    //if both positive - angle is in the 1st quadrant
    //if both negative - angle is in the 2nd quadrant
    //if one  negative - angle is in the 1st and 4th quadrant
    public static double getAngDeg(double a1, double a2){
        return Math.toDegrees(Math.atan(a1) - Math.atan(a2));
    }
    public static double getAngRad(double a1, double a2){
        return Math.atan(a1) - Math.atan(a2);
    }
    public String toString(){
        if(NaF){
            return "x = " + x;
        }
        return "y = " + a + "x" + (b<0 ? " " : " +") + b;
    }
}

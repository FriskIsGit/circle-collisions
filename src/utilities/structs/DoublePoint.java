package utilities.structs;

public class DoublePoint{
    public double x, y;
    public DoublePoint(double x, double y){
        this.x = x;
        this.y = y;
    }
    public boolean equals(DoublePoint aPoint){
        return this.x == aPoint.x && this.y == aPoint.y;
    }
    public DoublePoint pointBetween(DoublePoint aPoint){
        return new DoublePoint((x+aPoint.x)*0.5, (y+aPoint.y)*0.5);
    }
    public double distance(DoublePoint aPoint){
        return Math.sqrt(Math.pow(x - aPoint.x, 2) + Math.pow(y - aPoint.y, 2));
    }
    public boolean isNaN(){
        return x != x || y != y;
    }
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}

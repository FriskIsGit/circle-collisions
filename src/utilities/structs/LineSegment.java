package utilities.structs;

public class LineSegment{
    public Line line;
    public DoublePoint dp1, dp2;
    public LineSegment(DoublePoint dp1, DoublePoint dp2, Line line){
        this.line = line;
        this.dp1 = dp1;
        this.dp2 = dp2;
    }
    public LineSegment(Line line, DoublePoint dp1, DoublePoint dp2){
        this(dp1, dp2, line);
    }

    public LineSegment(DoublePoint dp1, DoublePoint dp2){
        line = Line.linearFunc(dp1, dp2);
        this.dp1 = dp1;
        this.dp2 = dp2;
    }
    public boolean crosses(LineSegment section){
        DoublePoint lineIntersect = this.line.intersect(section.line);
        if(lineIntersect.isNaN()){
            return false;
        }
        boolean between1 = isBetweenTwoPoints(lineIntersect, this.dp1, this.dp2);
        boolean between2 = isBetweenTwoPoints(lineIntersect, section.dp1, section.dp2);
        return between1 && between2;
    }
    public boolean crosses(Line aLine){
        DoublePoint intersect = this.line.intersect(aLine);
        if(intersect.isNaN()){
            return false;
        }
        return LineSegment.isBetweenTwoPoints(intersect, dp1, dp2);
    }
    public static boolean isBetweenTwoPoints(DoublePoint dp, DoublePoint dp1, DoublePoint dp2){
        Extreme x_extreme = new Extreme(dp1.x, dp2.x);
        Extreme y_extreme = new Extreme(dp1.y, dp2.y);
        boolean between_x = x_extreme.min <= dp.x && dp.x <= x_extreme.max;
        boolean between_y = y_extreme.min <= dp.y && dp.y <= y_extreme.max;
        return between_x && between_y;
    }

    @Override
    public String toString(){
        return line.toString() + ", " +  dp1.toString()  + ", " + dp2.toString();
    }
}

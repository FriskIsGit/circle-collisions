package utilities.structs;

import java.util.ArrayList;
import java.util.List;

public class Polygon{
    public final static double DELTA_ACCURACY = 0.001D;
    public DoublePoint[] points;
    public List<LineSegment> lineSegments;
    public Polygon(DoublePoint ... points){
        if(points.length < 3){
            throw new IllegalStateException("Not enough points to make up a polygon");
        }
        this.points = points;
        lineSegments = createLineSegments();
    }

    public boolean isInsidePoly(DoublePoint dp){
        int crossings = 0;
        Line pointLine = new Line(0, dp.y);
        for (LineSegment section : lineSegments){
            DoublePoint intersect = section.line.intersect(pointLine);
            if(intersect.isNaN()){
                continue;
            }
            if(intersect.x < dp.x){
                continue;
            }
            if(LineSegment.isBetweenTwoPoints(intersect, section.dp1, section.dp2)){
                crossings++;
            }
        }
        return crossings%2 == 1;
    }
    public static List<TriplePointAngle> calculateAngles(List<DoublePoint> points){
        List<TriplePointAngle> angles = new ArrayList<>();
        //create angles
        DoublePoint p1, p2, p3;
        int size = points.size();
        for (int i = 0; i < size-2; i++){
            p1 = points.get(i);
            p2 = points.get(i+1);
            p3 = points.get(i+2);
            angles.add(new TriplePointAngle(p1, p2, p3));
        }
        angles.add(new TriplePointAngle(points.get(size-2), points.get(size-1), points.get(0)));
        angles.add(new TriplePointAngle(points.get(size-1), points.get(0), points.get(1)));
        return angles;
    }

    public List<LineSegment> createLineSegments(){
        List<LineSegment> segments = new ArrayList<>();
        int len = points.length;
        for (int i = 0; i < len-1; i++){
            LineSegment lineSection = new LineSegment(points[i], points[i+1]);
            segments.add(lineSection);
        }
        segments.add(new LineSegment(points[0], points[len-1]));
        return segments;
    }

    private static void step(int[] choices, int i){
        if(i == -1){
            return;
        }
        if(choices[i] >= 1){
            choices[i] = 0;
            step(choices, i-1);
            return;
        }
        choices[i]++;
    }

    public static TriplePointAngle[] toArray(List<TriplePointAngle> anglesList, int size){
        TriplePointAngle[] arr = new TriplePointAngle[size];
        for (int i = 0; i < size; i++){
            arr[i] = anglesList.get(i);
        }
        return arr;
    }

    public DoublePoint centroid(){
        double sumX = 0, sumY = 0;
        for (DoublePoint dp : points){
            sumX += dp.x;
            sumY += dp.y;
        }
        return new DoublePoint(sumX/points.length,sumY/points.length);
    }
    public static boolean isDeltaEqual(double val1, double val2){
        double diff = Math.abs(val1-val2);
        return diff <= DELTA_ACCURACY;
    }
    public static boolean isDeltaMore(double more, double less){
        double diff = more - less;
        return diff > DELTA_ACCURACY;
    }
    public static DoublePoint centroid(List<DoublePoint> points){
        double sumX = 0, sumY = 0;
        for (DoublePoint dp : points){
            sumX += dp.x;
            sumY += dp.y;
        }
        return new DoublePoint(sumX/points.size(),sumY/points.size());
    }

    public int totalSumOfInsideDegrees(){
        return totalSumOfInsideDegrees(points.length);
    }

    private static int totalSumOfInsideDegrees(int vertices){
        return (vertices-2)*180;
    }

    public static class Inside{
        public final static int LEFT  = 0;
        public final static int RIGHT = 1;
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < points.length; i++){
            str.append(points[i]);
            if(i != points.length-1){
                str.append(", ");
            }
        }
        return str.toString();
    }
}

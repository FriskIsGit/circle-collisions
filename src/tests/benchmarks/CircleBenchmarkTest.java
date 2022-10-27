package tests.benchmarks;

import collide.Circle;
import collide.Rectangle;
import collide.Shapes;

import java.util.ArrayList;
import java.util.List;

public class CircleBenchmarkTest{
    public static void main(String[] args){
        test1();
    }
    private static void test1(){
        Rectangle rectangle = new Rectangle(1, 2, 4, 2);
        List<Circle> circlesList = new ArrayList<>(8);
        try{
            Thread.sleep(0, 12);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        Circle circle0 = new Circle(5, 1, 1);
        Circle circle1 = new Circle(5.9, 1.6, 1);
        Circle circle2 = new Circle(5.9, 1.5, 1);

        circlesList.add(circle0);
        circlesList.add(circle1);
        circlesList.add(circle2);

        //testCircles(rectangle, circlesList);
        benchmarkCircles(rectangle, circlesList);
    }

    private static void testCircles(Rectangle rectangle, List<Circle> circlesList){
        for (int i = 0; i < circlesList.size(); i++){
            Circle currentCircle = circlesList.get(i);
            boolean occurred = Shapes.collision(rectangle, currentCircle);
            System.out.println("Has #" + (i) + " collision occurred: " + occurred);
        }
    }
    private static void benchmarkCircles(Rectangle rectangle, List<Circle> circlesList){
        final int TESTS = 100;
        Circle currentCircle = circlesList.get(2);

        long sum = 0;
        for (int i = 0; i < TESTS; i++){
            long start = System.nanoTime();
            boolean occurred = Shapes.collision(rectangle, currentCircle);
            long end = System.nanoTime();
            sum += end-start;
        }
        //257/1_000_000_000
        System.out.println("avg: " + sum/TESTS);

    }
}

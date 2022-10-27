package collide;

public class Rectangle extends Figure{
    public Rectangle(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.diagonalHalf = Shapes.distance(centerX(),centerY(), x, y);
    }
    public double diagonalHalf;
    //left bottom corner 0, 0)
    public double width;
    public double height;

    public double centerX(){
        return width/2D + x;
    }
    public double centerY(){
        return height/2D + y;
    }
}
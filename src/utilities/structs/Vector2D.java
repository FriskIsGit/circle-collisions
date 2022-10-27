package utilities.structs;

public class Vector2D{
    public double x, y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "v[" + x + ", " + y + "]";
    }
    public double magnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2D subtract(double val){
        return new Vector2D(x-val, y-val);
    }
    public Vector2D add(double val){
        return new Vector2D(x+val, y+val);
    }
    public Vector2D multiply(double val){
        return new Vector2D(x*val, y*val);
    }
    public double multiply(Vector2D vec){
        return x*vec.x + y*vec.y;
    }

    public Vector2D divide(double val){
        return new Vector2D(x/val, y/val);
    }

    public Vector2D add(Vector2D vec){
        return new Vector2D(x + vec.x, y + vec.y);
    }
}

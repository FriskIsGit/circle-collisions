package utilities.structs;

public class IntPoint{
    public int x, y;

    public IntPoint(int x, int y){
        this.x = x;
        this.y = y;
    }
    public boolean equals(IntPoint aPoint){
        return this.x == aPoint.x && this.y == aPoint.y;
    }
    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}

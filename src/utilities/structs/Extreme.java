package utilities.structs;

public class Extreme{
    public double min, max;
    public Extreme(double val1, double val2){
        if(val1>val2){
            double temp = val1;
            val1 = val2;
            val2 = temp;
        }
        min = val1;
        max = val2;
    }

    public boolean equals(Extreme ext){
        return min == ext.min && max == ext.max;
    }
    @Override
    public String toString(){
        return "[" + min + ", " + max + "]";
    }
}

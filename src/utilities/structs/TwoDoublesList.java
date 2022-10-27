package utilities.structs;

public class TwoDoublesList{
    private int i = 0;
    private final double[] arr = new double[2];

    public void add(double num){
        arr[i++] = num;
    }
    public double first(){
        return arr[0];
    }
    public double second(){
        return arr[1];
    }
    public int size(){
        return i;
    }

    @Override
    public String toString(){
        String s = "[";
        if(0 < i){
            s+=arr[0];
            if(i == 2){
                s += ", " + arr[1];
            }
        }
        s+="]";
        return s;
    }
}

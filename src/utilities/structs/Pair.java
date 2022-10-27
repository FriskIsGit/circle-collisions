package utilities.structs;

public class Pair<K,V>{
    public K key;
    public V val;
    public Pair(K key, V val){
        this.key = key;
        this.val = val;
    }
    @Override
    public String toString(){
        return "[" + key + ", " + val + "]";
    }

}

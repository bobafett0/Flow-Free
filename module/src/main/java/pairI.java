
import java.util.Comparator;

public class pairI < Integer extends Comparable<Integer>,R > implements Comparable<pairI<Integer,R>>  {
    public Integer l;
    public R r;
    public pairI(Integer l, R r){
        this.l = l;
        this.r = r;
    }
    public Integer getL(){ return l; }
    public R getR(){ return r; }
    public void setL(Integer l){ this.l = l; }
    public void setR(R r){ this.r = r; }
    
    public int compareTo( pairI < Integer,R> comp)
    {
    	return this.l.compareTo(comp.getL());
    }
}

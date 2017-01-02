package module.src.main.java;


import java.util.Comparator;

public class PairI < Integer extends Comparable<Integer>,R > implements Comparable<PairI<Integer,R>>  {
    public Integer l;
    public R r;
    public PairI(Integer l, R r){
        this.l = l;
        this.r = r;
    }
    public Integer getL(){ return l; }
    public R getR(){ return r; }
    public void setL(Integer l){ this.l = l; }
    public void setR(R r){ this.r = r; }
    
    public int compareTo( PairI < Integer,R> comp)
    {
    	return this.l.compareTo(comp.getL());
    }
}

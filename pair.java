
public class pair<L,R> {
    public L l;
    public R r;
    public pair(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
    
//    public final Comparator<pair<L, R>> KEY_COMPARATOR = new Comparator<pair<L, R>>() {
//        public int compare(pair<L, R> first, pair<L, R> second) {
//            return ((Object) first.getL()).compareTo((Object)second.getR());
//        }
//    };
}

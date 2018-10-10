
/**
 * Write a description of DepthFilter here.
 * 
 * @Chia-Hao     
 * @2018.10.10
 */
public class DepthFilter implements Filter {
    private double depMin;
    private double depMax;
    public DepthFilter(double min, double max) { 
        depMin = min;
        depMax = max;
    }
    public  boolean satisfies(QuakeEntry qe)
    {
        if( qe.getDepth() >= depMin && qe.getDepth() <= depMax ) {
            return true;
        }
        return false;
    }
    public String getName(){
        return "DepthFilter";
    }
}


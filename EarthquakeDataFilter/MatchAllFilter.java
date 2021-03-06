
/**
 * Write a description of MatchAllFilter here.
 * 
 * @Chia-Hao     
 * @2018.10.10
 */
import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    public MatchAllFilter()
    {
        filters = new ArrayList<Filter>();
    }
    public void addFilter(Filter f)
    {
        filters.add(f);
    }
    public boolean satisfies(QuakeEntry qe) // Dynamic Dispatch
    { 
        for(Filter f : filters)
        {
            if(!f.satisfies(qe)) return false;
        }
        return true;
    }
    public String getName(){
        String name = "";
        for(Filter f : filters){
            name += "\t"+f.getName();
        }
        return name;
    }
}


/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @Chia-Hao
 * @2018.10.12
 */

import java.util.*;
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    //@Override
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        int title_cmp_re = qe1.getInfo().compareTo(qe2.getInfo()); 
        if( title_cmp_re != 0 ) return title_cmp_re;
        
        int depth_cmp_re = Double.compare(qe1.getDepth(), qe2.getDepth());
        if( depth_cmp_re != 0 ) return depth_cmp_re;
        
        return 0;
    }
}

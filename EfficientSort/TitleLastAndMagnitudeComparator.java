
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @Chia-Hao
 * @2018.10.12
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] qe1_words = qe1.getInfo().split("\\W");
        String last_word_qe1 = qe1_words[qe1_words.length-1];
        String[] qe2_words = qe2.getInfo().split("\\W");
        String last_word_qe2 = qe2_words[qe2_words.length-1];
        
        int word_cmp_re = last_word_qe1.compareTo(last_word_qe2);
        if(word_cmp_re != 0) return word_cmp_re;
        
        int mag_cmp_re =  Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
        if( mag_cmp_re != 0 ) return mag_cmp_re;
        
        return 0;
    }
}


/**
 * Write a description of WordFreqMap here.
 * 
 * @Chia-Hao
 * @2018.10.05
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordFreqMap {
    public void countWords()
    {
        FileResource fr = new FileResource();
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        for( String word : fr.words() )
        {   
            word = word.toLowerCase();
            if( hash.keySet().contains(word) ) hash.put(word, hash.get(word)+1);
            else hash.put(word, 1);
        }
        for( String word : hash.keySet() )
        {
            int occur = hash.get(word);
            if( occur > 500 )
            {
                System.out.println(occur+"\t"+word);
            }
        }
        System.out.println("# of unique words : "+hash.size());
    }
    
}

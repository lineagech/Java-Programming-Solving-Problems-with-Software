
/**
 * Write a description of WordLengths here.
 * 
 * @Chia-Hao
 * @2018.10.02
 */

import java.io.*;
import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts)
    {
        int maxLen = counts.length-1;
        for(String word : resource.words())
        {
            int word_len = word.length();
            if( word_len > 0 && !Character.isLetter(word.charAt(0)) ) 
                word_len--;
            if( word_len > 1 && !Character.isLetter(word.charAt(word_len-1)) )
                word_len--;
            if( word_len >= maxLen ) counts[maxLen]++;
            else counts[word_len]++;
        }
    }
    public int indexOfMax(int[] values)
    {
        int max_value = Integer.MIN_VALUE;
        int max_idx = -1;
        for(int i=0; i<values.length; i++)
        {
            if( values[i] > max_value )
            {
                max_value = values[i];
                max_idx = i;
            }
        }
        return max_idx;
    }
    public void testCountWordLengths()
    {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for(int i=0; i<counts.length; i++)
        {
            System.out.println("Length "+i+" : "+counts[i]);
        }
        System.out.println("Max idx is "+indexOfMax(counts));
    }
    public int[] countLetters(String message)
    {   
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for( int i = 0; i < message.length(); i++ )
        {
            char currChar = Character.toLowerCase(message.charAt(i));
            int index = alph.indexOf(currChar);
            if( index != -1 ) {
                counts[index]++;
            }
        }
        return counts;
    }
    public String decrypt(String encrypted)
    {   
        CaesarCipher cc = new CaesarCipher(0);
        int[] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex - 4;
        if( maxDex < 4 )
            dkey = 26-(4-maxDex);
        
        return cc.encrypt(encrypted, 26-dkey);
    }
}

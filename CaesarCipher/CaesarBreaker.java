
/**
 * Write a description of CaesarBreaker here.
 * 
 * @Chia-Hao
 * @2018.10.02
 */
import java.io.*;
import edu.duke.*;
public class CaesarBreaker {
    public int maxIndex(int[] values)
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
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if( maxDex < 4 )
            dkey = 26-(4-maxDex);
        
        return cc.encrypt(encrypted, 26-dkey);
    }
    public String decrypt(String encrypted, int key)
    {   
        CaesarCipher cc = new CaesarCipher(0);
        int dkey = key - 4;
        if( key < 4 )
            dkey = 26-(4-key);
        
        return cc.encrypt(encrypted, 26-dkey);
    }
    public void testDecrypt()
    {
        CaesarCipher cc = new CaesarCipher(0);
        String encrypted = cc.encrypt("eeeeeeeeeeeeeeeees",2);
        System.out.println("Decrypt message "+encrypted+" = "+decrypt(encrypted));
    }
    public String halfOfString(String message, int start)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<message.length(); i+=2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    public void testHalfOfString()
    {
        System.out.println("Half string of Qbkm Zgis starting with 0 = "+
                            halfOfString("Qbkm Zgis",0));
        System.out.println("Half string of Qbkm Zgis starting with 1 = "+
                            halfOfString("Qbkm Zgis",1));                    
    }
    public int getKey(String s)
    {
        int[] freqs = countLetters(s);
        int maxIdx = maxIndex(freqs);
        return maxIdx;
    }
    public String decryptTwoKeys(String encrypted)
    {   
        CaesarCipher cc = new CaesarCipher(0);
        String first_half_str = halfOfString(encrypted, 0);
        String sec_half_str = halfOfString(encrypted, 1);
        
        int key1 = getKey(first_half_str);
        int key2 = getKey(sec_half_str);
        
        int dkey1 = key1 - 4;
        if( key1 < 4 )
            dkey1 = 26-(4-key1);
        int dkey2 = key2 - 4;
        if( key2 < 4 )
            dkey2 = 26-(4-key2);
        System.out.println("Key1 = "+dkey1+"\t"+"Key2 = "+dkey2);
           
        String decrypted = cc.encryptTwoKeys(encrypted, 26-dkey1, 26-dkey2);
        return decrypted;
    }
    public void testDecryptTwoKeys()
    {
        FileResource fr = new FileResource();
        String decrypted = decryptTwoKeys(fr.asString());
        System.out.println("Decrypted = "+decrypted);
    }
    public void testDecryptTwoKeys2()
    {   
        CaesarCipher cc = new CaesarCipher(0);
        String decrypted = cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 24, 6);
        System.out.println("Decrypted Top ncmy qkff vi vguv vbg ycpx = "+decrypted);
    }
    public void testDecryptTwoKeys3()
    {
        String decrypted = decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
        System.out.println("Decrypted Akag tjw Xibhr awoa aoee xakex znxag xwko = "+decrypted);
    }
   
}

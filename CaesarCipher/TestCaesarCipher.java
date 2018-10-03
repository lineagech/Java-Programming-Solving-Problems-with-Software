
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @Chia-Hao
 * @2018.10.02
 */
import java.io.*;
import edu.duke.*;
public class TestCaesarCipher {
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
    
    public void simpleTests()
    {
        FileResource fr = new FileResource();
        String strFr = fr.asString();
        CaesarCipher cc = new CaesarCipher(0);
        String encrypted = cc.encrypt(strFr, 18);
        System.out.println("Encrypted msg = "+encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted msg = "+decrypted);
        
        breakCaesarCipher(encrypted);
    }
    
    public void breakCaesarCipher(String input)
    {   
        CaesarCipher cc = new CaesarCipher(0);
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        
        int dkey = maxDex - 4;
        if( maxDex < 4 )
            dkey = 26-(4-maxDex);
        
        System.out.println("breakCaesarCipher, key = "+dkey);
        String decrypted = cc.encrypt(input, 26-dkey);
        System.out.println("breakCaesarCipher, decrypted = "+decrypted);
    }
    
    public void testQuiz()
    {
        CaesarCipher cc = new CaesarCipher(0);
        String original = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = cc.encrypt(original,15);
        System.out.println("1. Encrypted msg = "+encrypted);
        
        encrypted = cc.encryptTwoKeys(original, 21, 8);
        System.out.println("2. Encrypted msg = "+encrypted);
        
        String decrypted = cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26-14, 26-24);
        System.out.println("6. Top ncmy qkff vi vguv vbg ycpx = "+decrypted);
        
        CaesarBreaker cb = new CaesarBreaker();
        decrypted = cb.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        System.out.println("7. Decrypted Akag tjw Xibhr awoa aoee xakex znxag xwko = "+decrypted);
        
        FileResource fr = new FileResource();
        decrypted = cb.decryptTwoKeys(fr.asString());
        System.out.println("8. Decrypted = "+decrypted);
    }
}

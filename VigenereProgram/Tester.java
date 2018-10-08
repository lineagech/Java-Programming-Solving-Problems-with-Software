
/**
 * Write a description of Tester here.
 * 
 * @Chia-Hao    
 * @2018.10.07
 */
import edu.duke.*;
import java.util.*;

public class Tester {
    public void testVigenereCipher()
    {   
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("../titus-small.txt");
        System.out.println(vc.encrypt(fr.asString()));
    }
    public void testSliceString()
    {   
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println("VB abcdefghijklm slice 0/3 string:"+vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println("VB abcdefghijklm slice 1/3 string:"+vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println("VB abcdefghijklm slice 2/3 string:"+vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println("VB abcdefghijklm slice 0/4 string:"+vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println("VB abcdefghijklm slice 1/4 string:"+vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println("VB abcdefghijklm slice 2/4 string:"+vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println("VB abcdefghijklm slice 3/4 string:"+vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println("VB abcdefghijklm slice 0/5 string:"+vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println("VB abcdefghijklm slice 1/5 string:"+vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println("VB abcdefghijklm slice 2/5 string:"+vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println("VB abcdefghijklm slice 3/5 string:"+vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println("VB abcdefghijklm slice 4/5 string:"+vb.sliceString("abcdefghijklm", 4, 5));
    }
    public void testTryKeyLength()
    {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("../athens_keyflute.txt");
        int[] key = vb.tryKeyLength(fr.asString(), 5, 'e');
        System.out.print("Try keys with athens_keyflute.txt : ");
        for(int i = 0; i < key.length; i++) System.out.print(key[i]+" ");
        System.out.println();
    }
    
    public void testQuiz1()
    {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("../secretmessage1.txt");
        int[] key = vb.tryKeyLength(fr.asString(), 4, 'e');
        System.out.print("Try keys with athens_keyflute.txt : ");
        for(int i = 0; i < key.length; i++) System.out.print(key[i]+" ");
        System.out.println();
        
        vb.breakVigenere();
        
    }
    public void testQuiz2()
    {   
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
        
    }
}

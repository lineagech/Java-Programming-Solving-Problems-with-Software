
/**
 * Write a description of CaesarCipher here.
 * 
 * @Chia-Hao.Chang
 * @2018.10.01
 */

import edu.duke.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key)
    {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
    }
    
    public String encrypt(String input, int key)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
        
        for( int i = 0; i < encrypted.length(); i++ )
        {
            char currChar = encrypted.charAt(i);
            char upCurrChar = currChar;
            if(Character.isLowerCase(currChar))
            {
                upCurrChar = Character.toUpperCase(upCurrChar);
            }
            int idx = alphabet.indexOf(upCurrChar);
            if(idx != -1) // found
            {
                char newChar = shiftedAlphabet.charAt(idx);
                if(Character.isLowerCase(currChar))
                {
                    newChar = Character.toLowerCase(newChar);
                }
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testEncrypt()
    {
        System.out.println("Encrypt FIRST LEGION ATTACK EAST FLANK! = " 
                            + encrypt("FIRST LEGION ATTACK EAST FLANK!", 23) );
        System.out.println("Encrypt First Legion with key 23 = " 
                            + encrypt("First Legion", 23) );
        System.out.println("Encrypt First Legion with key 17 = " 
                            + encrypt("First Legion", 17) ); 
                            
        System.out.println("Encrypt 'At noon be in the conference room with your hat on for a surprise party. YELL LOUD!' with key 15 = " 
                            + encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15) );                     
    }
    public void testCaesar()
    {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 17;
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    public String encryptTwoKeys(String input, int key1, int key2)
    {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);

        for( int i = 0; i < encrypted.length(); i++ )
        {   
            char currChar = encrypted.charAt(i);
            boolean isLower = Character.isLowerCase(currChar);
            if( isLower ) currChar = Character.toUpperCase(currChar);
            
            int idx = alphabet.indexOf(currChar);
            if( idx != -1 ) {
                char newChar;
                if( i % 2 == 0) { // key1
                    newChar = shiftedAlphabet1.charAt(idx);
                } 
                else { // key2
                    newChar = shiftedAlphabet2.charAt(idx);  
                }
                if( isLower ) newChar = Character.toLowerCase(newChar);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    public void testEncryptTwoKeys()
    {
        System.out.println("encrypt two keys 'ABCDE' = " + encryptTwoKeys("ABCDE",1,2));
        System.out.println("Encrypt 'At noon be in the conference room with your hat on for a surprise party. YELL LOUD!' with key1 8, key2 21 = " 
                            + encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21) ); 
    }
    public String decrypt(String input)
    {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input,26 - mainKey);
    }   
}

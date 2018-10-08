import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices)
        {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0; i<klength; i++)
        {
            String slice_i = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(slice_i);
        }
        return key;
    }

    public void breakVigenere() 
    {   
        String[] lanFiles = {"Danish", "Dutch", "English", "French", "German", 
                             "Italian", "Portuguese", "Spanish"};
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        FileResource fr = new FileResource("../secretmessage4.txt");
        String content = fr.asString();
        for( String lanF : lanFiles )
        {
            FileResource fr_dict = new FileResource("dictionaries/"+lanF);
            HashSet<String> dictionary = readDictionary(fr_dict);
            map.put(lanF, dictionary);
            //String decrypted = breakForLanguage(content, dictionary);
            //int[] key = tryKeyLength(content, 4, 'e');
        
            //VigenereCipher vc = new VigenereCipher(key);
            //String decrypted = vc.decrypt(content);
            //System.out.println("--------------------------------------------------");
            //System.out.println(decrypted);
            //String[] lines = decrypted.split(System.getProperty("line.separator"));
            //System.out.println(lines[0]);
        }
        breakForAllLangs(content, map);
    }
    
    public HashSet<String> readDictionary(FileResource fr)
    {
        HashSet<String> set = new HashSet<String>();
        for(String line : fr.lines())
        {
            line = line.toLowerCase();
            set.add(line);
        }
        return set;
    }
    public int countWords(String message, HashSet<String> dictionary)
    {   
        int count = 0;
        String[] words = message.split("\\W+");
        for( String word : words )
        {   
            word = word.toLowerCase();
            if(dictionary.contains(word)) count++;
        }
        return count;
    }
    public String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {   
        int maxKeyLen = 100;
        int keyLen;
        int maxCW = 0;
        int[] realKeys = {};
        String realDecrypted = null;
        for(keyLen = 1; keyLen <= maxKeyLen; keyLen++)
        {   
            char comm_char = mostCommonCharIn(dictionary);
            int[] keys = tryKeyLength(encrypted, keyLen, comm_char);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int cw = countWords(decrypted, dictionary);
            if( cw > maxCW ){
                maxCW = cw;
                realKeys = keys;
                realDecrypted = decrypted;
            }
            //if(keyLen == 38) System.out.println("Valid Word num with key len 38 is :"+cw);
        }
        System.out.println("Key Length is :"+realKeys.length);
        System.out.println("Valid Word num is :"+maxCW);
        return realDecrypted;
    }
    public char mostCommonCharIn(HashSet<String> dictionary)
    {   
        HashMap<Character,Integer> hash = new HashMap<Character,Integer>();
        for(String word : dictionary)
        {
            for(char c : word.toCharArray())
            {
                if(!hash.containsKey(c)){
                    hash.put(c,1);
                }
                else{
                    hash.put(c,hash.get(c)+1);
                }
            }
        }
        int maxCount = 0;
        char mostComChar = '0'; 
        for(char key : hash.keySet())
        {
            if( hash.get(key) > maxCount ){
                maxCount = hash.get(key);
                mostComChar = key;
            }
        }
        return mostComChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages)
    {   
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        for(String lan : languages.keySet())
        {
            String decrypted = breakForLanguage(encrypted, languages.get(lan));
            countMap.put(lan, countWords(decrypted, languages.get(lan)));
            
            String[] lines = decrypted.split(System.getProperty("line.separator"));
            System.out.println("First line of "+lan+" is : "+lines[0]);
        }
        System.out.println(countMap);
    }
    
}

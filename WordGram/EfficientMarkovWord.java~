
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @Chia-Hao 
 * @2018.10.14
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<Integer,ArrayList<String>> map;
    
    public EfficientMarkovWord(int N) {
        myRandom = new Random();
        myOrder = N;
        map = new HashMap<Integer,ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        //String key = myText[index];
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(wg);
            //System.out.println("The key is: "+wg);
            //System.out.println(follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            //key = next;
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        return map.get(kGram.hashCode());
    }
    
    private int indexOf(String[] words, WordGram target, int start)
    {   
        WordGram cmp = new WordGram(words, start, target.length());
        
        for( int i = start; i <= words.length-target.length(); i++ ) {
            //System.out.println(cmp+"\t"+i);
            if( cmp.equals(target) ) 
                return i;
            if( i+target.length() <= words.length-1 )
                cmp = cmp.shiftAdd(words[i+target.length()]);
        }
        return -1;
    }
    
    public void buildMap() {
        WordGram wg = new WordGram(myText, 0, myOrder);
        for( int i = 0; i <= myText.length-myOrder; i++ ) 
        {   
            //System.out.println(wg);
            int hash_code = wg.hashCode();
            if( !map.containsKey(hash_code) ) {
            
                ArrayList<String> follows = new ArrayList<String>();
                int pos = 0;
                while( pos < myText.length )
                {
                    int start = indexOf(myText, wg, pos);
                    if( start == -1 ) break;
                    if( start + myOrder > myText.length-1 ) break;
                    String next = myText[start+myOrder];
                    follows.add(next);
                    pos = start + 1;
                }
                map.put(hash_code, follows);
            }
            if( i+myOrder < myText.length ) 
                wg = wg.shiftAdd(myText[i+myOrder]);
        }
    }
    
    public void printHashMapInfo() {
        if( map.size() <= 10 ) {
            for( int key : map.keySet() )
            {
                System.out.println(key+":"+map.get(key));
            }
        }
        System.out.println("Size is "+map.size());
        int maxKey = getLargestKey();
        System.out.println("Max follows size is "+map.get(maxKey).size());
        for( int key : map.keySet() )
        {
            if( map.get(key).size() == map.get(maxKey).size() ) {
                System.out.println("\twith max follows size key : "+key);
            }
        }
        
    }
    private int getLargestKey() {
        int maxValue = 0;
        int maxKey = -1;
        for( int key : map.keySet() )
        {
            if( map.get(key).size() > maxValue ) {
                maxValue = map.get(key).size();
                maxKey = key;
            }
        }
        return maxKey;
    }
}

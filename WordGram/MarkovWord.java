
/**
 * Write a description of MarkovWord here.
 * 
 * @Chia-Hao 
 * @2018.10.14
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int N) {
        myRandom = new Random();
        myOrder = N;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
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
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while( pos < myText.length )
        {
            int start = indexOf(myText, kGram, pos);
            //System.out.println("Start: "+start+"\t"+myText[start]);
            if( start == -1 ) break;
            //if( start + key.length() >= myText.length-1 ) break;
            if( start + kGram.length() > myText.length-1 ) break;
            
            String next = myText[start+kGram.length()];
            follows.add(next);
            //pos = start + key.length();
            pos = start + 1;
        }
        return follows;
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
}

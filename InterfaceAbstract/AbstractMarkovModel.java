
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @Chia-Hao    
 * @2018.10.13
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> result = new ArrayList<String>();
        int index = myText.indexOf(key, 0);
        while( index != -1 ) {
            if( index + key.length() < myText.length() ) {
                result.add(myText.substring(index+key.length(), index+key.length()+1));
                index = myText.indexOf(key, index+1);
            }
            else {
                break;
            }   
        }
        return result;
    }

}

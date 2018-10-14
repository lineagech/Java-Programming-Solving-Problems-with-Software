
/**
 * Write a description of MarkovModel here.
 * 
 * @Chia-Hao 
 * @2018.10.12
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    private int predictNum;
    
    public MarkovModel(int N) {
        predictNum = N; 
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-predictNum);
        String key = myText.substring(index, index+predictNum);
        sb.append(key);
        for(int k=0; k < numChars-predictNum; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0) break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
    }
    
    public String toString() {
        return "MarkovModel of order "+predictNum;
    }
}

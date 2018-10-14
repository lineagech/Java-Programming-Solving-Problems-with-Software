
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @Chia-Hao 
 * @2018.10.14
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);

}

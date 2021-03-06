
/**
 * Write a description of class MarkovRunner here.
 * 
 * @Chia-Hao 
 * @2018.10.14
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        runModel(markovWord, st, 120, 139); 
    } 
    
    public void runMarkov2() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        //runModel(markovWord, st, 200); 
        runModel(markovWord, st, 120, 832); 
    } 
    
    public void testGetFollows() {  
        String st = "this is just a test yes this is a simple test"; 
        st = st.replace('\n', ' '); 
        MarkovWordOne markovWord = new MarkovWordOne(); 
        runModel(markovWord, st, 10); 
    } 
    
    public void testGetFollows2() {  
        String st = "this is just a test yes this is a simple test"; 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        runModel(markovWord, st, 10); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}

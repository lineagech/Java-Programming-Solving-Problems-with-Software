
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
        for(int k=0; k < 3; k++){ 
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
        //MarkovWordOne markovWord = new MarkovWordOne();
        MarkovWord markovWord = new MarkovWord(5);
        //runModel(markovWord, st, 200); 
        runModel(markovWord, st, 200, 844); 
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
    
    public void testHashMap() {
        EfficientMarkovWord markov = new EfficientMarkovWord(2);
        String text = "this is a test yes this is really a test";
        //text = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        text = st.replace('\n', ' '); 
        
        int seed = 65;
        int size = 50;
        runModel(markov, text, size, seed);
    }
    
    public void compareMethods() {
        EfficientMarkovWord emarkov = new EfficientMarkovWord(2);
        MarkovWord markov = new MarkovWord(2);
        String text = "this is a test yes this is really a test";
        text = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        text = st.replace('\n', ' '); 
        
        int seed = 42;
        int size = 100;
        long before = System.nanoTime();
        runModel(markov, text, size, seed);
        long after = System.nanoTime();
        System.out.println("Markov run time "+(after-before));
        
        before = System.nanoTime();
        runModel(emarkov, text, size, seed);
        after = System.nanoTime();
        System.out.println("Efficient Markov run time "+(after-before));
        
    }
}

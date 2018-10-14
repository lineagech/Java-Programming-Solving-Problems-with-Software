
/**
 * Write a description of Tester here.
 * 
 * @Chia-Hao
 * @2018.10.12
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        String st = "this is a test yes this is a test.";
        markov.setTraining(st);
        ArrayList<String> result = markov.getFollows("t");
        for(String c : result) System.out.print(c+" ");
        System.out.println("\nkey 't' Size is "+result.size());
        
        result = markov.getFollows("e");
        for(String c : result) System.out.print(c+" ");
        System.out.println("\nkey 'e' Size is "+result.size());
        
        result = markov.getFollows("es");
        for(String c : result) System.out.print(c+" ");
        System.out.println("\nkey 'es' Size is "+result.size());
    }
    
    public void testGetFollowsWithFile()
    {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
        String st = fr.asString();
        markov.setTraining(st);
        
        ArrayList<String> result = markov.getFollows("he");
        for(String c : result) System.out.print(c+" ");
        System.out.println("\nkey 'es' Size is "+result.size());
    }
}

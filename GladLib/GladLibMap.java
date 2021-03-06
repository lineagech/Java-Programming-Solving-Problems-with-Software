import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private HashMap<String, Integer> usedMap;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    private ArrayList<String> usedWords;
    private int usedWordsNum;
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        
        usedWordsNum = 0;
        usedWords = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] labels = {"adjective", "noun", "color", 
                           "country", "name", "animal",
                           "timeframe", "verb", "fruit"};
        for( String label : labels )    
        {
            ArrayList<String> list = readIt(source+"/"+label+".txt");
            myMap.put(label, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        String word = source.get(index);
        while( usedWords.indexOf(word) != -1 )
        {
            index = myRandom.nextInt(source.size());
            word = source.get(index);
        }
        return word;
    }
    
    private String getSubstitute(String label) {
        if( label.equals("country") || label.equals("color") || 
            label.equals("noun") || label.equals("name") || 
            label.equals("adjective") || label.equals("animal") || 
            label.equals("timeframe") || label.equals("verb") || 
            label.equals("fruit") ) {
            if(!usedMap.containsKey(label))    
                usedMap.put(label, 1);
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        usedWordsNum++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println();
        System.out.println(usedWordsNum+" of words replaced.");
    }
    
    public int totalWordsInMap()
    {
        int total = 0;
        for( String key : myMap.keySet() )
            total += myMap.get(key).size();
        return total;
    }
    public int totalWordsConsidered()
    {   
        int total = 0;
        for(String usedKey : usedMap.keySet())
        {
            total += myMap.get(usedKey).size();
        }
        return total;
    }
}


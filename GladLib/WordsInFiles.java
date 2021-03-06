
/**
 * Write a description of WordsInFiles here.
 * 
 * @Chia-Hao
 * @2018.10.05
 */
import edu.duke.*;
import java.io.*;
import java.util.*;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    public WordsInFiles()
    {
        map = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        for( String word : fr.words() )
        {
            if(map.containsKey(word)){
                int index = map.get(word).indexOf(f.getName());
                if( index == -1 ) {// not found 
                    map.get(word).add(f.getName());
                }
            }
            else{
                map.put(word, new ArrayList<String>());
                map.get(word).add(f.getName());
            }
        }
    }
    public void buildWordFileMap()
    {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles() )
        {
            addWordsFromFile(f);
        }
    }
    public int maxNumber()
    {   
        int maxNum = 0;
        for( String key : map.keySet() )
        {
            if( map.get(key).size() > maxNum ) 
                maxNum = map.get(key).size();
        }
        return maxNum;
    }
    public ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> result = new ArrayList<String>();
        for( String key : map.keySet() )
        {
            if( map.get(key).size() == number ) 
                result.add(key);
        }
        return result;
    }
    public void printFilesIn(String word)
    {   
        System.out.println(word+" appears in : ");
        for(String file : map.get(word))
        {
            System.out.println(file);
        }
    }
    public void tester()
    {
        buildWordFileMap();
        for(String key : map.keySet())
        {
            printFilesIn(key);
        }
        System.out.println("Max Num is "+maxNumber());
    }
    public void quiz()
    {
        buildWordFileMap();
        System.out.println("4. # of words : "+map.size());
        
        int num = 0;
        for(String key : map.keySet()){
            if(map.get(key).size() == 4) num++;
        }
        System.out.println("5. # of words : "+num);
        
        System.out.println("6.");
        printFilesIn("sea");
        
        System.out.println("7.");
        printFilesIn("tree");
    }
}

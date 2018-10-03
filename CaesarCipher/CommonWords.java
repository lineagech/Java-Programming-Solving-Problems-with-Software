
/**
 * Write a description of CommonWords here.
 * 
 * @Chia-Hao
 * @2018.10.02
 */
import java.io.*;
import edu.duke.*;
public class CommonWords {
    public String[] getCommon()
    {
        FileResource fr = new FileResource("data/common.txt");
        String[] common = new String[20];
        int index = 0;
        for( String s : fr.words() )
        {
            common[index] = s;
            index++;
        }
        return common;
    }
    public int indexOf(String[] list, String word)
    {
        for(int i=0; i<list.length; i++)
        {
            if(list[i].equals(word)) return i;
        }
        return -1;
    }
    public void countWords(FileResource fr, String[] common, int[] counts)
    {
        for(String s : fr.words())
        {   
            s = s.toLowerCase();
            int index = indexOf(common, s);
            if(index != -1) {
                counts[index] += 1;
            }
        }
    }
    public void countShakespeare()
    {
        //String[] plays = {"caesar.txt", "erros.txt", "hamlet.txt", 
        //                  "likeit.txt", "macbeth.txt", "romeo.txt"};
        String[] plays = {"erros.txt"};
        String[] common = getCommon();
        int[] counts = new int[common.length];
        
        for(int k = 0; k < plays.length; k++ )
        {
            FileResource fr = new FileResource("data/"+plays[k]);
            countWords(fr, common, counts);
            System.out.println("Done with "+plays[k]);
        }
        for(int k = 0; k < common.length; k++ )
        {
            System.out.println(common[k] + "\t"+counts[k]);   
        }
    }
    
}

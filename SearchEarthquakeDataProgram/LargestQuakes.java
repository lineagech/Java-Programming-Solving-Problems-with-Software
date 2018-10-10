
/**
 * Write a description of LargestQuakes here.
 * 
 * @Chia-Hao
 * @2018.10.09
 */

import java.util.*;

public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> data)
    {   
        int maxIndex = 0;
        for(int i=1; i<data.size(); i++)
        { 
            if(data.get(i).getMagnitude() > data.get(maxIndex).getMagnitude()){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany )
    {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy_quakeData = new ArrayList<QuakeEntry>(quakeData);
        
        for( int i =0; i < Math.min(howMany, quakeData.size()); i++ )
        {
            int maxIndex = indexOfLargest(copy_quakeData);
            ret.add(copy_quakeData.get(maxIndex));
            copy_quakeData.remove(copy_quakeData.get(maxIndex));
        }
        
        return ret;
    }
    
    public void findLargestQuakes()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        source = "nov20quakedatasmall.atom";
        source = "nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        for(QuakeEntry loc : list){
            System.out.println(loc);
        }
        System.out.println("read data for "+list.size());
        
        int maxIndex = indexOfLargest(list);
        System.out.println("Max index is "+maxIndex+"\t"+"with mag "+list.get(maxIndex).getMagnitude());
        
        ArrayList<QuakeEntry> largest_list = getLargest(list, 50);
        for(QuakeEntry loc : largest_list){
            System.out.println(loc);
        }
        System.out.println("50 largest quakes = "+largest_list.size());
        
    }
}

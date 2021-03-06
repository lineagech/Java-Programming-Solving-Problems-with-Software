
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy_quakeData = new ArrayList<QuakeEntry>(quakeData);
        
        for( int i =0; i < Math.min(howMany, quakeData.size()); i++ )
        {
            int minIndex = 0;
            for(int j = 1; j <copy_quakeData.size(); j++ )
            {
                if( current.distanceTo(copy_quakeData.get(j).getLocation()) < 
                    current.distanceTo(copy_quakeData.get(minIndex).getLocation()) )
                {
                    minIndex = j;
                }
            }
            ret.add(copy_quakeData.get(minIndex));
            copy_quakeData.remove(copy_quakeData.get(minIndex));
        }
        
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        source = "nov20quakedatasmall.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}

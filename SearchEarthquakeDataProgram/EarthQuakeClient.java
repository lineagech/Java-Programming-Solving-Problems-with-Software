import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry data : quakeData)
        {
            if( data.getMagnitude() > magMin){
                answer.add(data);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry data : quakeData)
        {   
            //System.out.println(from.distanceTo(data.getLocation()));
            //System.out.println(data.getInfo());
            if( from.distanceTo(data.getLocation()) < distMax ){
                answer.add(data);
            }
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry data : quakeData)
        {   
            if( data.getDepth() > minDepth && data.getDepth() < maxDepth ){
                answer.add(data);
            }
        }
        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry data : quakeData)
        {   
            String info = data.getInfo();
            if( where.equals("start") ){
                if(info.startsWith(phrase)) {
                    answer.add(data);
                }
            }
            else if( where.equals("end") ){
                if(info.endsWith(phrase)) {
                    answer.add(data);
                }
            }
            else{ // any
                if(info.contains(phrase)) {
                    answer.add(data);
                }
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        ArrayList<QuakeEntry> larger_list = filterByMagnitude(list, 5.0);
        //System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry e : larger_list)
            System.out.println(e);
        System.out.println("read data for "+larger_list.size()+" quakes");
        
        System.out.println("-------End of bigQuakes--------");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        source = "nov20quakedatasmall.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> from_city = filterByDistanceFrom(list, 1000000.0, city);
        for(QuakeEntry e : from_city)
            System.out.println(e);
        System.out.println("read data for Durham, NC "+from_city.size()+" quakes");
        
        // This location is Bridgeport, CA
        city =  new Location(38.17, -118.82);
        from_city = filterByDistanceFrom(list, 1000000.0, city);
        for(QuakeEntry e : from_city)
            System.out.println(e);
        System.out.println("read data for Bridgeport, CA "+from_city.size()+" quakes");
        
        System.out.println("-------End of closeToMe--------");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        source = "nov20quakedatasmall.atom";
        source = "nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        ArrayList<QuakeEntry> filter_depth = filterByDepth(list, -10000.0, -8000.0);
        for (QuakeEntry qe : filter_depth) {
            System.out.println(qe);
        }
        System.out.println("quakesOfDepth : "+filter_depth.size()+" found.");
        System.out.println("-------End of quakesOfDepth--------");
    }
    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        source = "nov20quakedatasmall.atom";
        source = "nov20quakedata.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        ArrayList<QuakeEntry> filter_phrase = filterByPhrase(list, "start", "Explosion");
        for (QuakeEntry qe : filter_phrase) {
            System.out.println(qe);
        }
        System.out.println("quakesByPhrase : "+filter_phrase.size()+" found.");
        
        filter_phrase = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe : filter_phrase) {
            System.out.println(qe);
        }
        System.out.println("quakesByPhrase : "+filter_phrase.size()+" found.");
        
        filter_phrase = filterByPhrase(list, "any", "Creek");
        for (QuakeEntry qe : filter_phrase) {
            System.out.println(qe);
        }
        System.out.println("quakesByPhrase : "+filter_phrase.size()+" found.");
        
        System.out.println("-------End of quakesByPhrase--------");
    }
}

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
           
        ArrayList<QuakeEntry> m7 = list;
        //Filter f = new MinMagFilter(4.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        //for (QuakeEntry qe: m7) { 
        //    System.out.println(qe);
        //} 

        Filter f_mag = new MagnitudeFilter(3.5, 4.5);
        m7 = filter(m7, f_mag); 
        //for (QuakeEntry qe: m7) { 
        //    System.out.println(qe);
        //} 
        //System.out.println("---DepthFilter---  ---MagnitudeFilter---");
        Filter f_dep = new DepthFilter(-55000.0, -20000.0);
        m7 = filter(m7, f_dep); 
        
        //Filter f_dist = new DistanceFilter(new Location(39.7392,-104.9903), 1000000);
        //m7 = filter(m7, f_dist); 
        
        //Filter f_phr = new PhraseFilter("end", "a");
        //m7 = filter(m7, f_phr); 
        
        System.out.println("---Filter---");
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        }
        System.out.println("Filter list size: "+m7.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter()
    {   
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter allFilter = new MatchAllFilter();
        allFilter.addFilter(new MagnitudeFilter(1.0, 4.0));
        allFilter.addFilter(new DepthFilter(-180000.0, -30000.0));
        allFilter.addFilter(new PhraseFilter("any", "o"));
        ArrayList<QuakeEntry> m7 = filter(list, allFilter); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("Filter: "+m7.size()+" quakes");
        System.out.println("Filters used are: "+allFilter.getName());
    }
    public void testMatchAllFilter2()
    {   
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));
        ArrayList<QuakeEntry> m7 = filter(list, maf); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("Filter: "+m7.size()+" quakes");
    }
    
    public void Quiz1()
    {   
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("read data for "+list.size()+" quakes");
        
        System.out.println("1.");
        MatchAllFilter allFilter = new MatchAllFilter();
        allFilter.addFilter(new DistanceFilter(new Location(35.42, 139.43), 10000000));
        ArrayList<QuakeEntry> m7 = filter(list, allFilter); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("DistanceFilter: "+m7.size()+" quakes");
        //System.out.println("Filters used are: "+allFilter.getName());
    }

}

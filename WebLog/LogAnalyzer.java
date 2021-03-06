
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private int location = 0;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
        FileResource fr = new FileResource(filename);
        for(String line : fr.lines())
        {
            records.add( WebLogParser.parseEntry(line) );
        }
     }
        
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for( LogEntry le : records )
         {
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr))
             {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num)
     {  
         for( LogEntry le : records )
         {
             if( le.getStatusCode() > num ){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday)
     {   
         ArrayList<String> month = new ArrayList<String>( Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", 
                                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"));
         ArrayList<String> result = new ArrayList<String>();
         for (LogEntry le : records) {
             Date date = le.getAccessTime();
             String str_date = date.toString();
             String cmp_date = str_date.substring(4, 10);
             if( cmp_date.equals(someday) && !result.contains(le.getIpAddress()) ) 
                result.add(le.getIpAddress());
              
         } 
         return result;
     }
     public int countUniqueIPsInRange(int low, int high)
     {  
         ArrayList<String> count = new ArrayList<String>();
         for( LogEntry le : records )
         {
             if( le.getStatusCode() >= low && le.getStatusCode() <= high 
              && !count.contains(le.getIpAddress()) ){
                 count.add(le.getIpAddress());
             }
         }
         return count.size();
     }
     public HashMap<String,Integer> countVisitsPerIP()
     {
         HashMap<String,Integer> map = new HashMap<String,Integer>();
         for (LogEntry le : records) 
         {
             String ip = le.getIpAddress();
             if(!map.containsKey(ip))
             {
                 map.put(ip,1);
             }else {
                 map.put(ip,map.get(ip)+1);
             }
         }
         return map;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts)
     {
         int maxCount = 0;
         for( String ip : counts.keySet() )
         {
             if(counts.get(ip) > maxCount) 
                maxCount = counts.get(ip);
         }
         return maxCount;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts)
     {
         int maxCount = mostNumberVisitsByIP(counts);
         ArrayList<String> result = new ArrayList<String>();
         for( String ip : counts.keySet() )
         {
             if(counts.get(ip) == maxCount) 
                result.add(ip);
         }
         return result;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays()
     {
         HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records) {
             Date date = le.getAccessTime();
             String ip = le.getIpAddress();
             String str_date = date.toString();
             String cmp_date = str_date.substring(4, 10);
             if( !map.containsKey(cmp_date) ){
                 ArrayList<String> al = new ArrayList<String>();
                 al.add(ip);
                 map.put(cmp_date, al);
             }
             else {
                 ArrayList<String> al = map.get(cmp_date);
                 //if( !al.contains(ip) ) {
                     al.add(ip);
                 //}
             }
         } 
         return map;
     }
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> ipForDay)
     {
         int maxCount = 0;
         String maxCountDay = null;
         for(String date : ipForDay.keySet() )
         {
             if( ipForDay.get(date).size() > maxCount ){
                 maxCount = ipForDay.get(date).size();
                 maxCountDay = date;
             }
         }
         return maxCountDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipForDay, String date )
     {  
         ArrayList<String> ipWithMostVisit = new ArrayList<String>();
         ArrayList<String> ipOnDay = ipForDay.get(date);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         int maxCount = 0;
         for(String ip : ipOnDay)
         {
             if(!counts.containsKey(ip)){
                 counts.put(ip, 1);
             }
             else {
                 counts.put(ip, counts.get(ip)+1);
             }
             maxCount = Math.max(counts.get(ip), maxCount);
         }
         for( String ip : counts.keySet() )
         {
             if(counts.get(ip) == maxCount){
                 ipWithMostVisit.add(ip);   
             }
         }
         return ipWithMostVisit;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
}

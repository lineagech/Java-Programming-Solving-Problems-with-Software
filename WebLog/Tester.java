
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../short-test_log");
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../short-test_log");
        System.out.println("# of unique IPs : "+la.countUniqueIPs());
    }
    
    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog-short_log");
        ArrayList<String> result = la.uniqueIPVisitsOnDay("Sep 14");
        //for(String r : result) System.out.println(r);
        
        result = la.uniqueIPVisitsOnDay("Sep 30");
        //for(String r : result) System.out.println(r);
        
        la.readFile("../weblog1_log");
        result = la.uniqueIPVisitsOnDay("Mar 17");
        for(String r : result) System.out.println(r);
        System.out.println("# of IPs on Mar 17 : "+result.size());
    }
    
    public void testCountUniqueIPsInRange()
    {   
        System.out.println("----------@@@@@@@_----------");
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("../short-test_log");
        //System.out.println("unique IP in range 200-299: "+la.countUniqueIPsInRange(200, 299));
        //System.out.println("unique IP in range 300-399: "+la.countUniqueIPsInRange(300, 399));
        
        la.readFile("../weblog1_log");
        System.out.println("weblog1 unique IP in range 300-399: "+la.countUniqueIPsInRange(300, 399));
    }
    
    public void testPrintAllHigherThanNum()
    {   
        System.out.println("----------@@@@@@@_----------");
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("../short-test_log");
        //la.printAllHigherThanNum(20);
        
        la.readFile("../weblog1_log");
        la.printAllHigherThanNum(400);
    }
    public void quiz()
    {   
        System.out.println("________QUIZ_______");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog2_log");
        System.out.println("1. # of unique IPs : "+la.countUniqueIPs());
        
        ArrayList<String> result = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("2.");
        for(String r : result) System.out.println(r);
        System.out.println("# of IPs on Sep 27 : "+result.size());
        
        System.out.println("3. weblog1 unique IP in range 200-299: "+la.countUniqueIPsInRange(200, 299));
    
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println("4. Most number of weblog2_log ip = "+ la.mostNumberVisitsByIP(counts));
        
        System.out.println("5.");
        ArrayList<String> re = la.iPsMostVisits(counts);
        System.out.println(re);
        
        System.out.println("6.");
        HashMap<String,ArrayList<String>> ip_for_days = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(ip_for_days));
        
        System.out.println("7.");
        ip_for_days = la.iPsForDays();
        ArrayList<String> iPsWithMostVisit = la.iPsWithMostVisitsOnDay(ip_for_days, "Sep 29");
        System.out.println(iPsWithMostVisit);
    }
}

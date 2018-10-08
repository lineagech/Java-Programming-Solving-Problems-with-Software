
/**
 * Write a description of CountTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class CountTester {
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../short-test_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        System.out.println("Most number of weblog1_log ip = "+ la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog1_log");
        HashMap<String,Integer> counts = la.countVisitsPerIP();
        ArrayList<String> re = la.iPsMostVisits(counts);
        System.out.println(re);
    }
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog3-short_log");
        HashMap<String,ArrayList<String>> result = la.iPsForDays();
        System.out.println(result);
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog1_log");
        HashMap<String,ArrayList<String>> result = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(result));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("../weblog1_log");
        HashMap<String,ArrayList<String>> result = la.iPsForDays();
        ArrayList<String> iPsWithMostVisit = la.iPsWithMostVisitsOnDay(result, "Mar 17");
        System.out.println(iPsWithMostVisit);
    }
}

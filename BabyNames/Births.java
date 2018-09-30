
/**
 * Write a description of Births here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Births {
    String file_suffix = ".csv";
    public void totalBirths(FileResource fr)
    {   
        int total_birth = 0;
        int total_male = 0;
        int total_female = 0;
        
        int total_names = 0;
        int total_male_names = 0;
        int total_female_names = 0;
        CSVParser parser = fr.getCSVParser(false);
        for( CSVRecord rec : parser )
        {   
            int numBorn = Integer.parseInt(rec.get(2));
            total_birth += numBorn;
            total_names++;
            if( rec.get(1).equals("M") ) {
                total_male += numBorn;
                total_male_names++;
            }
            else {
                total_female += numBorn;
                total_female_names++;
            }
        }
        System.out.println("----------------------------");
        System.out.println("total births = " + total_birth);
        System.out.println("total girls = " + total_male);
        System.out.println("total boys = " + total_female);
        
        System.out.println("total names = " + total_names);
        System.out.println("total girls names = " + total_female_names);
        System.out.println("total boys names = " + total_male_names);
    }
    public void testTotalBirths()
    {   
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender)
    {   
        int Rank = 1;
        String File = "yob"+year+file_suffix;
        FileResource fr = new FileResource(File);
        for( CSVRecord rec : fr.getCSVParser(false) )
        {   
            if(!rec.get(1).equals(gender)) continue;
            if( rec.get(0).equals(name) ){
                return Rank;
            }
            Rank++;
        }
        return -1;
    }
    
    public int getRank(FileResource fr, String name, String gender)
    {   
        int Rank = 1;
        for( CSVRecord rec : fr.getCSVParser(false) )
        {   
            if(!rec.get(1).equals(gender)) continue;
            if( rec.get(0).equals(name) ){
                return Rank;
            }
            Rank++;
        }
        return -1;
    }
    
    public void testGetRank()
    {   
        System.out.println("Mason M in 2012 rank = "+ getRank(2012, "Mason", "M"));
        System.out.println("Mason F in 2012 rank = "+ getRank(2012, "Mason", "F"));
    }
    
    public String getName(int year, int rank, String gender)
    {   
        int Rank = 1;
        String File = "yob"+year+file_suffix;
        FileResource fr = new FileResource(File);
        for( CSVRecord rec : fr.getCSVParser(false) )
        { 
            if(!rec.get(1).equals(gender)) continue;
            if( Rank == rank ){
                return rec.get(0);
            }
            Rank++;
        }
        return "NO NAME";
    }
    public void testGetName()
    {   
        System.out.println("Rank 2 M in 2012 name = "+ getName(2012, 2, "M"));
        System.out.println("Rank 2 F in 2012 name = "+ getName(2012, 12, "F"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int original_rank = getRank(year, name, gender);
        String new_name = getName(newYear, original_rank, gender);
        System.out.println(name+" born in "+ year+" would be "+new_name+
                           " if he/she was born in "+newYear);
    }
    public void testWhatIsNameInYear()
    {
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
    
    public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int highest_rank = Integer.MAX_VALUE;
        int the_year;
        String highest_rank_file = "";
        for( File f : dr.selectedFiles() )
        {
            FileResource fr = new FileResource(f);
            int year_rank = getRank(fr, name, gender);
            if( year_rank != -1 && year_rank < highest_rank )
            {
                highest_rank = year_rank;
                highest_rank_file = f.getName();
            }
        }
        if( highest_rank == Integer.MAX_VALUE ) return -1;
        
        the_year = Integer.parseInt(highest_rank_file.substring(3,7));
        return the_year;
    }
    public void testYearOfHighestRank()
    {
        System.out.println("The year of highest rank of Mason = "+
                           yearOfHighestRank("Mason", "M"));
    }
    
    public double getAverageRank(String name, String gender)
    {   
        int total_rank = 0;
        int num_of_year = 0;
        DirectoryResource dr = new DirectoryResource();
        for( File f : dr.selectedFiles() )
        {
            FileResource fr = new FileResource(f);
            int year_rank = getRank(fr, name, gender);
            if( year_rank != -1 ) {
                total_rank += year_rank;
                num_of_year += 1;
            }
        }
        if( num_of_year == 0 ) return -1.0;
        else return (double)(total_rank)/(double)(num_of_year);
    }
    public void testGetAverageRank()
    {
        System.out.println("The average rank of Mason = "+
                           getAverageRank("Mason", "M"));
        System.out.println("The average rank of Jacob = "+
                           getAverageRank("Jacob", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender)
    {   
        int total = 0;
        int numBirth_of_name = -1;
        String File = "yob"+year+file_suffix;
        FileResource fr = new FileResource(File);
        int the_rank = getRank(fr, name, gender);
        //for( CSVRecord rec : fr.getCSVParser(false) )
        //{ 
        //    if(rec.get(0).equals(name)){
        //        numBirth_of_name = Integer.parseInt(rec.get(2));
        //        break;
        //    }
        //}
        for( CSVRecord rec : fr.getCSVParser(false) )
        { 
            if(!rec.get(1).equals(gender))continue;
            if(rec.get(0).equals(name))continue;
            int curr_numBirth = Integer.parseInt(rec.get(2));
            int curr_rank = getRank(fr, rec.get(0), gender);
            if( curr_rank < the_rank )
            {
                total += curr_numBirth;
            }
        }
        return total;
    }
    public void testGetTotalBirthsRankedHigher()
    {
        System.out.println("The total birth num higher than Ethan = "+
                           getTotalBirthsRankedHigher(2012, "Ethan", "M"));

    }
    public void testQuiz()
    {   
        System.out.println("Number 1.");
        String file = "yob1900.csv";
        FileResource fr = new FileResource(file);
        totalBirths(fr);
        
        System.out.println("Number 2.");
        file = "yob1905.csv";
        fr = new FileResource(file);
        totalBirths(fr);
        
        System.out.println("Number 3.");
        System.out.println("Emily F in 1960 rank = "+getRank(1960, "Emily", "F"));
        
        System.out.println("Number 4.");
        System.out.println("Frank M in 1971 rank = "+getRank(1971, "Frank", "M"));
        
        System.out.println("Number 5.");
        System.out.println("Rank 350 F in 1980 name = "+ getName(1980, 350, "F"));
        
        System.out.println("Number 6.");
        System.out.println("Rank 450 M in 1982 name = "+ getName(1982, 450, "M"));
        
        System.out.println("Number 7.");
        whatIsNameInYear("Susan", 1972, 2014, "F");
        
        System.out.println("Number 8.");
        whatIsNameInYear("Owen", 1972, 2014, "M");
        
        System.out.println("Number 9.");
        //System.out.println("The year of highest rank of Genevieve = "+
        //                   yearOfHighestRank("Genevieve", "F"));
                           
        System.out.println("Number 10.");
        //System.out.println("The year of highest rank of Mich = "+
        //                   //yearOfHighestRank("Mich", "M"));
        
        System.out.println("Number 11.");
        //System.out.println("The average rank of Susan = "+
        //                   getAverageRank("Susan", "F")); 
        
        System.out.println("Number 12.");
        //System.out.println("The average rank of Robert = "+
        //                   getAverageRank("Robert", "M")); 
        
        System.out.println("Number 13.");
        System.out.println("The total birth num higher than Emily = "+
                           getTotalBirthsRankedHigher(1990, "Emily", "F"));
        
        System.out.println("Number 14.");
        System.out.println("The total birth num higher than Drew = "+
                           getTotalBirthsRankedHigher(1990, "Drew", "M"));                   
    }
}

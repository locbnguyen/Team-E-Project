import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Catalog {
    public final String CATALOG_FILE_NAME = "Catalog.dat"; //Catalog file name
    public  String courseId;
    public  String fromDate;
    public  String endDate;
    public  String courseName;
    public  String maxLimit;
    public  String totalEnrolled;
    
    public Catalog() {
    }
    
    //getCatalogFileName
    //public static String getCatalogFileName() {
    public String getCatalogFileName() {
	return CATALOG_FILE_NAME;
    }
    
    //showCatalog method
    public void showCatalog()
	    throws FileNotFoundException, IOException {
	FileReader fileReader = new FileReader(CATALOG_FILE_NAME);
	BufferedReader in = new BufferedReader(fileReader);

	//Print heading
	System.out.printf("\nCourse Catalog");
	System.out.printf("\n%-9s %-40s %10s %10s %3s",
		"CourseId", "Course Name", "Start Date", "End Date", "Max");
	System.out.printf("\n%-9s %-40s %10s %10s %3s",
		"--------", "----------------------------------------",
		"----------", "----------", "---");

	//Read input file & show on screen
	while (true) {
	    String s = in.readLine();
	    if (s == null) 
		break;

	    //System.out.println(s);
	    String[] fields = s.split("[|]"); // Split into fields
	    
	    // Only print up to 40 chars for description
	    int descLength = fields[3].length();
	    if (descLength > 39)
		descLength = 39;

	    System.out.printf("\n%-9s %-40s %10s %10s %3s",
		    fields[0], fields[3].substring(0, descLength),
		    fields[1], fields[2],fields[4]);
	}
	System.out.println();;
	fileReader.close();
	in.close();
    }
    
    //Get course id
    public void getCourse(String courseId) 
	    throws FileNotFoundException, IOException {
	
	FileReader fileReader = new FileReader(CATALOG_FILE_NAME);
	BufferedReader in = new BufferedReader(fileReader);
	//String cat = " ";
	
	//Get courseId
	while (true) {
	    String s = in.readLine();
	    if (s == null)
		break;

	    String[] fields = s.split("[|]"); // Split into fields

	    //Course Id does not match
	    if (!courseId.equals(fields[0])) {
		continue;
	    }
	    
	    courseId      = fields[0];
	    fromDate      = fields[1];
	    endDate       = fields[2];
	    courseName    = fields[3];
	    maxLimit      = fields[4];
	    totalEnrolled = fields[5];
	    break;
	}

	// Close files
	fileReader.close();
	in.close();
    }
}
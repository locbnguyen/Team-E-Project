import java.io.*;

public class StudentProfile {
    //private static String studentProfileName = "StudentProfile.txt"; // Student
    public final String STUDENT_PROF_FILE = "StudentProfile.dat"; // Student
    String studentId;
    String courseName;
    String startDate;
    String endDate;

    public StudentProfile() {
    }

    public StudentProfile(String studentId, String courseName, String startDate, String endDate) {
	this.studentId = studentId;
	this.courseName = courseName;
	this.startDate = startDate;
	this.endDate = endDate;
    }

    // getStudentProfileName
    public String getStudentProfileName() {
	return STUDENT_PROF_FILE ;
    }

    // getStudentProfile method
    public void getStudentProfile(String id) throws FileNotFoundException, IOException {

	// Create new filereader and buffer reader
	FileReader fileReader = new FileReader(STUDENT_PROF_FILE);
	BufferedReader in = new BufferedReader(fileReader);

	// Print heading
	System.out.println("\nStudent Profile");
	System.out.printf("\n%-7s %-40s %13s %13s", 
		"Student", "              Course Name              ", 
		"Start Date", "End Date");
	System.out.printf("\n%-7s %-40s %13s %13s", 
		"------", "----------------------------------------", 
		"-----------", "-----------");

	// Read input file & show on screen
	while (true) {
	    String s = in.readLine();
	    if (s == null)
		break;

	    String[] fields = s.split("[|]"); // Split into fields

	    // Print all if id = ALL, else only print for specific id
	    if ((!id.equals("ALL")) && (!id.equals(fields[0]))) {
		continue;
	    }

	    // Only print up to 40 chars for description
	    int descLength = fields[1].length();
	    if (descLength > 39)
		descLength = 39;

	    // Print record
	    System.out.printf("\n%-7s %-40s %13s %13s", 
		    fields[0], fields[1].substring(0, descLength), 
		    fields[2], fields[3]);
	}
	// Close files
	System.out.println();;
	fileReader.close();
	in.close();
    }

    // writeStudentProfile method
    public boolean writeStudentProfile(String studentId, 
	    String courseName, String startDate, String endDate)
	    throws FileNotFoundException, IOException {

	try (FileWriter fw = new FileWriter(STUDENT_PROF_FILE, true);
	     BufferedWriter bw = new BufferedWriter(fw);
	     PrintWriter output = new PrintWriter(bw)) {
	    // Write formatted output to the file
	    output.print(studentId + "|"); 
	    output.print(courseName + "|"); 
	    output.print(startDate + "|"); 
	    output.println(endDate);
	    return true;
	}
	catch (IOException e) {
	    return false;
	}
    }
    
    //getRegisteredCourse
    public String getRegisteredCourse(String id, String courseName) 
	    throws FileNotFoundException, IOException {

	// Create new filereader and buffer reader
	FileReader fileReader = new FileReader(STUDENT_PROF_FILE);
	BufferedReader in = new BufferedReader(fileReader);
	boolean isRegistered = false;
	int enrollmentCount = 0;

	// Read input file 
	while (true) {
	    String s = in.readLine();
	    if (s == null)
		break;

	    String[] fields = s.split("[|]"); // Split into fields

	    //Error if student already registered for the class
	    if ((id.equals(fields[0])) && (courseName.equals(fields[1]))) {
		isRegistered = true;
		break;
	    }

	    //Count the number of enrollment for course
	    if ((courseName.equals(fields[1]))) {
		enrollmentCount++;
	    }
	}

	// Close files
	fileReader.close();
	in.close();
	
	return isRegistered + "|" + enrollmentCount;
    }
}
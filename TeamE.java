import java.io.*;

public class TeamE {
    public static void main(String[] args) throws FileNotFoundException, IOException {

	java.util.Scanner sc = new java.util.Scanner(System.in);

	// Get student id
	System.out.print("Login with your Student Id (XXXX): ");
	String id = sc.next();
	int option = 0;

	while (option != 4) {
	    showOptions();
	    option = sc.nextInt();
	    switch (option) {
	    case 1:
		showCatalog();
		break;
	    case 2:
		showProfile(id);
		break;
	    case 3:
		System.out.print("Register for Course Id (XNNN.NN): ");
		String courseId = sc.next();
		boolean result = registerCourse(id, courseId);
		if (result)
		    showProfile(id);
		break;
	    case 4:
		System.out.print("\n*** Team E project ended");
		break;
	    }
	}
    }

    // Show menu options
    public static void showOptions() {
	System.out.println("\nAvailable Options");
	System.out.println("  1. Course Catalog");
	System.out.println("  2. Student Profile");
	System.out.println("  3. Register for a Course");
	System.out.println("  4. End Session");
	System.out.print("\nYour Selection: ");
    }

    // showCatalog method
    public static void showCatalog() throws FileNotFoundException, IOException {
	Catalog cat = new Catalog();
	cat.showCatalog();
    }

    // Show Student profile
    public static void showProfile(String id) throws FileNotFoundException, IOException {
	StudentProfile profile = new StudentProfile();
	profile.getStudentProfile(id);
    }

    public static boolean registerCourse(String id, String courseId) 
	    throws FileNotFoundException, IOException {

	// Get catalog
	Catalog myCat = new Catalog();
	//String cat = myCat.getCourse(courseId);
	myCat.getCourse(courseId);

	if (myCat.courseName == null) {
	    System.out.println("***ERROR: Invalid course id entered: " + courseId);
	    return false;
	}

	StudentProfile profile = new StudentProfile();
	String rs = profile.getRegisteredCourse(id, myCat.courseName);
	String[] regResult = rs.split("[|]"); // Split into fields

	// Already register
	if (regResult[0].equals("true")) {
	    System.out.println("***ERROR: Student: " + id + " already registered for: " + 
		    myCat.courseName);
	    return false;
	}

	int x = Integer.parseInt(regResult[1]);
	int y = Integer.parseInt(myCat.maxLimit);

	if (x >= y) {
	    System.out.println("***ERROR: Maximum enrollment (" + myCat.maxLimit + ")" + 
		    " reached for " + myCat.courseName);
	    return false;
	}

	// Write to registered file
	boolean writeResult = profile.writeStudentProfile(id, myCat.courseName, 
		myCat.fromDate, myCat.endDate);

	return true;
    }
}
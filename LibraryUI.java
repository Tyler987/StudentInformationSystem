import java.util.*;

public class LibraryUI {
	private static boolean loggedin = false;
	private static boolean isElevated = false;
	private static Scanner keyboard = new Scanner(System.in);
	private static void displayUI(){
		
		if(loggedin&&isElevated){
			System.out.println("Advisor portal");
			System.out.println(" ________________________________________________________________________________________________________________________________________ ");
			System.out.println("|    1.) Find Course    |    2.) Register Course    |    3.) Add Note For Student    |	4.) View Notes For Student    |    5.) Logout    |");
			System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------------- ");
		}else if(loggedin&&!isElevated){
			System.out.println(" ______________________________________________________________________________________________________________________________________ ");
			System.out.println("|    1.) Find Course    |    2.) Register Course    |    3.) Print Schedule    |    4.) Search Electives By Name    |	5.) Logout	");
			System.out.println(" -------------------------------------------------------------------------------------------------------------------------------------- ");
		}else{
			System.out.println(" _____________________________________________________________ ");
			System.out.println("|    1.) Login    |    2.) Create Account    |    3.) Quit    |");
			System.out.println(" ------------------------------------------------------------- ");
		}
	}
	private static ArrayList<String> getInputs(List<String> fields){

		System.out.println("Please enter");
		ArrayList<String> returnl = new ArrayList<>();
		for(String field:fields){
			System.out.println(field+": ");
			String val = keyboard.nextLine();
			returnl.add(val);
		}
		return returnl;
	}
	private static void logoutUI(Library app){
		app.logout();
		isElevated=false;
		System.out.println("\n\n\n\n\n");
		loggedin = false;
	}
	private static void loginUI(Library app){
		if(app.getCurrentUser().getClass().getSimpleName().equals("Advisor"))
			isElevated=true;
		loggedin = true;
	}
	public static void main(String[] args) {
		Library library = new Library();
		//library.displayStudentDataByLastName("West");
		CourseList courseList = CourseList.getInstance();
		MajorList mList = MajorList.getInstance();
		// ArrayList<Course> courses = courseList.getCourses();
		ArrayList<Degree> degrees = mList.getList();
		Library application = new Library();
		System.out.println("--------Welcome to AMGT Degree Works--------");
		while(true){
			displayUI();
			if(loggedin&&isElevated){
				int input = keyboard.nextInt();
				keyboard.nextLine(); 
				ArrayList<String> vals; 
				switch(input){
					case 3:
						{
						vals = getInputs(Arrays.asList("Student Name (First Last)","Notes"));
						String[] names = vals.get(0).split(" ");
						for(String s:names){
							System.out.println(s);
						}
						UUID studentIDforAdvisor = UserList.getInstance().getStudentByName(names[0], names[1]).getUuid();
						Advisor person = (Advisor)application.getCurrentUser();
						person.addNotes(studentIDforAdvisor, vals.get(1));
						System.out.println("\n\n\n\n\n");
						System.out.println("**Notes Added**");
						}
						break;
					case 4:
						vals = getInputs(Arrays.asList("Student Name (First Last)"));
						String[] names = vals.get(0).split(" ");
						UUID studentIDforAdvisor = UserList.getInstance().getStudentByName(names[0], names[1]).getUuid();
						Advisor person = (Advisor)application.getCurrentUser();
						System.out.println("\n\n\n\n\n");
						System.out.println(person.getNotes(studentIDforAdvisor));
					case 5:
						logoutUI(application);
						break;
				}
			}else if(loggedin&&!isElevated){
				int input = keyboard.nextInt();
				keyboard.nextLine();
				ArrayList<String> vals; 
				switch(input){
					case 1:
						vals = getInputs(Arrays.asList("Course Name"));
						boolean coursesearch = application.findCourse(vals.get(0));
						if(coursesearch){

						}else{
							System.out.println("\n\n\n\n\n");
							System.out.println("**Course Could Not be Found.**");
						}
						break;
					case 3:
						ArrayList<Degree> degree = MajorList.getInstance().getList();
						System.out.println(degree.get(0).printPlanner());
						break;
					case 4:
						System.out.println("Please enter the type of electives you'd like to look at: ");
						String key = keyboard.nextLine();
						System.out.println(application.getElectivesTypeByName(key));
						break;
						
					case 5:
						logoutUI(application);
						break;
				}
			}else{
				int input = keyboard.nextInt(); 
				keyboard.nextLine();
				ArrayList<String> vals;
				switch(input){
					case 1:
						vals = getInputs(Arrays.asList("Email","Password"));
						boolean login = application.login(vals.get(0),vals.get(1));
						if(login){
							System.out.println("\n\n\n\n\n");
							loginUI(application);
						}else{
							System.out.println("\n\n\n\n\n");
							System.out.println("**Invalid Email or Password entered.**");
						}
						break;
					case 2:
						vals = getInputs(Arrays.asList("1.) Student    2.) Advisor"));
						if(Integer.parseInt(vals.get(0))==1){
							vals = getInputs(Arrays.asList("First Name","Last Name","Email","Password"));
							System.out.println("Please Choose a major from the following:");
							System.out.println("1.) Computer Science, 2.) Computer Information Systems");
							ArrayList<String> majorval = getInputs(Arrays.asList("Major"));
							System.out.println("\n\n\n\n\n");
							System.out.println("Created Student Account");
							application.createAccount(vals.get(2), vals.get(3), vals.get(0), vals.get(1),majorval.get(0));
							loginUI(application);
						}else if(Integer.parseInt(vals.get(0))==2){
							vals = getInputs(Arrays.asList("First Name","Last Name","Email","Password"));
							System.out.println("\n\n\n\n\n");
							System.out.println("Created Advisor Account");
							application.createAccount(vals.get(2), vals.get(3), vals.get(0), vals.get(1), true);
							loginUI(application);
						}else{
							System.out.println("\n\n\n\n\n");
							System.out.println("**Invalid Option.**");
						}
						break;
					case 3:
						System.exit(0);
				}
			}
		}
	}
}

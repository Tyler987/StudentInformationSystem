import java.util.*;

public class Degree {
    protected UUID id;
    private DegreeType major;
    private ArrayList<Course> courses;
    protected SchedulePlanner degreeCourses;
    protected int totalCreditHours;
    private ArrayList<Elective> electives;

    public Degree(UUID id, DegreeType major, int totalCreditHours, ArrayList<Course> courses, SchedulePlanner planner, ArrayList<Elective> electives) {
        this.major = major;
        this.id = id;
        this.courses = courses;
        degreeCourses = planner;
        this.totalCreditHours = totalCreditHours;
        this.electives = electives;
    }

    public void updateElectiveReq(Course course) {
        for(int i = 0; i < electives.size(); i++) {
            electives.get(i).updateCredits(course);
        }
    }


    public DegreeType getName() {

        return major;
    }

    public int getCreditHours() {
        return totalCreditHours;
    }

    public UUID getID() {
        return id;
    }
    public ArrayList<Course> getCourses(){
        return courses;
    }
    public DegreeType getMajor() {
        return major;
    }

    public int getTotalCreditHours() {
        return totalCreditHours;
    }

    public String printPlanner() {
        String str = "Major: " + major + "\n" + "Credit Hours: " + totalCreditHours + "\nCourses:\n";
        str = str + degreeCourses.printDefaultPlanner() + "__________________________\n" + printElectiveRequirements();
        return str;

    }

    public SchedulePlanner getPlanner() {
        return degreeCourses;
    }

    public String printElectiveRequirements() {
        String str = "Elective Requirements\n_________________________\n";
        for(int i = 0; i < electives.size(); i++) {
            str+= electives.get(i).getCategory() + "\t" + electives.get(i).getNumCredits() + "\n";
        }
        return str;
    }


}

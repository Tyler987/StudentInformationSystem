import java.util.*;

public class SemesterSchedule {
    protected ArrayList<Course> courses;
    protected int semesterNum;

    public SemesterSchedule(ArrayList<Course> courses, int semesterNum) {
        this.courses = courses;
        this.semesterNum = semesterNum;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public int getTotalCredits() {
        int num = 0;
        for(int i = 0; i < courses.size(); i++ ) {
            num+=courses.get(i).getCreditHours();
        }
        return num;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(UUID id, String courseName, Category category, int creditHours, ArrayList<String> prereqs) {
        Course newCourse = new Course(id, courseName, category, creditHours, prereqs);
        courses.add(newCourse);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }
    public String printSS() {
        String str = "Semester Number: " + semesterNum + "\n==========================================\n";
        for (int i = 0; i < courses.size(); i++) {
            str = str + courses.get(i).getCourseName() + "\n";
            // str = str + "---------------------------------\n";
        }
        str+= "\nTotal Credit Hours: " + getTotalCredits() + "\n";
        return str;
    }
}

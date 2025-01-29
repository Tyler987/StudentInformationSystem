import java.util.*;

public class DWFacade {
    private Advisor advisor;
    private CourseList courseList;
    private Degree degree;
    private Elective elective;
    private SemesterSchedule schedule;
    private Student student;
    private UserList users;
    private MajorList majors;

    public DWFacade() {
        this.majors = MajorList.getInstance();
        this.courseList = CourseList.getInstance();
        this.users = UserList.getInstance();
        // this.student = UserList.();

    }

    public boolean login(String email, String password) {
        User user = users.getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            if (user instanceof Student) {
                Student student = (Student) user;
                System.out.println("Login successful!");
                System.out.println(student.printStudentInfo());
            } else {
                System.out.println("Login successful, but the user is not a student.");
            }
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    public Course findCourse(String str) {

        ArrayList<Course> courses = CourseList.getInstance().getCourses();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseName().contains(str)) {
                return courses.get(i);
            }
        }
        return null;
    }
}

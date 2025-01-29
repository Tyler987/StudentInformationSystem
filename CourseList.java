import java.util.ArrayList;
import java.util.UUID;

public class CourseList {
    private static CourseList instance;
    private ArrayList<Course> courses;

    private CourseList() {
        courses = DataLoader.getCourses();
    }

    public static CourseList getInstance() {
        if (instance == null) {
            instance = new CourseList();
        }
        return instance;
    }

    public Course getCourse(String courseName) {
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                return course;
            }
        }
        return null;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Course getCourseByID(UUID id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return courses.get(i);
            }
        }
        return null;
    }

    public Course getElectiveByID(UUID id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return courses.get(i);
            }
        }
        return null;
    }

}

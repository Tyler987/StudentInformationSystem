import java.util.ArrayList;
import java.util.UUID;

public class GageTesting {
    public static void main(String[] args) {
        ArrayList<String> pre = new ArrayList<>();
        UUID newid = UUID.randomUUID();
        //pre.add(new Course(newid, "New course 101", Category.PREREQUISITE, 5, newid, new ArrayList<Course>()));
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(newid, "Programming", Category.LAB_SCIENCE_ELECTIVE, 5, newid, pre));
        courses.add(new Course(newid, "Programming 2", Category.LAB_SCIENCE_ELECTIVE, 5, newid, new ArrayList<Course>()));
        Student gage = new Student(newid.toString(), "Gage", "Hulbert", "email", "Cool", null, "10110C", 4.0, courses);
        ArrayList<Student> students = new ArrayList<>();
        students.add(gage);
        UserList.getInstance().loadUsers();
        DataWriterTwo.writeStudent();
    }
}

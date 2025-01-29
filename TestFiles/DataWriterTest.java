import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.beans.Transient;
import java.io.File;
import java.io.FileWriter;

public class DataWriterTest {
    UserList accounts;
    CourseList courses;
    MajorList degrees;
    @BeforeEach
    public void setup(){
        accounts = UserList.getInstance();
        courses = CourseList.getInstance();
        degrees = MajorList.getInstance();
        new File("src/STUDENT_FILE_NAME.json").delete();
        new File("src/COURSE_FILE_NAME.json").delete();
        new File("src/DEGREE_FILE_NAME.json").delete();
        new File("src/ADVISOR_FILE_NAME.json").delete();
    }
    @AfterEach
    public void alsoclearfiles(){
        accounts.getStudents().clear();
        accounts.getAdvisors().clear();
        courses.getCourses().clear();
        degrees.getList().clear();
        // new File("src/STUDENT_FILE_NAME.json").delete();
        // new File("src/COURSE_FILE_NAME.json").delete();
        // new File("src/DEGREE_FILE_NAME.json").delete();
        // new File("src/ADVISOR_FILE_NAME.json").delete();
    }
    @Test
    public void writeStudents(){
        DataWriterTesting.writeStudent();
        Student brax = DataLoaderTesting.getStudents().get(0);
        assertEquals("Brax", brax.getFirstName());
    }
    @Test
    public void writeStudentsEmpty(){
        Student person = new Student(null, null, null, null, null, null, null, 0, null, null, null);
        accounts.getStudents().clear();
        accounts.addUser(person);
        DataWriterTesting.writeStudent();
        Student gage = DataLoaderTesting.getStudents().get(0);
        assertEquals("", gage.getFirstName());
    }
    @Test
    public void writeStudentsSingle(){
        ArrayList<Course> MyCourses = new ArrayList<>();
        Random rand = new Random();
        char initchar = (char) (rand.nextInt(26) + 'a');
        String uscID = initchar + "";
        for (int i = 0; i < 8; i++) {
        uscID += Integer.toString((rand.nextInt(9) + 1));
        }
        Student person = new Student(UUID.randomUUID(), "Gage", "Hulbert", "GH@email", "GH", MajorList.getInstance().getDegreeById(UUID.fromString("e82ab805-e4da-4858-85b7-545452d33ae1")), uscID, 0, null, null, null);
        accounts.getStudents().clear();
        accounts.addUser(person);
        DataWriterTesting.writeStudent();
        Student gage = DataLoaderTesting.getStudents().get(0);
        assertEquals("Gage", gage.getFirstName());
    }
    @Test
    public void writeAdvisors(){
        DataWriterTesting.writeAdvisorList();
        Advisor doc = DataLoaderTesting.getAdvisors().get(0);
        assertEquals("Dr. Jane ", doc.getFirstName());
    }
    @Test
    public void writeAdvisorsEmpty(){
        Advisor person = new Advisor(null, null, null, null, null, null);
        accounts.getAdvisors().clear();
        accounts.addUser(person);
        DataWriterTesting.writeAdvisorList();
        Advisor doc = DataLoaderTesting.getAdvisors().get(0);
        assertEquals("", doc.getFirstName());
    }
    @Test
    public void writeAdvisorsSingle(){
        Advisor person = new Advisor(UUID.randomUUID(), "Doc", "J", "The@Doc", "123", null);
        accounts.getAdvisors().clear();
        accounts.addUser(person);
        DataWriterTesting.writeAdvisorList();
        Advisor doc = DataLoaderTesting.getAdvisors().get(0);
        assertEquals("Doc", doc.getFirstName());
    }
    @Test
    public void writeDegrees(){
        DataWriterTesting.writeMajorList();
        assertEquals("Computer_Science", degrees.getList().get(0).getName());
    }
    @Test
    public void writeCourses(){
        DataWriterTesting.writeCourses();
        assertEquals("ENGL 101", courses.getCourses().get(0).getCourseName());
    }
}

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class DataLoaderTest {
    private UserList users = UserList.getInstance();
    private MajorList degreeList = MajorList.getInstance();
    private CourseList courseList = CourseList.getInstance();
    private ArrayList<Student> students = DataLoader.getStudents();
    private ArrayList<Advisor> advisors = DataLoader.getAdvisors();
    private ArrayList<Course> courses = DataLoader.getCourses();
    private ArrayList<Degree> degrees = DataLoader.getDegrees();


    @BeforeEach
    public void setUp() {
        students.clear();
        advisors.clear();
        courses.clear();
        degrees.clear();
    }

    @AfterEach
    public void tearDown() {
        users.getInstance().getAdvisors().clear();
        users.getInstance().getStudents().clear();
        degreeList.getInstance().getList().clear();
        courseList.getInstance().getCourses().clear();

    }

    //Testing Students Loading
    @Test
    public void seeIfStudentIsCreated() {
        students = DataLoader.getStudents();
        boolean isCreated = (students.get(0)!=null);
        assertTrue(isCreated);
    }

    @Test
    public void testGettingEmail() {
        students = DataLoader.getStudents();
        assertEquals("thill@gmail.com", students.get(0).getEmail());
    }

    @Test
    public void testGettingFirstName() {
        students = DataLoader.getStudents();
        assertEquals("Tawnie", students.get(0).getFirstName());
    }

    @Test
    public void getSizeOfArray() {
        students = DataLoader.getStudents();
        assertEquals(1, students.size());
    }

    @Test 
    void testIncorrectName() {
        students = DataLoader.getStudents();
        assertNotEquals("Brax", students.get(0).getFirstName());
    }

    //Testing Advisors Loading
    @Test
    public void advisorCreated() {
        advisors = DataLoader.getAdvisors();
        boolean isCreated = advisors.get(0) != null;
        assertTrue(isCreated);
    }

    @Test
    public void advisorEmailRead() {
        advisors = DataLoader.getAdvisors();
        assertEquals("Jdoe@gmail.com", advisors.get(0).getEmail());
    }

    @Test
    public void studentArrayCreated() {
        advisors = DataLoader.getAdvisors();
        assertEquals(3, advisors.get(0).getStudents().size());
    }

    @Test
    public void advisorLastNameRead() {
        advisors = DataLoader.getAdvisors();
        assertEquals("Doe", advisors.get(0).getLastName());
    }

    @Test
    public void correctStudentsLoaded() {
        students = DataLoader.getStudents();
        advisors = DataLoader.getAdvisors();
        Student one = UserList.getInstance().getStudentByID(UUID.fromString((String)"973d94b9-1b16-4b0a-a83c-a6b8cb0ffc20"));
        Student two = UserList.getInstance().getStudentByID(UUID.fromString((String)"b5bca4bf-1aba-4a05-a429-aaafdb59cfb7"));
        Student three = UserList.getInstance().getStudentByID(UUID.fromString((String)"18f80b3f-3227-4cf5-a61c-d29bc684b5d0"));
        Student[] arr = {one, two, three};
        Student[] arr2 = {advisors.get(0).getStudents().get(0), advisors.get(0).getStudents().get(1), advisors.get(0).getStudents().get(2)};
        assertArrayEquals(arr, arr2);
        //There was an issue with the comparison here
    }

    //Courses Test
    @Test
    public void coursesSize() {
        courses = DataLoader.getCourses();
        assertEquals(4, courses.size());
    }

    @Test
    public void containsTheCourse() {
        courses = DataLoader.getCourses();
        assertEquals("MATH 141", courses.get(1).getCourseName());
    }

    @Test
    public void readPrereqss() {
        courses = DataLoader.getCourses();
        String[] arr = {"MATH 111", "MATH 111I", "MATH 115"};
        assertArrayEquals(arr, courses.get(2).getPrerequisites().toArray());
    }

    @Test
    public void readCreditHours(){
        courses = DataLoader.getCourses();
        assertEquals(3, courses.get(0).getCreditHours());
    }

    @Test
    public void correctCategory(){
        courses = DataLoader.getCourses();
        Category cat = courses.get(0).getCategory();
        assertEquals(Category.PREREQUISITE, cat);
    }

    //Degrees Test

    @Test
    public void numberOfDegrees() {
        courses = DataLoader.getCourses();
        degrees = DataLoader.getDegrees();
        assertEquals(2, degrees.size());
    }

    @Test 
    public void numOfCourses() {
        courses = DataLoader.getCourses();
        degrees = DataLoader.getDegrees();
        assertEquals(32, degrees.get(0).getCourses().size());
    }

    @Test
    public void readDegreeType() {
        courses = DataLoader.getCourses();
        degrees = DataLoader.getDegrees();
        assertEquals(DegreeType.Computer_Science, degrees.get(0).getMajor());
    }

    @Test
    public void courseRead() {
        courses = DataLoader.getCourses();
        degrees = DataLoader.getDegrees();
        assertEquals("ENGL 101", degrees.get(0).getCourses().get(0).getCourseName());
    }
}

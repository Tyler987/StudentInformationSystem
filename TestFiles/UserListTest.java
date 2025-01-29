import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserListTest {
    private static UserList userList = UserList.getInstance();
    private ArrayList<Student> students = userList.getStudents();
    private ArrayList<Advisor> advisors = userList.getAdvisors();

    @BeforeEach
    public void setup() {
        students.clear();
        advisors.clear();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getStudents().clear();
        UserList.getInstance().getAdvisors().clear();
    }

    @Test
    public void addingANewAdvisor() {
        students = DataLoader.getStudents();
        advisors = DataLoader.getAdvisors();
        Advisor testAdvisor = new Advisor(UUID.randomUUID(), "Alandis", "Patterson", "alandisp6@gmail.com", "AP1234", null);
        UserList.getInstance().addUser(testAdvisor);
        assertEquals("Alandis", advisors.get(advisors.size()-1).getFirstName());
    }
    @Test
    public void addingANewStudent() {
        students = UserList.getInstance().getStudents();
        advisors = UserList.getInstance().getAdvisors();
        ArrayList<Degree> majors = DataLoader.getDegrees();
        Student newStudent = new Student(UUID.randomUUID(), "Alandis", "Patterson", "alandisp6@gmail.com", "AP1234", majors.get(0), "M375984", 3.9, null, null, null );
        UserList.getInstance().addUser(newStudent);
        assertEquals("Alandis", students.get(students.size()-1).getFirstName());
    }
    @Test
    public void addingNullUser() {
        students = UserList.getInstance().getStudents();
        advisors = UserList.getInstance().getAdvisors();
        UserList.getInstance().addUser(null);
        assertEquals(null, advisors.get(advisors.size()-1));
    }
    @Test 
    public void findStudentByEmail() {
        students = UserList.getInstance().getStudents();
        User newStudent = UserList.getInstance().getUser("bWest@gmail.com");
        assertEquals("Brax", newStudent.getFirstName());
    }
    @Test
    public void findAdvisorByEmail() {
        students = UserList.getInstance().getStudents();
        User newAdvisoUser = UserList.getInstance().getUser("Oodden@gmail.com");
        assertEquals("Odden", newAdvisoUser.getLastName());
    }
    @Test
    public void teestNullUser() {
        boolean isNull = UserList.getInstance().getUser(null) == null;
        assertTrue(isNull);
    }
    @Test
    public void getAStudentByID() {
        UUID id = UUID.fromString("619bd589-9a03-421b-aea9-98f5e3fe8793");
        Student newStu = UserList.getInstance().getStudentByID(id);
        assertEquals("V12589135", newStu.getUscID());
    }
    @Test
    public void getAStudentByIDNull() {
        UUID id = UUID.fromString("619bd589-9a03-421b-aea9-98f5e3fe8793");
        Student newStu = UserList.getInstance().getStudentByID(null);
        assertEquals(null, newStu);
    }
    @Test
    public void getStudentByRandomID() {
        UUID id = UUID.randomUUID();
        assertEquals(null, UserList.getInstance().getStudentByID(id));
    }

    @Test
    public void getStudentByFnLn() {
        students = DataLoader.getStudents();
        Student newStu = UserList.getInstance().getStudentByName("Brax", "West");
        assertEquals(newStu.getLastName(), students.get(0).getLastName());
    }
    @Test
    public void getStudentInvalidFnln() {
        Student newStu = UserList.getInstance().getStudentByName("Alandis", "Patterson");
        assertEquals(null, newStu);
    }
    @Test
    public void getStudentNullFnLn() {
        Student newStu = UserList.getInstance().getStudentByName(null, null);
        assertEquals(null, newStu);
    }
    @Test
    public void getStudentValidFnInValidLn() {
        Student newStu = UserList.getInstance().getStudentByName("Brax", "Jefferson");
        assertEquals(null, newStu);
    }
    @Test
    public void getStudentInvalidFnValidLn() {
        Student newStu = UserList.getInstance().getStudentByName("Brenda", "West");
        assertEquals(null, newStu);
    }
    @Test
    public void getAdvisorValidID() {
        UUID id = UUID.fromString("4d9d4dc2-7d3c-4437-a7a6-15c9b0f06a59");
        Advisor advisor = UserList.getInstance().getAdvisorByID(id);
        assertEquals(advisor.getFirstName(), advisors.get(0).getFirstName());
    }
    @Test
    public void getAdvisorInvalidID() {
        UUID id = UUID.randomUUID();
        Advisor advisor = UserList.getInstance().getAdvisorByID(id);
        assertEquals(null, advisor);
    }
    @Test
    public void getAdvisorNullID() {
        Advisor advisor = UserList.getInstance().getAdvisorByID(null);
        assertEquals(null, advisor);
    }



    
}

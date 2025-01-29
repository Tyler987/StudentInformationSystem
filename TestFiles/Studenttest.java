import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import javax.lang.model.type.NullType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class Studenttest {
    Student person;
    @BeforeEach
    public void EnrollStudent(){
        ArrayList<Course> MyCourses = new ArrayList<>();
        MyCourses.add(CourseList.getInstance().getCourse("ENGL 101"));
        Random rand = new Random();
        char initchar = (char) (rand.nextInt(26) + 'a');
        String uscID = initchar + "";
        for (int i = 0; i < 8; i++) {
            uscID += Integer.toString((rand.nextInt(9) + 1));
        }
        person = new Student(UUID.randomUUID(), "Gage", "Hulbert", "GH@email", "GH", MajorList.getInstance().getDegreeById(UUID.fromString("e82ab805-e4da-4858-85b7-545452d33ae1")), uscID, 0, MyCourses,new ArrayList<SemesterSchedule>(), new HashMap<Course, Double>());
    }
    @AfterEach
    public void expellStudent(){
        person = null;
    }
    @Test
    public void testme(){
        assertEquals("Gage", person.getFirstName());
    }
    @Test
    public void completeaclass(){
        person.completeCourse(UUID.fromString("361a8573-0b0b-4738-becc-2b178b78fb25"), 4.0);
        assertEquals(4.0, person.getCompletedCourses().get(CourseList.getInstance().getCourse("ENGL 101")));
    }
}

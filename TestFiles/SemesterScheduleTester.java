//TK
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SemesterScheduleTester {

    private SemesterSchedule schedule;




    @BeforeEach
    public void setUp() {
        schedule = new SemesterSchedule(new ArrayList<>(), 1);

    }

    @AfterEach
    public void tearDown() {
        schedule = null;

    }

    @Test
    public void testAddCourse() {
    //add course with add course
        schedule.addCourse(UUID.randomUUID(), "Course 1", Category.GFL, 3, new ArrayList<>());

    //check
        assertEquals(1, schedule.getCourses().size());
    }

    @Test
    public void testPrintSS() {
    //add courses
        schedule.addCourse(UUID.randomUUID(), "Course 1", Category.GFL, 3, new ArrayList<>());
        schedule.addCourse(UUID.randomUUID(), "Course 2", Category.GFL, 3, new ArrayList<>());

    //pritn schedule
        String expected = "Semester Number: 1\n==========================================\n" +
                          "Course 1\nCourse 2\nAIU: \n";
        assertEquals(expected, schedule.printSS());
    }

    @Test
    public void testPrintSSCompleted() {
    //add completed courses
        HashMap<Course, Double> completedCourses = new HashMap<>();
        Course course1 = new Course(UUID.randomUUID(), "Course 1", Category.GFL, 3, new ArrayList<>());
        Course course2 = new Course(UUID.randomUUID(), "Course 2", Category.GFL, 3, new ArrayList<>());
        completedCourses.put(course1, 4.0); // Grade 4.0
        completedCourses.put(course2, 3.5); // Grade 3.5
        schedule.getCompCourseL().add(completedCourses);

    //prints courses
        String expected = "Semester Number: 1\n==========================================\n" +
                          "Course 1     Grade: 4.0\nCourse 2     Grade: 3.5\n";
        assertEquals(expected, schedule.printSSCompleted());
    }
}
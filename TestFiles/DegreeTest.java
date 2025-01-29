//Tested by Tyler K
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
public class DegreeTest {
    
    private ArrayList<Degree> degrees;

    @BeforeEach
    public void setUp() {
        degrees = new ArrayList<>();
        degrees.add(new Degree(UUID.randomUUID(), DegreeType.Computer_Science, 120, new ArrayList<Course>(), new SchedulePlanner(null)));
        degrees.add(new Degree(UUID.randomUUID(), DegreeType.Computer_Information_Systems, 130, new ArrayList<Course>(), new SchedulePlanner(null)));
    }

    @AfterEach
    public void tearDown() {
        degrees.clear();
    }

    @Test
    public void testNumberOfDegrees() {
        assertEquals(2, degrees.size());
    }

    @Test
    public void testNumOfCourses() {
        assertEquals(0, degrees.get(0).getCourses().size());
        assertEquals(0, degrees.get(1).getCourses().size());
    }

    @Test
    public void testReadDegreeType() {
        assertEquals(DegreeType.Computer_Science, degrees.get(0).getMajor());
        assertEquals(DegreeType.Computer_Information_Systems, degrees.get(1).getMajor());
    }

    @Test
    public void testCourseRead() {
        assertTrue(degrees.get(0).getCourses().isEmpty());
        assertTrue(degrees.get(1).getCourses().isEmpty());
    }
        
    @Test
    public void testGetName() {
        assertEquals(DegreeType.Computer_Science, degrees.get(0).getName());
        assertEquals(DegreeType.Computer_Information_Systems, degrees.get(1).getName());
    }
    
    @Test
    public void testGetCreditHours() {
        assertEquals(120, degrees.get(0).getCreditHours());
        assertEquals(130, degrees.get(1).getCreditHours());
    }
    
    @Test
    public void testGetID() {
        assertNotNull(degrees.get(0).getID());
        assertNotNull(degrees.get(1).getID());
    }
    
    @Test
    public void testGetCourses() {
    //no courses
        assertEquals(0, degrees.get(0).getCourses().size());
        
    //one courses
        degrees.get(0).getCourses().add(new Course("Course 1", "CSE101", 4));
        assertEquals(1, degrees.get(0).getCourses().size());
        
    //two courses
        degrees.get(0).getCourses().add(new Course("Course 2", "CSE102", 3));
        assertEquals(2, degrees.get(0).getCourses().size());
        
    //three courses
        degrees.get(0).getCourses().add(new Course("Course 3", "CSE103", 3));
        assertEquals(3, degrees.get(0).getCourses().size());
        
    //four courses
        degrees.get(0).getCourses().add(new Course("Course 4", "CSE104", 3));
        degrees.get(0).getCourses().add(new Course("Course 5", "CSE105", 3));
        assertEquals(5, degrees.get(0).getCourses().size());
    }
    
    @Test
    public void testGetMajor() {
        assertEquals(DegreeType.Computer_Science, degrees.get(0).getMajor());
        assertEquals(DegreeType.Computer_Information_Systems, degrees.get(1).getMajor());
    }
    
    @Test
    public void testGetTotalCreditHours() {
        assertEquals(120, degrees.get(0).getTotalCreditHours());
        assertEquals(130, degrees.get(1).getTotalCreditHours());
    }
    
    @Test
    public void testPrintPlanner() {
    //no courses
        String expected1 = "Major: Computer_Science\nCredit Hours: 120\nCourses:\n";
        assertEquals(expected1, degrees.get(0).printPlanner());
        
    //one course
        degrees.get(0).getCourses().add(new Course("Course 1", "CSE101", 4));
        String expected2 = "Major: Computer_Science\nCredit Hours: 120\nCourses:\nCourse 1 (CSE101) - Credits: 4\n";
        assertEquals(expected2, degrees.get(0).printPlanner());
        
    //two courses
        degrees.get(0).getCourses().add(new Course("Course 2", "CSE102", 3));
        String expected3 = "Major: Computer_Science\nCredit Hours: 120\nCourses:\nCourse 1 (CSE101) - Credits: 4\nCourse 2 (CSE102) - Credits: 3\n";
        assertEquals(expected3, degrees.get(0).printPlanner());
        
    //three courses
        degrees.get(0).getCourses().add(new Course("Course 3", "CSE103", 3));
        String expected4 = "Major: Computer_Science\nCredit Hours: 120\nCourses:\nCourse 1 (CSE101) - Credits: 4\nCourse 2 (CSE102) - Credits: 3\nCourse 3 (CSE103) - Credits: 3\n";
        assertEquals(expected4, degrees.get(0).printPlanner());
        
    //four courses
        degrees.get(0).getCourses().add(new Course("Course 4", "CSE104", 3));
        degrees.get(0).getCourses().add(new Course("Course 5", "CSE105", 3));
        String expected5 = "Major: Computer_Science\nCredit Hours: 120\nCourses:\nCourse 1 (CSE101) - Credits: 4\nCourse 2 (CSE102) - Credits: 3\nCourse 3 (CSE103) - Credits: 3\nCourse 4 (CSE104) - Credits: 3\nCourse 5 (CSE105) - Credits: 3\n";
        assertEquals(expected5, degrees.get(0).printPlanner());
    }
    
    @Test
    public void testGetPlanner() {
        //degree with not null
        assertNotNull(degrees.get(0).getPlanner());
        
        //degree with null
        assertNull(degrees.get(1).getPlanner());
        
        //degree different non null
        assertNotNull(degrees.get(1).getPlanner());
        
        //degree another null
        assertNull(degrees.get(0).getPlanner());
        
        //degree with another null
        assertNotNull(degrees.get(0).getPlanner());
    }
}
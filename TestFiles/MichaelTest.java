import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MichaelTest {
  private Library library = new Library();
  private CourseList courseList = CourseList.getInstance();
  private StudentList studentList = StudentList.getInstance();
  private UserList userList = UserList.getInstance();

  @Test
  public void testCreateAccountAdvisor() {
    library.createAccount("advisor@gmail.com", "password123", "Michael", "Lait", true);
    library.createAccount("advisor@gmail.com", "password123", "Michael", "Lait", true); // Attempt to create duplicate account
    library.createAccount("", "", "", "", true); // Invalid input
    library.createAccount(null, null, null, null, true); // Null input
    library.createAccount("advisor3@gmail.com", "password123", "Misha", "Laitro", false); // Incorrect isAdvisor value
  }

  @Test
  public void testCreateAccountStudent() {
    library.createAccount("student@gmail.com", "password123", "Michael", "Lait", "Computer Science");
    library.createAccount("student@gmail.com", "password123", "Michael", "Lait", "Computer Science"); // Attempt to create duplicate account
    library.createAccount("", "", "", "", ""); // Invalid input
    library.createAccount(null, null, null, null, (Boolean) null); // Null input
    library.createAccount("student@gmail.com", "password123", "Jane", "Doe", ""); // Empty Major
  }

  @Test
  public void testLogin() {
    library.createAccount("student@gmail.com", "password123", "Jane", "Doe", "Computer Science");
    library.login("student@gmail.com", "password123"); // Correct login
    library.login("student@gmail.com", "wrongpassword"); // Incorrect password
    library.login("nonexistent@gmail.com", "password123"); // Non-existent user
    library.login("", ""); // Empty input
    library.login(null, null); // Null input
  }

  @Test
  public void testFindCourse() {
    courseList.getCourse("CSCE 101");
    library.findCourse("CSCE 101"); // Course exists
    library.findCourse("CSCE 999"); // Course does not exist
    library.findCourse(""); // Empty course name
    library.findCourse(null); // Null course name
    library.findCourse(" "); // Whitespace course name
  }

  @Test
  public void testEnrollCourse() {
    library.createAccount("student@gmail.com", "password123", "Michael", "Lait", "Computer Science");
    courseList.getCourse("CSCE 101");
    library.enrollCourse("CSCE 101"); // Course enrollment successful
    library.enrollCourse("CSCE 999"); // Course does not exist
    library.enrollCourse(""); // Empty course name
    library.enrollCourse(null); // Null course name
    library.enrollCourse(" "); // Whitespace course name
  }

}

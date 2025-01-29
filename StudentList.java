import java.util.ArrayList;
import java.util.UUID;

public class StudentList {
  private static StudentList instance;
  // private ArrayList<Student> students;
  private static StudentList studentLists;
  private ArrayList<Student> students;

  private StudentList() {
    students = new ArrayList<>();
    students = DataLoader.getStudents();
    //advisors = DataLoader.getAdvisors();
  }

  public static StudentList getInstance() {
    if (instance == null) {
      instance = new StudentList();
    }
    return instance;
  }
  // public StudentList getInstance() {
  //   if (instance == null) {
  //     instance = new StudentList();
  //   }
  //   return instance;
  // }
  public User getUser(String email) {
    for (Student student : students) {
      if (student.getEmail().equals(email)) {
        return student;
      }
    }
    return null; // Return null if no user is found
  }

  // public ArrayList<Student> getStudents() {
  //   return students;
  // }

  public ArrayList<Student> getStudents() {
    return students;
  }
  public Student getStudentByIDAdVIS(UUID id) {
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getUuid().equals(id)) {
        return students.get(i);
      }
    }
    return null;
  }

}

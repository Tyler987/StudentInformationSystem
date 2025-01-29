import java.util.ArrayList;
import java.util.UUID;

public class UserList {
  private static UserList instance;
  private ArrayList<Student> students;
  private ArrayList<Advisor> advisors;

  private UserList() {
    students = new ArrayList<>();
    advisors = new ArrayList<>();
    students = DataLoader.getStudents();
    advisors = DataLoader.getAdvisors();
    //loadUsers();
  }

  public static UserList getInstance() {
    if (instance == null) {
      instance = new UserList();
    }
    return instance;
  }
  public void addUser(User person){
    if(person.getClass().getSimpleName()=="Advisor"){
      advisors.add((Advisor)person);
    }else{
      students.add((Student)person);
    }

  }
  public User getUser(String email) {
    for (Student student : students) {
      if (student.getEmail().equals(email)) {
        return student;
      }
    }
    for (Advisor advisor : advisors) {
      if (advisor.getEmail().equals(email)) {
        return advisor;
      }
    }
    return null; // Return null if no user is found
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public ArrayList<Advisor> getAdvisors() {
    return advisors;
  }

  public Student getStudentByID(UUID id) {
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getUuid().equals(id)) {
        return students.get(i);
      }
    }
    return null;
  }
  public Student getStudentByName(String first, String last) {
    for (int i = 0; i < students.size(); i++) {
      if (students.get(i).getFirstName().equalsIgnoreCase(first)||students.get(i).getFirstName().equalsIgnoreCase(last)) {
        return students.get(i);
      }
    }
    return null;
  }

  public Advisor getAdvisorByID(UUID id) {
    for (int i = 0; i < advisors.size(); i++) {
      if (advisors.get(i).getUuid().equals(id)) {
        return advisors.get(i);
      }
    }
    return null;
  }
}

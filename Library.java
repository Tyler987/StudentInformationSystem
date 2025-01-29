import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Library {
  private UserList userList;
  private CourseList courseList;
  private User currentUser;
  private DataWriter write;
  private Elective electives;

  public Library() {
    userList = UserList.getInstance();
    courseList = CourseList.getInstance();
    //electives = new Elective();
  }

  // Creates a new user account
  public boolean createAccount(String email, String password, String firstname, String lastname, Boolean isAdvisor) {
    ArrayList<Student> MyStudents = new ArrayList<>();
    if (!login(email, password)) {
      currentUser = new Advisor(UUID.randomUUID(), firstname, lastname, email, password, MyStudents);
      userList.addUser(currentUser);
    }
    return true;
  }

  public boolean createAccount(String email, String password, String firstname, String lastname, String Major) {
    ArrayList<Course> MyCourses = new ArrayList<>();
    Random rand = new Random();
    char initchar = (char) (rand.nextInt(26) + 'a');
    String uscID = initchar + "";
    for (int i = 0; i < 8; i++) {
      uscID += Integer.toString((rand.nextInt(9) + 1));
    }
    // students.add(new Student(id, firstName, lastName, email, password, major,
    // uscId, gpa, courses, completedS, allCourses));
    if (!login(email, password)) {
      Degree degty = new Degree(null, null, initchar, MyCourses, null);
      if (Major.equals(1)) {
        degty = MajorList.getInstance().getDegreeById(UUID.fromString("e82ab805-e4da-4858-85b7-545452d33ae1"));
      } else if (Major.equals(2)) {
        degty = MajorList.getInstance().getDegreeById(UUID.fromString("0649889f-5360-4e6d-9ca8-1777f0edaba1"));
      }
      currentUser = new Student(UUID.randomUUID(), firstname, lastname, email, password, degty, uscID, 0.0, MyCourses,
          new ArrayList<SemesterSchedule>(), new HashMap<Course, Double>());
      userList.addUser(currentUser);
    }
    // currentUser = new Student(UUID.randomUUID(), firstname, lastname, email,
    // password,
    // MajorList.getInstance().getDegreeById(majorid), uscID, 0.0, MyCourses);
    return true;
  }

  // THERE IS NO LOAD USERS IN DATALOADER so load students and advisors
  // tyler
  public boolean login(String email, String password) {
    // userList.loadUsers();
    userList.getStudents(); // Load users before attempting to log in
    userList.getAdvisors();
    currentUser = userList.getUser(email);
    if (currentUser != null && currentUser.getPassword().equals(password)) {
      return true; // Successful login
    } else {
      currentUser = null;
      return false; // Incorrect email or password
    }
  }
  // public boolean login(String email,String password) {
  // userList.loadUsers(); // Load users before attempting to log in
  // currentUser = userList.getUser(email);
  // return currentUser != null;
  // }

  public User getCurrentUser() {
    return currentUser;
  }

  // Returns true if course is found, and false otherwise
  public boolean findCourse(String courseName) {
    return courseList.getCourse(courseName) != null;
  }

  public boolean enrollCourse(String courseName) {
    if (!findCourse(courseName)) {
      return false; // Course not found
    }

    if (currentUser == null) {
      return false; // No user is currently logged in
    }

    // Get the course to enroll from the course list
    Course courseToEnroll = courseList.getCourse(courseName);
    if (courseToEnroll == null) {
      return false; // Course not found, should not happen if findCourse passed
    }

    // Check if the user is a student
    if (currentUser instanceof Student) {
      // Cast currentUser to Student
      Student student = (Student) currentUser;

      // Enroll the student in the course
      student.addCourse(courseToEnroll);

      return true; // Enrollment successful
    } else {
      return false; // Current user is not a student
    }
  }
  // public boolean enrollCourse(String courseName) {
  // if (!findCourse(courseName)) {
  // return false; // Course not found
  // }

  // if (currentUser == null) {
  // return false; // No user is currently logged in
  // }

  // // Assuming you have a method in your Course class to get a course by its
  // name
  // Course courseToEnroll = courseList.getCourse(courseName);
  // if (courseToEnroll == null) {
  // return false; // Course not found, should not happen if findCourse passed
  // }

  // // Enroll the current user in the course
  // currentUser.addCourse(courseToEnroll);

  // return true; // Enrollment successful
  // }

  public void logout() {
    // write.writeStudent();
    // write.writeAdvisorList();
  }

  // public void displayStudentDataByLastName(String lastName) {
  // ArrayList<Student> students = userList.getStudents();
  // ArrayList<Course> courses = courseList.getCourses();

  // for (Student student : students) {
  // if (student.getLastName().equals(lastName)) {
  // System.out.println("First Name: " + student.getFirstName());
  // System.out.println("Last Name: " + student.getLastName());

  // Degree studentDegree = student.getMajor();
  // if (studentDegree != null) {
  // System.out.println("Major: " + studentDegree.getName());
  // System.out.println("Total Major Credit Hours: " +
  // studentDegree.getTotalCreditHours());
  // } else {
  // System.out.println("Major: Unknown");
  // System.out.println("Total Major Credit Hours: Unknown");
  // }

  // System.out.println("Email: " + student.getEmail());
  // System.out.println("Password: " + student.getPassword());
  // System.out.println("USC ID: " + student.getUscID());
  // System.out.println("UUID: " + student.getUuid());

  // System.out.println("Courses:");
  // double totalCreditHours = 0.0;
  // for (Course course : student.getCourses()) {
  // System.out.println("- " + course.getCourseName() + " (" +
  // course.getCreditHours() + " credit hours)");
  // totalCreditHours += course.getCreditHours();
  // }

  // System.out.println("Completed Courses:");
  // Map<Course, Double> completedCourses = student.getCompletedCourses();
  // for (Map.Entry<Course, Double> entry : completedCourses.entrySet()) {
  // Course course = entry.getKey();
  // double grade = entry.getValue();
  // System.out.println("- " + course.getCourseName() + " (" +
  // course.getCreditHours() + " credit hours) (Grade: " + grade + ")");
  // totalCreditHours += course.getCreditHours();
  // }

  // System.out.println("Total Completed Credit Hours: " + totalCreditHours);

  // System.out.println("Courses Not Taken Yet:");
  // for (Course degreeCourse : studentDegree.getCourses()) {
  // boolean taken = false;
  // for (Course studentCourse : student.getCourses()) {
  // if (degreeCourse.getId().equals(studentCourse.getId())) {
  // taken = true;
  // break;
  // }
  // }
  // if (!taken && !student.getCompletedCourses().containsKey(degreeCourse)) {
  // System.out.println("- " + degreeCourse.getCourseName() + " (" +
  // degreeCourse.getCreditHours() + " credit hours)");
  // }
  // }

  // return;
  // }
  // }

  // System.out.println("Student with last name " + lastName + " not found.");
  // }
  public void displayStudentDataByLastName(String lastName) {
    ArrayList<Student> students = userList.getStudents();
    ArrayList<Course> courses = courseList.getCourses();

    for (Student student : students) {
      if (student.getLastName().equals(lastName)) {
        System.out.println("First Name: " + student.getFirstName());
        System.out.println("Last Name: " + student.getLastName());
        Degree studentDegree = student.getMajor();
        if (studentDegree != null) {
          System.out.println("Major: " + studentDegree.getName());
          System.out.println("Total Major Credit Hours: " + studentDegree.getTotalCreditHours());
        } else {
          System.out.println("Major: Unknown");
          System.out.println("Total Major Credit Hours: Unknown");
        }
        System.out.println("Email: " + student.getEmail());
        System.out.println("Password: " + student.getPassword());
        System.out.println("USC ID: " + student.getUscID());
        System.out.println("UUID: " + student.getUuid());
        System.out.println("Courses:");
        double totalCreditHours = 0.0;
        for (Course course : student.getCourses()) {
          System.out.println("- " + course.getCourseName() + " (" + course.getCreditHours() + " credit hours)");
          totalCreditHours += course.getCreditHours();
        }
        System.out.println("Completed Courses:");
        Map<Course, Double> completedCourses = student.getCompletedCourses();
        for (Map.Entry<Course, Double> entry : completedCourses.entrySet()) {
          Course course = entry.getKey();
          double grade = entry.getValue();
          System.out.println(
              "- " + course.getCourseName() + " (" + course.getCreditHours() + " credit hours) (Grade: " + grade + ")");
          totalCreditHours += course.getCreditHours();
        }
        System.out.println("Total Completed Credit Hours: " + totalCreditHours);
        System.out.println("Courses Not Taken Yet:");
        double totalRemainingCreditHours = 0.0;
        for (Course degreeCourse : studentDegree.getCourses()) {
          boolean taken = false;
          for (Course studentCourse : student.getCourses()) {
            if (degreeCourse.getId().equals(studentCourse.getId())) {
              taken = true;
              break;
            }
          }
          if (!taken && !student.getCompletedCourses().containsKey(degreeCourse)) {
            System.out
                .println("- " + degreeCourse.getCourseName() + " (" + degreeCourse.getCreditHours() + " credit hours)");
            totalRemainingCreditHours += degreeCourse.getCreditHours();
          }
        }
        System.out.println("Total Remaining Credit Hours: " + totalRemainingCreditHours);
        return;
      }
    }
    System.out.println("Student with last name " + lastName + " not found.");
  }

  public String getElectivesTypeByName(String name) {

    if (name.equalsIgnoreCase("gss")) {
      return electives.listGSS();
    } else if (name.equalsIgnoreCase("gfs")) {
      return electives.listGFLs();
    } else if (name.equalsIgnoreCase("sciences")) {
      return electives.listSciences();
    } else if (name.equalsIgnoreCase("liberal arts")) {
      return electives.listLiberalArts();
    } else if (name.equalsIgnoreCase("csce electives")) {
      return electives.listCSCEElectives();
    } else {
      return "Elective not Found";
    }

  }

}

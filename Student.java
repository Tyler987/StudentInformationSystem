import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.*;

public class Student extends User {
    private UUID uid;
    private String uscID;
    private SemesterSchedule schedule;
    private double gpa;
    private ArrayList<Course> courses;
    private LinkedHashMap<Course, Double> completedCourses;
    public String id;
    public String password;
    public Degree major;
    private ApplicationArea aa;
    private SemesterSchedule currentSemester;
    private ArrayList<SemesterSchedule> arr;
    private int semesterNum = 1;
    private ArrayList<Course> neededCourses;

    public Student(UUID uid, String firstName, String lastName, String email, String password, Degree major,
            String uscId, double gpa, ArrayList<Course> courses, LinkedHashMap<Course, Double> completedCourses) {
        super(uid, firstName, lastName, email, password);
        this.major = major;
        this.uscID = uscId;
        this.gpa = gpa;
        this.courses = courses;
        this.completedCourses = completedCourses;
        neededCourses = new ArrayList<>();
        neededCourses(completedCourses);
    }

    public void setSemesterSchedules (ArrayList<SemesterSchedule> arr) {
        this.arr = arr; 
    }
    public void neededCourses(HashMap<Course, Double> completedC) {
        ArrayList<Course> cCourses = new ArrayList<Course>();
        for(Course course: completedC.keySet()){
            cCourses.add(course);
        }
        if(major.getName().equals(DegreeType.Computer_Science)) {
            Degree degree = DataLoader.getDegrees().get(0);
            ArrayList<Course> degreeCourses = degree.getCourses();
            for(int i = 0; i < degreeCourses.size(); i++) {
                boolean found = false;
                for(int j = 0; j < cCourses.size(); j++) {
                    if(degreeCourses.get(i).getCourseName().equals(cCourses.get(j).getCourseName())) {
                        found = true;
                        break;
                    }
                    else {
                        found = false;
                    }
                }
                if(found == false) {
                    neededCourses.add(degreeCourses.get(i));
                }

            }
        }
    }

    public void loadCompletedCourses() {
        if(major.equals(DegreeType.Computer_Science)) {
            

        }
    }

    public ArrayList<Course> getNeededCourses() {
        return neededCourses;
    }
    public int searchForCourse(String theName, ArrayList<Course> arr) {
        for(int i = 0; i < arr.size(); i++) {
            if(theName.equals(arr.get(i).getCourseName())) {
                return 1;
            }
        }
        return 0;

    }
    public DegreeType getDegreeType() {
        return major.getMajor();
    }
    public UUID getDegreeID(){
        return major.getID();
    }
    public String getUscID() {
        return uscID;
    }

    public double getGPA() {
        return gpa;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public LinkedHashMap<Course, Double> getCompletedCourses() {
        return completedCourses;
    }

    public void completeCourse(UUID target, double grade) {
        int indy = -1;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == target) {
                indy = i;
                break;
            }
        }
        if (indy >= 0) {
            completedCourses.put(courses.get(indy), grade);
            completedCourses.remove(indy);
        }
    }

    public void setUscID(String uscID) {
        this.uscID = uscID;
    }

    public void setSchedule(SemesterSchedule schedule) {
        this.schedule = schedule;
    }
    public ArrayList<SemesterSchedule> getPastSems(){
        return arr;
    }
    public void setGPA(double gpa) {
        this.gpa = gpa;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void setCompletedCourses(LinkedHashMap<Course, Double> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void calculateGPA() {
        double totalCredits = 0.0;
        double totalGradePoints = 0.0;

        for (Course course : completedCourses.keySet()) {
            double grade = convertToGPA(completedCourses.get(course));
            int creditHours = course.getCreditHours();
            totalGradePoints += grade * creditHours;
            totalCredits += creditHours;
        }

        if (totalCredits > 0) {
            this.gpa = totalGradePoints / totalCredits;
        } else {
            this.gpa = 0.0;
        }
    }

    public String printStudentInfo() {
        String str = super.toString() + "\n";
        str = str + "Student ID: " + uscID + "\n" + "Major Name: " + this.getDegreeType() + "\n" + "GPA: "
                + gpa + "\nDegree Progress: " + calculateDegreeProgress() + "%";
        return str;

    }

    public double calculateDegreeProgress() {
        double num = 0;
        for (Course course : completedCourses.keySet()) {
            num = num + course.getCreditHours();
        }
        return Math.round(num / major.getCreditHours() * 100);
    }
    public void addCourse(Course course) {
        // Add the course to the list of courses for the student
        courses.add(course);
    }

    public Degree getMajor() {
        return major;
    }

    public String printCompletedCourses() {
        String str = "Completed Courses: \n";
        for (Course course: completedCourses.keySet()) {
            str = str + course.getCourseName() + "\t" + completedCourses.get(course) + "\n";
        }
        return str;
    }

    public double convertToGPA(double num) {
        if(num >= 97 && num <= 100) {
            return 4.0;
        } else if (num >= 93 && num <= 96) {
            return 4.0;
        } else if(num >= 90 && num <= 92) {
            return 3.7;
        } else if (num >= 87 && num <= 89) {
            return 3.3;
        } else if (num >= 83 && num <= 86) {
            return 3.0;
        } else if (num >= 80 && num <= 82) {
            return 2.7;
        } else if (num >= 77 && num <= 79) {
            return 2.3;
        } else if (num >= 73 && num <= 76) {
            return 2.0;
        } else if (num >= 70 && num <= 72) {
            return 1.7;
        } else if(num >= 67 && num <= 69) {
            return 1.3;
        } else if (num >= 65 && num <= 66) {
            return 1.0;
        } else {
            return 0.0;
        }   
    }





}
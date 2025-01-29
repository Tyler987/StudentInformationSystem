
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoaderTesting extends DataConstants {

    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            FileReader reader = new FileReader("src/COURSE_FILE_NAME.json");
            JSONParser parser = new JSONParser();
            JSONArray courseListJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < courseListJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) courseListJSON.get(i);
                UUID id = UUID.fromString((String) courseJSON.get(COURSE_ID));
                String courseName = (String) courseJSON.get(COURSE_NAME);
                int creditHours = ((Long) courseJSON.get(COURSE_CREDIT_HOURS)).intValue();
                // UUID degreeId = UUID.fromString((String) courseJSON.get("degreeId"));
                String category = (String) courseJSON.get(COURSE_CATEGORY_ID);
                Category courseCat;
                if (category.contains("GFL")) {
                    courseCat = Category.GFL;
                } else if (category.contains("GSS")) {
                    courseCat = Category.GSS;
                } else if (category.contains("APPLICATION_AREA")) {
                    courseCat = Category.APPLICATION_AREA;
                } else if (category.contains("LAB_SCIENCE_ELECTIVE")) {
                    courseCat = Category.LAB_SCIENCE_ELECTIVE;
                } else if (category.contains("CSCE_ELECTIVE")) {
                    courseCat = Category.CSCE_ELECTIVE;
                } else if (category.contains("LIBERAL_ARTS")) {
                    courseCat = Category.LIBERAL_ARTS;
                } else if (category.contains("PREREQUISITE")) {
                    courseCat = Category.PREREQUISITE;
                } else if (category.contains(("GHS"))) {
                    courseCat = Category.GHS;
                } else if (category.contains("AIU")) {
                    courseCat = Category.AIU;
                } else if (category.contains("CMS")) {
                    courseCat = Category.CMS;
                } else {
                    courseCat = Category.COREREQUISITE;
                }
                ArrayList<String> prerequisites = new ArrayList<>();
                JSONArray prereqArray = (JSONArray) courseJSON.get(COURSE_PREREQUISITES_ID);
                if (prereqArray != null) {
                    for (int m = 0; m < prereqArray.size(); m++) {
                        String prereqName = ((String) prereqArray.get(m));
                        prerequisites.add(prereqName);
                    }
                }

                Course course = new Course(id, courseName, courseCat, creditHours, prerequisites);
                courses.add(course);
            }
            return courses;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

        public static ArrayList<Student> getStudents() {
            ArrayList<Student> students = new ArrayList<>();
    
            try {
                FileReader reader = new FileReader("src/STUDENT_FILE_NAME.json");
                JSONParser parser = new JSONParser();
                JSONArray studentListJSON = (JSONArray) parser.parse(reader);
                
                for(int i = 0; i < studentListJSON.size(); i++) {
                    JSONObject studentJSON = (JSONObject) studentListJSON.get(i);
                    UUID id = UUID.fromString((String)studentJSON.get(STUDENT_ID));
                    String firstName = (String) studentJSON.get(STUDENT_FIRST_NAME);
                    String lastName = (String) studentJSON.get(STUDENT_LAST_NAME);
                    String email = (String) studentJSON.get(STUDENT_EMAIL);
                    String password = (String) studentJSON.get(STUDENT_PASSWORD);
                    // 
                    // UUID majorId = MajorList.getInstance().getDegreeByID(UUID.fromString((String)studentJSON.get(STUDENT_DEGREE_ID)));
                    UUID majorId = UUID.fromString((String) studentJSON.get(STUDENT_DEGREE_ID)); // Parse major ID
                    Degree major = MajorList.getInstance().getDegreeById(majorId);
                    String uscId = (String) studentJSON.get(STUDENT_USC_ID);
                    double gpa = Double.parseDouble(studentJSON.get(STUDENT_GPA).toString());
                    JSONArray coursesArray = (JSONArray) studentJSON.get(STUDENT_COURSES);
                    ArrayList<Course> courses = new ArrayList<>();
                    if(coursesArray.size() > 0) {
                        for(int m = 0; m < coursesArray.size(); m++) {
                            UUID courseID = UUID.fromString((String)coursesArray.get(m));
                            Course course = CourseList.getInstance().getCourseByID(courseID);
                            courses.add(course);
                        }
                    }
                    JSONArray completedCoursesJson = (JSONArray) studentJSON.get(STUDENT_COMPLETED_COURSES);
                    ArrayList<SemesterSchedule> completedS = new ArrayList<SemesterSchedule>();
                    HashMap<Course, Double> allCourses = new HashMap<>();
                    for(int l = 0; l < completedCoursesJson.size(); l++) {
                        ArrayList<HashMap> arr = new ArrayList<HashMap>();
                        HashMap<Course, Double> completedHashMap = new HashMap();
                        JSONObject courseJSON = (JSONObject) completedCoursesJson.get(l);
                        int semesNum = ((Long) courseJSON.get(STUDENT_COMPLETED_COURSES_PREFERRED_SEMESTER)).intValue();
                        JSONArray iDsJSON = (JSONArray) courseJSON.get(STUDENT_COMPLETED_COURSES_COURSE_ID);
                        ArrayList<Course> semCourses = new ArrayList<Course>();
                        if(iDsJSON.size() > 0) {
                            for (int k = 0; k < iDsJSON.size(); k++) {
                                UUID courseID = UUID.fromString((String) iDsJSON.get(k));
                                Course theCourse = CourseList.getInstance().getCourseByID(courseID);
                                semCourses.add(theCourse);
                            }
                            JSONArray gradesJSON = (JSONArray) courseJSON.get(STUDENT_COMPLETED_COURSES_FINAL_GRADE);
                            ArrayList<Double> grades = new ArrayList<Double>();
                            for(int n = 0; n < gradesJSON.size(); n++) {
                                double num =  Double.parseDouble(gradesJSON.get(n).toString());
                                grades.add(num);
                            }
                            for(int a = 0; a < semCourses.size(); a++) {
                                completedHashMap.put(semCourses.get(a), grades.get(a));
                                allCourses.put(semCourses.get(a), grades.get(a));
                            }
                            arr.add(completedHashMap);
                            SemesterSchedule newS = new SemesterSchedule(semesNum, arr);
                            completedS.add(newS);
                        }
                    }
                    // public Student(UUID id, String firstName, String lastName, String email, String password, Degree major, String uscId, double gpa, ArrayList<Course> courses) {
                    students.add(new Student(id, firstName, lastName, email, password, major, uscId, gpa, courses, completedS, allCourses));
                    //System.out.println(completedS.size());
                }
                return students;
    
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return null;
        }

    public static ArrayList<Advisor> getAdvisors() {
        ArrayList<Advisor> advisors = new ArrayList<>();

        try {
            FileReader reader = new FileReader("src/ADVISOR_FILE_NAME.json");
            JSONParser parser = new JSONParser();
            JSONArray advisorListJSON = (JSONArray) parser.parse(reader);
            for (int i = 0; i < advisorListJSON.size(); i++) {
                JSONObject advisorJSON = (JSONObject) advisorListJSON.get(i);
                UUID id = UUID.fromString((String) advisorJSON.get(ADVISOR_ID));
                String firstName = (String) advisorJSON.get(ADVISOR_FIRST_NAME);
                String lastName = (String) advisorJSON.get(ADVISOR_LAST_NAME);
                String email = (String) advisorJSON.get(ADVISOR_EMAIL);
                String password = (String) advisorJSON.get(ADVISOR_PASSWORD);
                ArrayList<Student> students = new ArrayList<>();
                JSONArray studentsIDsJSON = (JSONArray) advisorJSON.get(ADVISOR_ADVISEES);
                if(studentsIDsJSON.size() > 0) {
                    for (int j = 0; j < studentsIDsJSON.size(); j++) {
                        UUID stuID = UUID.fromString((String) studentsIDsJSON.get(j));
                        Student newStu = StudentList.getInstance().getStudentByIDAdVIS(stuID);
                        students.add(newStu);
                    }
                }
                advisors.add(new Advisor(id, firstName, lastName, email, password, students));
            }
            return advisors;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Degree> getDegrees() {
        ArrayList<Degree> degrees = new ArrayList<>();

        try {
            FileReader reader = new FileReader("src/DEGREE_FILE_NAME.json");
            JSONParser parser = new JSONParser();
            JSONArray degreeListJSON = (JSONArray) parser.parse(reader);

            for (int i = 0; i < degreeListJSON.size(); i++) {
                JSONObject degreeJSON = (JSONObject) degreeListJSON.get(i);
                UUID id = UUID.fromString((String) degreeJSON.get(DEGREE_ID));
                String name = (String) degreeJSON.get(DEGREE_NAME);
                DegreeType major;
                if (name.contains("Computer_Science")) {
                    major = DegreeType.Computer_Science;
                } else {
                    major = DegreeType.Computer_Information_Systems;
                }
                ArrayList<Course> courses = new ArrayList<Course>();
                int totalCreditHours = ((Long) degreeJSON.get(DEGREE_TOTAL_CREDIT_HOURS)).intValue();
                JSONArray courseListJson = (JSONArray) degreeJSON.get(DEGREE_COURSES);
                ArrayList<SemesterSchedule> semesterSchedules = new ArrayList<SemesterSchedule>();
                for (int j = 0; j < courseListJson.size(); j++) {
                    JSONObject courseJSON = (JSONObject) courseListJson.get(j);
                    JSONArray iDsJSON = (JSONArray) courseJSON.get(DEGREE_COURSE_ID);
                    ArrayList<Course> semesCourses = new ArrayList<Course>();
                    for (int k = 0; k < iDsJSON.size(); k++) {
                        UUID courseID = UUID.fromString((String) iDsJSON.get(k));
                        Course theCourse = CourseList.getInstance().getCourseByID(courseID);
                        semesCourses.add(theCourse);
                        courses.add(theCourse);
                    }
                    int semesNum = ((Long) courseJSON.get(Degree_SEMESTER_NUM)).intValue();
                    SemesterSchedule newS = new SemesterSchedule(semesCourses, semesNum);
                    semesterSchedules.add(newS);
                }
                SchedulePlanner plan = new SchedulePlanner(semesterSchedules);
                Degree degree = new Degree(id, major, totalCreditHours, courses, plan);
                degrees.add(degree);
            }
            return degrees;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
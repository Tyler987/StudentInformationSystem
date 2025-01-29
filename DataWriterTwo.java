import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;

public class DataWriterTwo extends DataConstants {
    public static void writeStudent() {
        try {
            ArrayList<Student> students = UserList.getInstance().getStudents();
            JSONArray studList = new JSONArray();
            for (int i = 0; i < students.size(); i++) {
                JSONObject currStud = new JSONObject();
                currStud.put(STUDENT_FIRST_NAME, students.get(i).getFirstName());
                currStud.put(STUDENT_LAST_NAME, students.get(i).getLastName());
                currStud.put(STUDENT_EMAIL, students.get(i).getEmail());
                currStud.put(STUDENT_PASSWORD, students.get(i).getPassword());
                currStud.put(STUDENT_ID, students.get(i).getUuid());
                currStud.put(STUDENT_USC_ID, students.get(i).getUscID());
                currStud.put(STUDENT_GPA, students.get(i).getGPA());
                currStud.put(STUDENT_DEGREE_ID, students.get(i).getMajorId());
                ArrayList<Course> courses = students.get(i).getCourses();
                JSONArray jsonCourses = new JSONArray();
                for (int v = 0; v < courses.size(); v++) {
                    jsonCourses.add(courses.get(v).getId());
                }
                // for(int v=0;v<courses.size();v++){
                // JSONObject newcourse = courseToJson(courses.get(v));
                // jsonCourses.add(newcourse);
                // }
                // currStud.put(STUDENT_COURSES, jsonCourses);
                // Gson hashconverter = new Gson();
                // String convertedmap = hashconverter.toJson(students.get(i).getCompletedCourses());
                //JSONObject completedmap = new JSONObject();
                JSONArray convertedmap = new JSONArray();
                for(Map.Entry<Course,Double> entry: students.get(i).getCompletedCourses().entrySet()){
                    JSONObject completed = new JSONObject();
                    completed.put("UID", entry.getKey().getId());
                    completed.put("Value", entry.getValue());
                    convertedmap.add(completed);
                }
                currStud.put(STUDENT_COMP_COURSES, convertedmap);
                studList.add(currStud);
            }
            FileWriter output = new FileWriter(new File("./Gage/students.json"));
            output.write(studList.toJSONString());
            output.close();
        } catch (Exception e) {

        }
    }

    public static void writeCourses() {
        try {
            JSONArray courseList = new JSONArray();
            ArrayList<Course> courses = CourseList.getInstance().getCourses();
            for (int i = 0; i < courses.size(); i++) {
                JSONObject course = courseToJson(courses.get(i));
                // course.put(COURSE_NAME, courses.get(i).getCourseName());
                // course.put(COURSE_CATEGORY, courses.get(i).getCategory());
                // course.put(COURSE_DATE, courses.get(i).getDate());
                // course.put(COURSE_CREDIT_HOURS,courses.get(i).getCreditHours());
                // course.put(COURSE_TIME,courses.get(i).getTime());
                // course.put(COURSE_PROFESSOR,courses.get(i).getProfessorName());
                courseList.add(course);
            }
            FileWriter output = new FileWriter(new File(COURSE_FILE_NAME));
            output.write(courseList.toJSONString());
            output.close();
        } catch (Exception e) {

        }
    }

    public static void writeMajorList() {
        try {
            JSONArray majorList = new JSONArray();
            ArrayList<Degree> degrees = MajorList.getInstance().getList();
            for (int i = 0; i < degrees.size(); i++) {
                JSONObject degree = new JSONObject();
                degree.put(DEGREE_NAME, degrees.get(i).getName());
                degree.put(DEGREE_TOTAL_CREDIT_HOURS, degrees.get(i).getCreditHours());
                degree.put(DEGREE_ID, degrees.get(i).getID());
                JSONArray courseL = new JSONArray();
                ArrayList<Course> courses = degrees.get(i).getCourses();
                ArrayList<Course> elecs = degrees.get(i).getElectives();
                JSONArray elecL = new JSONArray();
                for (int v = 0; v < courses.size(); v++) {
                    courseL.add(courses.get(v).getId());
                }
                for (int v = 0; v < elecs.size(); v++) {
                    elecL.add(elecs.get(v).getId());
                }
                degree.put(DEGREE_COURSES, courseL);
                degree.put(DEGREE_ELECTIVES, elecL);
                majorList.add(degree);
            }
            FileWriter output = new FileWriter(new File(DEGREE_FILE_NAME));
            output.write(majorList.toJSONString());
            output.close();
        } catch (Exception e) {

        }
    }

    public static void writeAdvisorList() {
        try {
            ArrayList<Advisor> advisors = UserList.getInstance().getAdvisors();
            JSONArray advisorList = new JSONArray();
            for (int i = 0; i < advisors.size(); i++) {
                JSONObject advisor = new JSONObject();
                advisor.put(ADVISOR_FIRST_NAME, advisors.get(i).getFirstName());
                advisor.put(ADVISOR_LAST_NAME, advisors.get(i).getLastName());
                advisor.put(ADVISOR_PASSWORD, advisors.get(i).getPassword());
                advisor.put(ADVISOR_EMAIL, advisors.get(i).getEmail());
                advisor.put(ADVISOR_ID, advisors.get(i).getID());
                ArrayList<Student> studlist = advisors.get(i).getStudents();
                JSONArray studarray = new JSONArray();
                for (int v = 0; v < studlist.size(); v++) {
                    studarray.add(studlist.get(v).getUuid());
                }
                advisor.put(ADVISOR_ADVISEES, studarray);
                advisorList.add(advisor);
            }
            FileWriter output = new FileWriter(new File(ADVISOR_FILE_NAME));
            output.write(advisorList.toJSONString());
            output.close();
        } catch (Exception e) {

        }
    }

    private static JSONObject courseToJson(Course c) {
        JSONObject newcourse = new JSONObject();
        newcourse.put(COURSE_NAME, c.getCourseName());
        newcourse.put(COURSE_CATEGORY, c.getCategory());
        newcourse.put(COURSE_CREDIT_HOURS, c.getCreditHours());
        newcourse.put(COURSE_ID, c.getId());
        newcourse.put(COURSE_DEGREE_ID, c.getId());
        ArrayList<String> prereqL = c.getPrerequisites();
        JSONArray prereqsjson = new JSONArray();
        for (int i = 0; i < prereqL.size(); i++) {
            prereqsjson.add(prereqL.get(i));
        }
        // ArrayList<Course> prereqL = c.getPrerequisites();
        // JSONArray prereqsjson = new JSONArray();
        // for (int i = 0; i < prereqL.size(); i++) {
        //     if (prereqL.get(i) != null) {
        //         JSONObject newpre = courseToJson(prereqL.get(i));
        //         prereqsjson.add(newpre);
        //     }
        // }
        newcourse.put(COURSE_PREREQUISITES_ID, prereqsjson);
        return newcourse;
    }
}

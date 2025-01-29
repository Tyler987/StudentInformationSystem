import java.util.*;
public class AlandisTesting {
    
    public static void main(String[] args) {    
        UserList ggs;
        MajorList g2s;
        CourseList g3s;
        ggs = UserList.getInstance();
        g2s = MajorList.getInstance();
        g3s = CourseList.getInstance();
        ArrayList<Degree> degrees = g2s.getList();
        ArrayList<Student> students = ggs.getStudents();
        Student brax = students.get(0);
        // ArrayList<Course> needed = brax.getNeededCourses();
        // for(int j = 0; j < needed.size(); j++) {
        //     System.out.println(needed.get(j).getCourseName());
        // }
        // System.out.println();
        // System.out.println(degrees.get(0).printElectiveRequirements());
        // ArrayList<Course> cCourses = new ArrayList<>();
        // ArrayList<Double> grades = new ArrayList<>();
        // System.out.println(brax.calculateDegreeProgress());
        // for(Course course: brax.getCompletedCourses().keySet()) {
        //     cCourses.add(course);
        //     grades.add(brax.getCompletedCourses().get(course));
        // }
        //System.out.println(brax.printCompletedCourses());
        int num = 0;
        ArrayList<SemesterSchedule> ss = degrees.get(0).getPlanner().getSemesterSchedules();
        ArrayList<SemesterSchedule> braxSchedules = new ArrayList<>();
        LinkedHashMap<Course, Double> copy = brax.getCompletedCourses();
        for(int i = 0; i < ss.size(); i++) {
            SemesterSchedule braxSemester;
            ArrayList<Course> courses = new ArrayList<>();
            for(int j = 0; j < ss.get(i).getCourses().size(); j++)
            {
                for(Course course: copy.keySet()) {
                    if(ss.get(i).getCourses().get(j).getCourseName().equals(course.getCourseName())) {
                        courses.add(course);
                    }
                }          
            }
            braxSemester = new SemesterSchedule(courses, i+1);
            braxSchedules.add(braxSemester);  
        }

        for(int j = 0; j < braxSchedules.size(); j++) {
            System.out.println(braxSchedules.get(j).printSS());
        }
    }
}



import java.util.*;

public class Elective {
    // private int numCredits;
   // protected ArrayList<Course> electiveCourses;
    public int numCredits;
    private Category category;

    public Elective(Category category, int numCredits) {
        this.numCredits = numCredits;
        this.category = category;
        // ArrayList<Course> allCourses = DataLoader.getCourses();
        // electiveCourses = new ArrayList<Course>();

        // for (int i = 0; i < allCourses.size(); i++) {
        //     if (allCourses.get(i).getCategory().equals(Category.CSCE_ELECTIVE)
        //             || allCourses.get(i).getCategory().equals(Category.GFL)
        //             || allCourses.get(i).getCategory().equals(Category.GSS)
        //             || allCourses.get(i).getCategory().equals(Category.LAB_SCIENCE_ELECTIVE)
        //             || allCourses.get(i).getCategory().equals(Category.LIBERAL_ARTS)
        //             || allCourses.get(i).getCategory().equals(Category.AIU) ||
        //             allCourses.get(i).getCategory().equals(Category.GHS)
        //             || allCourses.get(i).getCategory().equals(Category.CMS)) {
        //         electiveCourses.add(allCourses.get(i));
        //     }
        // }
    }

    public int getNumCredits() {
        return numCredits;
    }

    public Category getCategory() {
        return category;
    }

    public void updateCredits(Course course) {
        if(course.getCategory().equals(this.getCategory())) {
            numCredits=-course.getCreditHours();
        }
    }

    // public String listGFLs() {
    //     String str = " Available GFL Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.GFL)) {
    //             str = str + electiveCourses.get(i).printCourseInfo() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listGSS() {
    //     String str = " Available GSS Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.GSS)) {
    //             str = str + electiveCourses.get(i).printCourseInfo() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listSciences() {
    //     String str = " Available Lab Science Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.LAB_SCIENCE_ELECTIVE)) {
    //             str = str + electiveCourses.get(i).printCourseInfo() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listLiberalArts() {
    //     String str = " Available Liberal Arts Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.LIBERAL_ARTS)) {
    //             str = str + electiveCourses.get(i).printCourseInfo() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listCSCEElectives() {
    //     String str = " Available CSCE Elective Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.CSCE_ELECTIVE)) {
    //             str = str + electiveCourses.get(i).toString() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listGHSElectives() {
    //     String str = " Available GHS Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.GHS)) {
    //             str = str + electiveCourses.get(i).toString() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listCMSElectives() {
    //     String str = " Available CMS Elective Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.CMS)) {
    //             str = str + electiveCourses.get(i).printCourseInfo() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public String listAIUElectives() {
    //     String str = " Available AIU Courses: \n";
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCategory().equals(Category.AIU)) {
    //             str = str + electiveCourses.get(i).printCourseInfo() + "\n -----------------------------" + "\n";
    //         }
    //     }
    //     return str;
    // }

    // public Course seachForCourse(String courseName) {
    //     for (int i = 0; i < electiveCourses.size(); i++) {
    //         if (electiveCourses.get(i).getCourseName().equals(courseName)) {
    //             return electiveCourses.get(i);
    //         }
    //     }
    //     return null;
    // }
    // public ArrayList<Course> getElectives(){
    //     return electiveCourses;
    // }


}

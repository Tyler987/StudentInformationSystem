import java.util.*;

public class ApplicationArea {
    private ArrayList<Course> aa;
    private AA name;

    public ApplicationArea(AA name) {
        this.name = name;
        ArrayList<Course> allCourses = DataLoader.getCourses();
        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).getCategory().equals(Category.APPLICATION_AREA)) {
                aa.add(allCourses.get(i));
            }
        }
    }

    public String getMaths() {
        String str = "";
        for (int i = 0; i < aa.size(); i++) {
            if (aa.get(i).getCourseName().contains("Math")) {
                str = str + aa.get(i).getCourseName() + "\n";
            }
        }
        return str;
    }

    public String getSciences() {
        String str = "";
        for (int i = 0; i < aa.size(); i++) {
            if (aa.get(i).getCourseName().contains("Science")) {
                str = str + aa.get(i).getCourseName() + "\n";
            }
        }
        return str;
    }

    public String getDigitalDs() {
        String str = "";
        for (int i = 0; i < aa.size(); i++) {
            if (aa.get(i).getCourseName().contains("Digital Design")) {
                str = str + aa.get(i).getCourseName() + "\n";
            }
        }
        return str;
    }

    public String getRobotics() {
        String str = "";
        for (int i = 0; i < aa.size(); i++) {
            if (aa.get(i).getCourseName().contains("Robotics")) {
                str = str + aa.get(i).getCourseName() + "\n";
            }
        }
        return str;
    }

    public String getSpeechs() {
        String str = "";
        for (int i = 0; i < aa.size(); i++) {
            if (aa.get(i).getCourseName().contains("Speech")) {
                str = str + aa.get(i).getCourseName() + "\n";
            }
        }
        return str;
    }

    public String getStats() {
        String str = "";
        for (int i = 0; i < aa.size(); i++) {
            if (aa.get(i).getCourseName().contains("Stats")) {
                str = str + aa.get(i).getCourseName() + "\n";
            }
        }
        return str;
    }

    public void changeAA(AA newAA) {
        this.name = newAA;

    }

    public AA getName() {
        return name;
    }
}

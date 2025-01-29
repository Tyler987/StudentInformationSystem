import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;

public class Course {
    private UUID id;
    private String courseName;
    private int creditHours;
    private Category category;
    private ArrayList<String> prerequisites;

    public Course(UUID id, String courseName, Category category, int creditHours, ArrayList<String> prereqs) {
        this.id = id;
        this.category = category;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.prerequisites = prereqs;
    }

    public Course(String string, String string2, int i) {
        //TODO Auto-generated constructor stub
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public UUID getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String printCourseInfo() {
        String str = "";
        str = str + "Course Name: " + courseName + "\n" + "Category: " + category + "\n" + "Credit Hours: "
                + creditHours + "\nPrerequisites: \n";
        for (int i = 0; i < prerequisites.size(); i++) {
            str = str + prerequisites.get(i) + "\n";
        }
        return str;
    }
}

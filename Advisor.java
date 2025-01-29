import java.util.*;

public class Advisor extends User {
    private ArrayList<Student> students;
    private String id;
    private HashMap<UUID,ArrayList<String>> studentNotes;

    public Advisor(UUID id, String firstName, String lastName, String email, String password,
            ArrayList<Student> students) {
        super(id, firstName, lastName, email, password);
        this.students = students;
        studentNotes = new HashMap<>();
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    
    public void addNotes(UUID person, String note){
        if(studentNotes.keySet().contains(person)){
            studentNotes.get(person).add(note);
        }else{
            studentNotes.put(person, new ArrayList<String>());
            studentNotes.get(person).add(note);
        }
    }
    public String getNotes(UUID person){
        if(studentNotes.keySet().contains(person)){
            String ret = "";
            for(String s:studentNotes.get(person)){
                ret+=s+"\n";
            }
            return ret;
        }else{
            return null;
        }
    }
    // public void addStudent(String id, String firstName, String lastName, String email, String password, Degree major,
    //         String uscId, double gpa, ArrayList<Course> courses) {
    //     Student newStudent = new Student(UUID.fromString(id), firstName, lastName, email, password, major, uscId, gpa,
    //             courses);
    //     students.add(newStudent);
    //}

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getID() {
        return id;
    }

    public String printStudentList() {
        String str = "";
        for(int i = 0; i < students.size(); i++) {
            str = str + students.get(i).getFirstName() + " " + students.get(i).getLastName() + "\n";
        }
        return str;
    }
}

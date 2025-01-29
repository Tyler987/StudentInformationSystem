import java.util.*;

public class SchedulePlanner {

    protected ArrayList<SemesterSchedule> sS;
    private ArrayList<String> defaults;

    public SchedulePlanner(ArrayList<SemesterSchedule> sS) {
        this.sS = sS;
    }

    

    public String printDefaultPlanner() {
        String str = "";
        for (int i = 0; i < sS.size(); i++) {
            str = str + sS.get(i).printSS();
        }
        return str;
    }

    public ArrayList<SemesterSchedule> getSemesterSchedules() {
        return sS;
    }

    public String printPlansTest() {
        String str = "";
        for(int i = 0; i < defaults.size(); i++) {
            str = str + defaults.get(i);
        }
        return str;
    }



    //public String printScheduleWithCC()
    
}

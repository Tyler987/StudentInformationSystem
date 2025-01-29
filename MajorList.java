import java.util.*;

public class MajorList {
  private static MajorList instance;
  private ArrayList<Degree> list;

  private MajorList() {
    list = new ArrayList<>();
    list = DataLoader.getDegrees();
  }

  public static MajorList getInstance() {
    if (instance == null) {
      instance = new MajorList();
    }
    return instance;
  }

  public ArrayList<Degree> getList() {
    return list;
  }

  public Degree getDegreeById(UUID id) {
    // for (Degree degree : list) {
    // if (degree.getID().equals(id)) {
    // return degree;
    // }
    // }
    // return null;
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getID().equals(id)) {
        return list.get(i);
      }
    }
    return null;
  }
}
// for(int i = 0; i < list.size(); i++) {
// if(list.get(i).equals(id)) {
// return list.get(i);
// }
// }
// return null;

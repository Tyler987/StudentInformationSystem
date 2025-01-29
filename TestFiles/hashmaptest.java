import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.*;
import com.google.gson.reflect.*;;

public class hashmaptest {
    public static void main(String[] args) {
        HashMap<User, Double> mymap = new HashMap<>();
        User dummy = new User(UUID.randomUUID(), "Gage", "Hulbert", "email", "Cool");
        mymap.put(dummy, 5.5);
        System.out.println(mymap.get(dummy));
    //     Gson converter = new Gson();
    //     String thejson = converter.toJson(mymap);
    //     HashMap<User, Double> convback = new HashMap<>();
    //     convback = (HashMap<User, Double>) converter.fromJson(thejson, convback.getClass());
    //     System.out.println(converter.toJson(mymap));
    //     System.out.println(convback.get(dummy));
    // }
        JSONArray convertedmap = new JSONArray();
        for(Map.Entry<User,Double> entry: mymap.entrySet()){
            // JSONObject completed = new JSONObject();
            // completed.put("UID", entry.getKey().getFirstName());
            // completed.put("Value", entry.getValue());
            // convertedmap.add(completed);
            convertedmap.add(entry.getKey().getFirstName());
            convertedmap.add(entry.getValue());
        }
        System.out.println(convertedmap);
    }
}

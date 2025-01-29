import java.util.*;

public class User {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(UUID id, String fN, String lN, String email, String password) {
        this.id = id;
        this.lastName = lN;
        this.firstName = fN;
        this.email = email;
        this.password = password;
    }

    public void setFirstName(String fN) {
        this.firstName = fN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lN) {
        this.lastName = lN;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public UUID getUuid() {
        return this.id;
    }

    public String toString() {
        String str = "";
        str = str + firstName + " " + lastName + "\n" + "Email: " + email;
        return str;
    }

}

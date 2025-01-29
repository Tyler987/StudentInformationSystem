import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class AdvisorTest {
    Advisor person;
    @BeforeEach
    public void makeAdvisor(){
        person = new Advisor(UUID.randomUUID(), "Doc", "Gage", "Email@email", "GDAWG", null);
    }
    @AfterEach
    public void fireAdvisor(){
        person = null;
    }
    @Test
    public void createNote(){
        UUID randomkid = UUID.randomUUID();
        person.addNotes(randomkid, "Horrible student, they smell");
        assertEquals("Horrible student, they smell\n", person.getNotes(randomkid));
    }
    @Test
    public void createEmptyNote(){
        UUID randomkid = UUID.randomUUID();
        person.addNotes(randomkid, "");
        assertEquals("\n", person.getNotes(randomkid));
    }
    @Test
    public void multiNote(){
        UUID randomkid = UUID.randomUUID();
        UUID randomkid2 = UUID.randomUUID();
        person.addNotes(randomkid, "Horrible");
        person.addNotes(randomkid2, "Wonderfull");
        assertEquals("Wonderfull\n", person.getNotes(randomkid2));
    }

}
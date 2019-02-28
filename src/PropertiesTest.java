import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.*;

import java.util.ResourceBundle;

public class PropertiesTest {
    public static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    ResourceBundle myResources;
    @BeforeEach
    void initialize() {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Test");
    }


    @Test
    void getCorrectGameName() {
        String expected = "Fake Simulation";
        String actual = myResources.getString("TypeOfSimulation");
        assertEquals(expected, actual);
    }

    @Test
    void getCorrectFile() {
        String expected = "Fake Simulation";
        String actual = myResources.getString("TypeOfSimulation");
        assertEquals(expected, actual);
    }
}

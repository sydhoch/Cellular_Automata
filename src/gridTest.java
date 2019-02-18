import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void main() {
       String testFile = "test-1.csv";
       String expected = "[[1, 0, 1], [0, 1, 1], [0, 0, 0]]";
       String actual = Grid.readFile(testFile);
       assertEquals(expected, actual);
    }

   /* @Test
    void checkDimensions() {
        int width
    }*/
}
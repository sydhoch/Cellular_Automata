import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void main() {
       Grid myGrid = new Grid();
       String testFile = "test-1.csv";
       int[][] expected = {{1, 0, 1}, {0, 1, 1}, {0, 0, 0}};
       int[][] actual = myGrid.getGrid(testFile);
       assertEquals(expected, actual);
    }

   /* @Test
    void checkDimensions() {
        int width
    }*/
}
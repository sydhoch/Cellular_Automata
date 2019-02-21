import cell.Cell;
import cell.GoLCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

//    @Test
//    void main() {
//       Grid myGrid = new Grid();
//       String testFile = "test-1.csv";
//       int[][] expected = {{1, 0, 1}, {0, 1, 1}, {0, 0, 0}};
//       int[][] actual = myGrid.getGrid(testFile);
//       assertArrayEquals(expected, actual);
//    }

   /* @Test
    void checkDimensions() {
        int width
    }*/

   @Test
    void checkNeighborsMiddle(){
       int row = 1;
       int col = 1;
       Grid myGrid = new Grid("test-1.csv");
       Cell[] expected = {new GoLCell(1),
               new GoLCell(0),
               new GoLCell(1),
               new GoLCell(0),
               new GoLCell(1),
               new GoLCell(0),
               new GoLCell(0),
               new GoLCell(0)};
       Cell[] actual = myGrid.setNeighbors(row, col);
       for(int i = 0; i < 8; i++){
           assertEquals(expected[i], actual[i]);
       }
   }

    @Test
    void checkNeighborsEdge(){
        int row = 0;
        int col = 1;
        Grid myGrid = new Grid("test-1.csv");
        Cell[] expected = {new GoLCell(0),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(1),
                new GoLCell(1),
                new GoLCell(0),
                new GoLCell(1),
                new GoLCell(1)};
        Cell[] actual = myGrid.setNeighbors(row, col);
        for(int i = 0; i < 8; i++){
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void checkNeighborsCorner(){
        int row = 2;
        int col = 2;
        Grid myGrid = new Grid("test-1.csv");
        Cell[] expected = {new GoLCell(1),
                new GoLCell(1),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(1),
                new GoLCell(1)};
        Cell[] actual = myGrid.setNeighbors(row, col);
        for(int i = 0; i < 8; i++){
            assertEquals(expected[i], actual[i]);
        }
    }
}
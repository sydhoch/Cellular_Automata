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

   @Test
    void checkNeighborsMiddle(){
       Grid myGrid = new Grid("test-1.csv");
       Cell[] expected = {new GoLCell(1),
               new GoLCell(0),
               new GoLCell(1),
               new GoLCell(0),
               new GoLCell(1),
               new GoLCell(0),
               new GoLCell(0),
               new GoLCell(0)};
       compareCells(myGrid, expected, 1, 1);
   }

    @Test
    void checkNeighborsEdge(){
        Grid myGrid = new Grid("test-1.csv");
        Cell[] expected = {new GoLCell(0),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(1),
                new GoLCell(1),
                new GoLCell(0),
                new GoLCell(1),
                new GoLCell(1)};
        compareCells(myGrid, expected, 0, 1);
    }

    @Test
    void checkNeighborsCorner(){
        Grid myGrid = new Grid("test-1.csv");
        Cell[] expected = {new GoLCell(1),
                new GoLCell(1),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(0),
                new GoLCell(1),
                new GoLCell(1)};
        compareCells(myGrid, expected, 2, 2);
    }

    void compareCells(Grid myGrid, Cell[] expected, int row, int col){
        Cell[] actual = myGrid.setNeighbors(row, col);
        for(int i = 0; i < 8; i++){
            assertEquals(expected[i].getState(), actual[i].getState());
        }
    }
}
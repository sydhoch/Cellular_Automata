import cell.Cell;
import cell.GoLCell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void testReadFile() {
       Grid myGrid = new Grid("gol-grid-3.csv");
       Cell[][] expected = {{new GoLCell(1), new GoLCell(0), new GoLCell(1)}, {new GoLCell(0), new GoLCell(1), new GoLCell(1)}, {new GoLCell(0), new GoLCell(0), new GoLCell(0)}};
       Cell[][] actual = myGrid.getGrid();
       for(int i = 0; i < 3; i++){
           for(int j =0; j < 3; j++){
               assertEquals(expected[i][j].getState(), actual[i][j].getState());
           }
       }
    }

   @Test
    void testNeighborsMiddle(){
       Grid myGrid = new Grid("gol-grid-3.csv");
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
    void testNeighborsEdge(){
        Grid myGrid = new Grid("gol-grid-3.csv");
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
    void testNeighborsCorner(){
        Grid myGrid = new Grid("gol-grid-3.csv");
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
        Cell[] actual = myGrid.setNeighborsToroidal(row, col);
        for(int i = 0; i < 8; i++){
            assertEquals(expected[i].getState(), actual[i].getState());
        }
    }
}
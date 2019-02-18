package cell;

import cell.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private Cell c1;
    private Cell c2;
    private Cell c3;
    private Cell c4;
    private Cell c5;
    private Cell c6;
    private Cell c7;
    private Cell c8;
    private Cell c9;
    private Cell[] neighbors = new Cell[8];

    @BeforeEach
    void setup(){
         c1 = new PercCell(0,1,1);
         c2 = new PercCell(1,1,1);
         c3 = new PercCell(1,1,1);
         c4 = new PercCell(2,1,1);
         c5 = new PercCell(2,1,1);
         c6 = new PercCell(0,1,1);
         c7 = new PercCell(0,1,1);
         c8 = new PercCell(0,1,1);
         c9 = new PercCell(0,1,1);
         neighbors[0] = c2;
         neighbors[1] = c3;
         neighbors[2] = c4;
         neighbors[3] = c5;
         neighbors[4] = c6;
         neighbors[5] = c7;
         neighbors[6] = c8;
         neighbors[7] = c9;

         //c2 = new cell.GoLCell(0,1,1);
    }

    @Test
    public void percCheckNeighborStatus(){
        c1.checkNeighborStatus(neighbors);
        int expected = 1;
        int actual = c1.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void updatePercCell() {
    }

    @Test
    public void updateGoLCell() {
    }

    @Test
    public void getRectangle() {
    }

    @Test
    public void setNextState() {
    }

    @Test
    public void getState() {
    }

    @Test
    public void equals() {
    }
}

package cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    private Cell p0;
    private Cell p1;
    private Cell p2;

    private Cell g0;
    private Cell g1;

    private Cell r0;
    private Cell r1;
    private Cell r2;

    @BeforeEach
    void setup(){
         p0 = new PercCell(0);
         p1 = new PercCell(1);
         p2 = new PercCell(2);

         g0 = new GoLCell(0);
         g1 = new GoLCell(1);

         r0 = new RPSCell(0);
         r1 = new RPSCell(1);
         r2 = new RPSCell(2);
    }

    @Test
    public void setRockToPaper(){
        Cell[] neighbors = {r0,r1,r1,r1,r1,r2};
        r0.checkNeighborStatus(neighbors);
        int expected = 1;
        int actual = r0.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void rockStays(){
        Cell[] neighbors = {r0,r1,r1,r1,r2};
        r0.checkNeighborStatus(neighbors);
        int expected = 0;
        int actual = r0.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void scissorsToRock(){
        Cell[] neighbors = {r0,r0,r0,r0,r1,r1,r1,r1};
        r2.checkNeighborStatus(neighbors);
        int expected = 0;
        int actual = r2.getNextState();
        assertEquals(expected,actual);
    }


    @Test
    public void openPercCellFilled(){
        Cell[] neighbors = {p0,p0,p0,p0,p1,p2,p2,p2};
        p0.checkNeighborStatus(neighbors);
        int expected = 1;
        int actual = p0.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void closedPercCellStayClosed(){
        Cell[] neighbors = {p0,p0,p0,p0,p1,p2,p2,p2};
        p2.checkNeighborStatus(neighbors);
        int expected = 2;
        int actual = p2.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void fullPercCellStayFull(){
        Cell[] neighbors = {p0,p0,p0,p0,p1,p2,p2,p2};
        p1.checkNeighborStatus(neighbors);
        int expected = 1;
        int actual = p1.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void emptyPercCellStayEmpty(){
        Cell[] neighbors = {p0,p0,p0,p0,p2,p2,p2,p2};
        p0.checkNeighborStatus(neighbors);
        int expected = 0;
        int actual = p0.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void aliveGoLCellKilledByUnderPopulation(){
        Cell[] neighbors = {g0,g0,g0,g0,g0,g0,g0,g1};
        g1.checkNeighborStatus(neighbors);
        int expected = 0;
        int actual = g1.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void aliveGoLCellKilledByOverPopulation(){
        Cell[] neighbors = {g0,g0,g0,g0,g1,g1,g1,g1};
        g1.checkNeighborStatus(neighbors);
        int expected = 0;
        int actual = g1.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void deadGoLCellBroughtBack(){
        Cell[] neighbors = {g0,g0,g0,g0,g0,g1,g1,g1};
        g0.checkNeighborStatus(neighbors);
        int expected = 1;
        int actual = g0.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void deadGoLCellStayDead(){
        Cell[] neighbors = {g0,g0,g0,g0,g1,g1,g1,g1};
        g0.checkNeighborStatus(neighbors);
        int expected = 0;
        int actual = g0.getNextState();
        assertEquals(expected,actual);
    }

    @Test
    public void percChangeStateToFull() {
        Cell[] neighbors = {p0,p0,p0,p0,p1,p2,p2,p2};
        p0.checkNeighborStatus(neighbors);
        p0.updateCell();
        int expected = 1;
        int actual = p0.getState();
        assertEquals(expected,actual);
    }

    @Test
    public void percUpdateCell_Works_With_No_NeighborCheck() {
        p0.updateCell();
        int expected = 0;
        int actual = p0.getState();
        assertEquals(expected,actual);
    }

    @Test
    public void percStateStaysSameAfterUpdate() {
        Cell[] neighbors = {p0,p0,p0,p0,p1,p2,p2,p2};
        p0.checkNeighborStatus(neighbors);
        p0.updateCell();
        int expected = 1;
        int actual = p0.getState();
        assertEquals(expected,actual);
    }

    @Test
    public void golUpdateCell_Works_With_No_NeighborCheck() {
        g1.updateCell();
        int expected = 1;
        int actual = g1.getState();
        assertEquals(expected,actual);
    }

    @Test
    public void golUpdateStateFromDeadToAlive() {
        Cell[] neighbors = {g0,g0,g0,g0,g0,g1,g1,g1};
        g0.checkNeighborStatus(neighbors);
        g0.updateCell();
        int expected = 1;
        int actual = g1.getState();
        assertEquals(expected,actual);
    }
}

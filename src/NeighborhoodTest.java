import cell.Cell;
import cell.GoLCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodTest {
    private GoLCell g0;
    private GoLCell g1;
    private GoLCell g2;

    @BeforeEach
    void setup(){
        g0 = new GoLCell(0);
        g1 = new GoLCell(1);
        g2 = new GoLCell(2);

    }

    @Test
    void getNeighbors() {
        Grid myGrid = new Grid("gol-grid-3.csv");

    }
}
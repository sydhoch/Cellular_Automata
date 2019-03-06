package grid;

import cell.Cell;
import cell.GoLCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NeighborhoodTest {
    private Cell golState0;
    private Cell golState1;

    @BeforeEach
    void setCells() {
        golState0 = new GoLCell(0);
        golState1 = new GoLCell(1);
    }

    @Test
    void testNeighborsMiddle_RectTorridalComplete() {
        Grid myGrid = new Grid("gol-grid-3.csv");
        Cell[] expected = {golState1, golState0, golState1, golState0, golState1, golState0, golState0, golState0};
        compareCells(myGrid, expected, 1, 1);
    }

    @Test
    void testNeighborsEdge_RectTorridalComplete() {
        Grid myGrid = new Grid("gol-grid-3.csv");
        Cell[] expected = {golState0, golState0, golState0, golState1, golState1, golState0, golState1, golState1};
        compareCells(myGrid, expected, 0, 1);
    }

    @Test
    void testNeighborsCorner_RectTorridalCompleter() {
        Grid myGrid = new Grid("gol-grid-3.csv");
        Cell[] expected = {golState1, golState1, golState0, golState0, golState0, golState0, golState1, golState1};
        compareCells(myGrid, expected, 2, 2);
    }
    void compareCells(Grid myGrid, Cell[] expected, int row, int col) {
        Cell[] actual = myGrid.setNeighborsToroidal(row, col); //change method
        for (int i = 0; i < 8; i++) {
            assertEquals(expected[i].getState(), actual[i].getState());
        }
    }

}

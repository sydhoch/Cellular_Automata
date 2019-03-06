package grid;

import cell.Cell;
import cell.GoLCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridTest {

    @Test
    void testReadFile() {
        Grid myGrid = new Grid("gol-grid-3.csv");
        Cell[][] expected = {{new GoLCell(1), new GoLCell(0), new GoLCell(1)}, {new GoLCell(0), new GoLCell(1), new GoLCell(1)}, {new GoLCell(0), new GoLCell(0), new GoLCell(0)}};
        Cell[][] actual = myGrid.getGrid();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(expected[i][j].getState(), actual[i][j].getState());
            }
        }
    }

    @Test
    void checkStateMapCorrectAtBeginning() {
        List<Integer[]> expected0 = new ArrayList<>(Arrays.asList(new Integer[]{0, 0}, new Integer[]{0, 2}, new Integer[]{0, 4}, new Integer[]{1, 1}, new Integer[]{1, 3}, new Integer[]{1, 4}, new Integer[]{2, 1}, new Integer[]{2, 2}, new Integer[]{3, 0}, new Integer[]{3, 1}, new Integer[]{3, 3}, new Integer[]{3, 4}, new Integer[]{4, 2}, new Integer[]{4, 3}));
        List<Integer[]> expected1 = new ArrayList<>(Arrays.asList(new Integer[]{0, 1}, new Integer[]{0, 3}));
        List<Integer[]> expected2 = new ArrayList<>(Arrays.asList(new Integer[]{1, 0}, new Integer[]{1, 2}, new Integer[]{2, 0}, new Integer[]{2, 3}, new Integer[]{2, 4}, new Integer[]{3, 2}, new Integer[]{4, 0}, new Integer[]{4, 1}, new Integer[]{4, 4}));
        Grid myGrid = new Grid("perc-grid-1.csv");

        compareLists(expected0, myGrid);
        compareLists(expected1, myGrid);
        compareLists(expected2, myGrid);
    }

    @Test
    void checkStateMapCorrectAtEnd() {
        List<Integer[]> expected0 = new ArrayList<>();
        List<Integer[]> expected1 = new ArrayList<>(Arrays.asList(new Integer[]{0, 0}, new Integer[]{0, 1}, new Integer[]{0, 2}, new Integer[]{0, 3}, new Integer[]{0, 4}, new Integer[]{1, 1}, new Integer[]{1, 3}, new Integer[]{1, 4}, new Integer[]{2, 1}, new Integer[]{2, 2}, new Integer[]{3, 0}, new Integer[]{3, 1}, new Integer[]{3, 3}, new Integer[]{3, 4}, new Integer[]{4, 2}, new Integer[]{4, 3}));
        List<Integer[]> expected2 = new ArrayList<>(Arrays.asList(new Integer[]{1, 0}, new Integer[]{1, 2}, new Integer[]{2, 0}, new Integer[]{2, 3}, new Integer[]{2, 4}, new Integer[]{3, 2}, new Integer[]{4, 0}, new Integer[]{4, 1}, new Integer[]{4, 4}));
        Grid myGrid = new Grid("perc-grid-1.csv");

        for (int i = 0; i < 3; i++) {
            myGrid.setNextStates();
            myGrid.updateStates();
        }

        compareLists(expected0, myGrid);
        compareLists(expected1, myGrid);
        compareLists(expected2, myGrid);
    }


    void compareLists(List<Integer[]> expected, Grid myGrid) {
        for(int a = 0; a < 3; a++) {
            List<Integer[]> actual = myGrid.getCellsInState(a);
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i)[0], actual.get(i)[0]);
                assertEquals(expected.get(i)[1], actual.get(i)[1]);
            }
        }
    }
}
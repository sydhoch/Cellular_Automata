import cell.Cell;

import java.util.*;


public class Grid {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";

    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private String myType;
    private Shape myShape;
    private Arrangement myArr;
    private Edge myEdge;
    private ResourceBundle myResources;
    private Map<Integer, List<Integer[]>> myCellStates;

    public Grid(String file) {
        GridMaker maker = new GridMaker(file);
        myHeight = maker.getHeight();
        myWidth = maker.getWidth();
        myGrid = maker.getGrid();
        myType = maker.getGameType();
        myCellStates = new HashMap<>();
    }

    public String getType(){
        return myType;
    }

    protected void setNextStates() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                Cell[] neighbors = setNeighborsToroidal(i, j);
                getCell(i, j).checkNeighborStatus(neighbors);
            }
        }
    }

    protected void updateStates() {
        myCellStates.clear();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                getCell(i, j).updateCell();
                addToMap(i, j, getCell(i, j));
            }
        }
    }

    private void addToMap(int row, int col, Cell cell){
        myCellStates.putIfAbsent(cell.getState(), new ArrayList<>());
        myCellStates.get(cell.getState()).add(new Integer[]{row, col});
    }


    protected Cell getCell(int row, int col) {
        return myGrid[row][col];
    }

    public Cell[] setNeighborsToroidal(int row, int col) { //break up
        Cell[] neighbors = new Cell[8];
        if (row == 0 && col == 0) {
            neighbors[0] = getCell(myHeight - 1, myWidth - 1);
        } else if (row == 0) {
            neighbors[0] = getCell(myHeight - 1, col - 1);
        } else if (col == 0) {
            neighbors[0] = getCell(row - 1, myWidth - 1);
        } else {
            neighbors[0] = getCell(row - 1, col - 1);
        }

        if (row == 0) {
            neighbors[1] = getCell(myHeight - 1, col);
        } else {
            neighbors[1] = getCell(row - 1, col);
        }

        if (row == 0 && col == myWidth - 1) {
            neighbors[2] = getCell(myHeight - 1, 0);
        } else if (row == 0) {
            neighbors[2] = getCell(myHeight - 1, col + 1);
        } else if (col == myWidth - 1) {
            neighbors[2] = getCell(row - 1, 0);
        } else {
            neighbors[2] = getCell(row - 1, col + 1);
        }

        if (col == 0) {
            neighbors[3] = getCell(row, myWidth - 1);
        } else {
            neighbors[3] = getCell(row, col - 1);
        }

        if (col == myWidth - 1) {
            neighbors[4] = getCell(row, 0);
        } else {
            neighbors[4] = getCell(row, col + 1);
        }

        if (row == myHeight - 1 && col == 0) {
            neighbors[5] = getCell(0, myWidth - 1);
        } else if (row == myHeight - 1) {
            neighbors[5] = getCell(0, col - 1);
        } else if (col == 0) {
            neighbors[5] = getCell(row + 1, myWidth - 1);
        } else {
            neighbors[5] = getCell(row + 1, col - 1);
        }

        if (row == myHeight - 1) {
            neighbors[6] = getCell(0, col);
        } else {
            neighbors[6] = getCell(row + 1, col);
        }


        if (row == myHeight - 1 && col == myWidth - 1) {
            neighbors[7] = getCell(0, 0);
        } else if (row == myHeight - 1) {
            neighbors[7] = getCell(0, col + 1);
        } else if (col == myWidth - 1) {
            neighbors[7] = getCell(row + 1, 0);
        } else {
            neighbors[7] = getCell(row + 1, col + 1);
        }
        return neighbors;
    }

    public Cell[] setNeighbors(int row, int col) {
        Neighborhood neighbors = new Neighborhood(row, col, myShape, myArr, myEdge);
        return neighbors.getNeighbors();
    }

    protected int getWidth() { return myWidth; }

    protected int getHeight() { return myHeight; }

    public Cell[][] getGrid() {
        return myGrid;
    }

    public List<Integer[]> getCellsInState(int state){
        return myCellStates.get(state);
    }

}
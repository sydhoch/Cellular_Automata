package grid;

import Enums.*;
import frontend.Play;
import cell.*;

import java.util.*;


public class Grid {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";

    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private SimType myGameType;
    private int simNum;
    private Shape myShape;
    private Arrangement myArr;
    private Edge myEdge;
    private ResourceBundle myResources;
    private Map<Integer, List<Integer[]>> myCellStates;

    public Grid(String file, String neighborPolicy,String cellShape, String edgePolicy ) {
        myCellStates = new HashMap<>();
        myGrid = makeGrid(readFile(file));
        simNum = Integer.valueOf(file.substring(file.length()-5, file.length()-4));
    }

    private String[] readFile(String gameFile){
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        String csv = "";
        while (s.hasNext()) {
            csv = csv + s.next();
        }
        String[] csvSplit = csv.split(",");
        String[]seperatedVals = new String[csvSplit.length];
        int k = 0;
        for(int i = 0; i < csvSplit.length; i++){
            if(!csvSplit[i].equals("")){
                seperatedVals[k] = csvSplit[i];
                k++;
            }
        }
        return seperatedVals;
    }

    private Cell[][] makeGrid(String[] seperatedVals) {
        myGameType = SimType.valueOf(seperatedVals[0].toUpperCase());
        myWidth = Integer.valueOf(seperatedVals[1]);
        myHeight = Integer.valueOf(seperatedVals[2]);
        Cell[][] grid = new Cell[myHeight][myWidth];
        int cell = 3;
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                int state = Integer.valueOf(seperatedVals[cell]);
                if (myGameType.equals(SimType.PERC)) {
                    grid[i][j] = new PercCell(state);
                } else if (myGameType.equals(SimType.GOL)) {
                    grid[i][j] = new GoLCell(state);
                } else if (myGameType.equals(SimType.RPS)) {
                    grid[i][j] = new RPSCell(state);
                } else if (myGameType.equals(SimType.SEG)) {
                    grid[i][j] = new SegCell(state);
                } else if (myGameType.equals(SimType.FIRE)) {
                    grid[i][j] = new FireCell(state);
                } else if (myGameType.equals(SimType.PP)) {
                    grid[i][j] = new PPCell(state);
                }
                addToMap(i, j, grid[i][j]);
                cell++;
            }
        }
        return grid;
    }

    public SimType getType(){
        return myGameType;
    }

    public void setNextStates() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                Cell[] neighbors = setNeighborsToroidal(i, j);
                getCell(i, j).checkNeighborStatus(neighbors);
            }
        }
    }

    public void updateStates() {
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


    public Cell getCell(int row, int col) {
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

    public int getWidth() { return myWidth; }

    public int getHeight() { return myHeight; }

    public Cell[][] getGrid() {
        return myGrid;
    }

    public List<Integer[]> getCellsInState(int state){
            return myCellStates.get(state);
    }

    public int getSimNum(){
        return simNum;
    }

}
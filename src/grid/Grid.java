/**@author Arilia Frederick**/


package grid;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import Enums.SimType;
import Exceptions.InvalidValueException;
import cell.*;
import frontend.Play;

import java.util.*;
import java.util.stream.Collectors;


public class Grid {
    private static final int RANDOM_GRID_SIZE = 5;

    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private SimType myGameType;
    private int simNum;
    private Shape myShape;
    private Arrangement myArr;
    private Edge myEdge;
    private Map<Integer, List<Cell>> myCellStates;
    Map<Integer, List<Cell>> mapCopy;

    public Grid(String file, Arrangement neighborPolicy, Shape cellShape, Edge edgePolicy, SimType s)  {
        myCellStates = new HashMap<>();
        mapCopy = new HashMap<>();
        try {
            myGrid = makeGrid(readFile(file));
        } catch (InvalidValueException e) {
            e.printStackTrace();
            myGrid = makeRandomGrid(s);
        }
        simNum = Integer.valueOf(file.substring(file.length() - 5, file.length() - 4));
        myShape = cellShape;
        myArr = neighborPolicy;
        myEdge = edgePolicy;
    }

    private String[] readFile(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        String csv = "";
        while (s.hasNext()) {
            csv = csv + s.next();
        }
        String[] csvSplit = csv.split(",");
        String[] separatedValues = new String[csvSplit.length];
        int k = 0;
        for (int i = 0; i < csvSplit.length; i++) {
            if (!csvSplit[i].equals("")) {
                separatedValues[k] = csvSplit[i];
                k++;
            }
        }
        return separatedValues;
    }

    private Cell[][] makeGrid(String[] seperatedVals) throws InvalidValueException {
        myGameType = SimType.valueOf(seperatedVals[0].toUpperCase());
        myWidth = Integer.valueOf(seperatedVals[1]);
        myHeight = Integer.valueOf(seperatedVals[2]);
        if ((myHeight | myWidth) <= 3) {
            throw new InvalidValueException("Both Width and Height need to have dimensions greater than 3");
        }
        Cell[][] grid = new Cell[myHeight][myWidth];
        int cell = 3;
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                grid[i][j] = makeCell(myGameType, Integer.valueOf(seperatedVals[cell]));
                addToMap(grid[i][j]);
                cell++;
            }
        }
        return grid;
    }

    private Cell[][] makeRandomGrid(SimType s){
        myGameType = s;
        Cell[][] grid = new Cell[RANDOM_GRID_SIZE][RANDOM_GRID_SIZE];
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                int state = new Random(3).nextInt();
                if(s.equals(SimType.GOL)){
                    state = new Random(3).nextInt();
                }
                grid[i][j] = makeCell(s, state);
                addToMap(grid[i][j]);
            }
        }
        return grid;
    }

    private Cell makeCell(SimType s, int state){
        if (s.equals(SimType.PERC)) {
            return new PercCell(state);
        } else if (s.equals(SimType.GOL)) {
            return new GoLCell(state);
        } else if (s.equals(SimType.RPS)) {
            return new RPSCell(state);
        } else if (s.equals(SimType.SEG)) {
            return new SegCell(state);
        } else if (s.equals(SimType.FIRE)) {
            return new FireCell(state);
        } else {
            return new PPCell(state);
        }
    }

    public SimType getType() {
        return myGameType;
    }

    public void setNextStates() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                Cell[] neighbors = setNeighbors(i, j);
                getCell(i, j).checkNeighborStatus(neighbors, mapCopy);
            }
        }
    }

    public void updateStates() {
        myCellStates.clear();
        mapCopy.clear();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                getCell(i, j).updateCell();
                addToMap(getCell(i, j));
            }
        }
    }

    private void addToMap(Cell cell) {
        myCellStates.putIfAbsent(cell.getState(), new ArrayList<>());
        myCellStates.get(cell.getState()).add(cell);
        mapCopy.putIfAbsent(cell.getState(), new ArrayList<>());
        mapCopy.get(cell.getState()).add(cell);
    }

    public Cell getCell(int row, int col) {
        return myGrid[row][col];
    }


    public Cell[] setNeighbors(int row, int col) {
        Neighborhood neighbors = new Neighborhood(row, col, myShape, myArr, myEdge, this);
        return neighbors.getNeighbors();
    }

    public int getWidth() {
        return myWidth;
    }

    public int getHeight() {
        return myHeight;
    }

    public List<Cell> getCellsInState(int state) {
        return myCellStates.get(state);
    }

    public int getSimNum() {
        return simNum;
    }

    public Shape getShape() {
        return myShape;
    }


    public void setVal(double d){
        for(int i = 0; i < getHeight(); i++){
            for(int j = 0; j < getWidth(); j++){
                getCell(i, j).setSpecialValue((int)d);
            }
        }
    }
}
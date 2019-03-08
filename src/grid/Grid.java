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
    private Map<Integer, List<Cell>> myCellStates;
    Map<Integer, List<Cell>> mapCopy;

    public Grid(String file, Arrangement neighborPolicy, Shape cellShape, Edge edgePolicy)  {
        myCellStates = new HashMap<>();
        mapCopy = new HashMap<>();
        try {
            myGrid = makeGrid(readFile(file));
        } catch (InvalidValueException e) {
            e.printStackTrace();
            file = "gol-grid-1.csv";
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
                addToMap(grid[i][j]);
                cell++;
            }
        }
        return grid;
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

    public Cell[][] getGrid() {
        return myGrid;
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

    public Arrangement getArr() {
        return myArr;
    }

    public Edge getEdge() {
        return myEdge;
    }
}
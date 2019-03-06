import cell.*;

import java.util.*;
import java.util.stream.Collectors;


public class Grid {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";

    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private SimType myGameType;
    private Shape myShape;
    private Arrangement myArr;
    private Edge myEdge;
    private ResourceBundle myResources;
    private Map<Integer, List<Cell>> myCellStates;

    public Grid(String file) {
        myCellStates = new HashMap<Integer, List<Cell>>();
        myGrid = makeGrid(file);
    }

    private Cell[][] makeGrid(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        String csv = "";
        while (s.hasNext()) {
            csv = csv + s.next();
        }
        String[] seperatedVals = csv.split(",");
        myGameType = SimType.valueOf(seperatedVals[0].toUpperCase());
        myWidth = Integer.valueOf(seperatedVals[4]);
        myHeight = Integer.valueOf(seperatedVals[5]);
        Cell[][] grid = new Cell[myHeight][myWidth];
        int cell = 8;
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

    protected void setNextStates() {
        Map<Integer,List<Cell>> mapCopy = myCellStates.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> List.copyOf(e.getValue())));
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                Cell[] neighbors = setNeighbors(i, j);
                getCell(i, j).checkNeighborStatus(neighbors, mapCopy);
            }
        }
    }


    protected void updateStates () {
        myCellStates.clear();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                getCell(i, j).updateCell();
                addToMap(getCell(i, j));
            }
        }
    }

    private void addToMap (Cell cell){
        myCellStates.putIfAbsent(cell.getState(), new ArrayList<>());
        myCellStates.get(cell.getState()).add(cell);
    }


    protected Cell getCell ( int row, int col){
        return myGrid[row][col];
    }


    public Cell[] setNeighbors ( int row, int col){
        Neighborhood neighbors = new Neighborhood(row, col, myShape, myArr, myEdge, this);
        return neighbors.getNeighbors();
    }

    protected int getWidth () {
        return myWidth;
    }

    protected int getHeight () {
        return myHeight;
    }

    public Cell[][] getGrid () {
        return myGrid;
    }

    public List<Cell> getCellsInState(int state){
        return myCellStates.get(state);
    }

    public void setShape (Shape shape){
        myShape = shape;
    }

    public void setArr(Arrangement arr){
        myArr= arr;
    }
    public void setEdge (Edge edge){
        myEdge= edge;
    }
}
package grid;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    private List<Cell> myNeighbors;
    private List<Cell> myEdgeNeighbors;
    private List<Cell> myVertexNeighbors;
    private Grid myGrid;

    private int myNumNeighbors;
    private int myNumEdgeNeighbors;
    private int myNumVertexNeighbors;
    private int myRow;
    private int myCol;

    public Neighborhood(int row, int col, Shape shape, Arrangement arr, Edge edge, Grid grid) {
        myNeighbors = new ArrayList<>();
        myEdgeNeighbors = new ArrayList<>();
        myVertexNeighbors = new ArrayList<>();
        myGrid = grid;
        myRow = row;
        myCol = col;
        setShape(shape);
        setNeighbors(edge);
        setArr(arr);
    }

    //sets nums
    private void setShape(Shape shape) {
        if (shape.equals(Shape.TRIANGLE)) {
            myNumEdgeNeighbors = 3;
            myNumVertexNeighbors = 9;
        }
        if (shape.equals(Shape.RECTANGLE)) {
            myNumEdgeNeighbors = 4;
            myNumVertexNeighbors = 4;
        }
        if (shape.equals(Shape.HEXAGON)) {
            myNumEdgeNeighbors = 6;
            myNumVertexNeighbors = 0;
        }
        myNumNeighbors = myNumEdgeNeighbors + myNumVertexNeighbors;
    }

    //sets ALL neighbors (complete)--> fills in edge and vertex neighbor lists
    private void setNeighbors(Edge edge) {
        setEdgeNeighbors(edge);
        setVertexNeighbors(edge);
    }

    private void setVertexNeighbors(Edge edge) {
        if (myNumVertexNeighbors == 4) {
            setSquareVertexes(edge);
        }
        if (myNumVertexNeighbors == 9) {
            setTriangleVertexes(edge);
        }
    }

    private void setSquareVertexes(Edge edge) {
        handleToroidal(-1, -1, edge,myVertexNeighbors);
        handleToroidal(-1, 1, edge,myVertexNeighbors);
        handleToroidal(1, 1, edge,myVertexNeighbors);
        handleToroidal(1, -1, edge,myVertexNeighbors);
    }

    private void setTriangleVertexes(Edge edge) {
        handleToroidal(0, -2, edge,myVertexNeighbors);
        handleToroidal(-1, -2, edge,myVertexNeighbors);
        handleToroidal(-1, 0, edge,myVertexNeighbors);
        handleToroidal(0, 2, edge,myVertexNeighbors);
        handleToroidal(1, 2, edge,myVertexNeighbors);
        handleToroidal(1, 0, edge,myVertexNeighbors);
        if (myCol % 2 == 0) {
            handleToroidal(-1, -3, edge,myVertexNeighbors);
            handleToroidal(-1, 1, edge,myVertexNeighbors);
            handleToroidal(1, 1, edge,myVertexNeighbors);
        } else {
            handleToroidal(-1, -1, edge,myVertexNeighbors);
            handleToroidal(1, 3, edge,myVertexNeighbors);
            handleToroidal(1, -1, edge,myVertexNeighbors);
        }
    }

    private void setEdgeNeighbors(Edge edge) {
        handleToroidal(0, -1, edge,myEdgeNeighbors);
        handleToroidal(0, 1, edge,myEdgeNeighbors);
        if (myNumEdgeNeighbors == 3) {
            setTriangleEdges(edge);
        }
        if (myNumEdgeNeighbors == 4) {
            setSquareEdges(edge);
        }
        if (myNumEdgeNeighbors == 6) {
            setHexagonEdges(edge);
        }
    }

    private void setTriangleEdges(Edge edge) {
        if (myCol % 2 == 0) {
            handleToroidal(-1, -1, edge,myEdgeNeighbors);
        } else {
            handleToroidal(1, 1, edge,myEdgeNeighbors);
        }
    }

    private void setSquareEdges(Edge edge) {
        handleToroidal(1, 0, edge,myEdgeNeighbors);
        handleToroidal(-1, 0, edge,myEdgeNeighbors);
    }

    private void setHexagonEdges(Edge edge) {
        setSquareEdges(edge);
        handleToroidal(-1, -1, edge,myEdgeNeighbors);
        handleToroidal(1, 1, edge,myEdgeNeighbors);
    }

    private void handleToroidal(int rn, int cn, Edge edge,List<Cell> neighbors) {
        boolean toroidalRow=false;
        boolean toroidalCol=false;
        try{
            neighbors.add(myGrid.getCell(myRow+rn, myCol+cn));
        }
        catch(IndexOutOfBoundsException e){
            int row = handleVal(myRow, rn, myGrid.getHeight());
            int col = handleVal(myCol, cn, myGrid.getWidth());
            if(edge==Edge.TOROIDAL){
                neighbors.add(myGrid.getCell(row, col));
            }
            if(edge==Edge.TOROIDAL_ROW_ONLY){
                try{
                    neighbors.add(myGrid.getCell(row, myCol+cn));
                }
                catch(IndexOutOfBoundsException f){}
            }
        }

    }

    public int handleVal(int val, int n, int maxVal) {
        if (n == -1 && val == 0) {
            return maxVal - 1;
        }
        Integer[] arr = {1,2,3};
        if (n == 1 && val == maxVal - 1) {
            return 0;
        }
        if (n == -2 && val==1) {
            return maxVal - 1;
        }
        if (n==-2 && val == 0) {
                return maxVal - 2;
        }
        if (n == 2 && val == maxVal - 2) {
            return 0;
        }
        if (n == 2 && val == maxVal - 1) {
            return 1;
        }
        if (n == -3 && val==2) {
            return maxVal - 1;
        }
        if (n==-3 && val == 1) {
            return maxVal - 2;
        }
        if (n==-3 && val == 0) {
            return maxVal - 3;
        }
        if (n == 3 && val==maxVal-3) {
            return 0;
        }
        if (n == 3 && val==maxVal-2) {
            return 1;
        }
        if (n == 3 && val==maxVal-1) {
            return 2;
        }
        return val+n;
    }


    //extracts neighbors wanted
    private void setArr(Arrangement arr) {
        myNeighbors.clear();
        if (arr.equals(Arrangement.COMPLETE)) {
            myNeighbors.addAll(myEdgeNeighbors);
            myNeighbors.addAll(myVertexNeighbors);
        }
        if (arr.equals(Arrangement.CARDINAL)) {
            myNeighbors.addAll(myEdgeNeighbors);
        }
        if (arr.equals(Arrangement.VERTEX)) {
            myNeighbors.addAll(myVertexNeighbors);
        }
    }


    public Cell[] getNeighbors() {
        return myNeighbors.toArray(new Cell[myNeighbors.size()]);
    }
}
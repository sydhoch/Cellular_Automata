package grid;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import cell.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Neighborhood {
    private List<Cell> myNeighbors;
    private List<Cell> myEdgeNeighbors;
    private List<Cell> myVertexNeighbors;
    private Grid myGrid;
    private int myRow;
    private int myCol;

    public Neighborhood(int row, int col, Arrangement arr, Edge edge, Grid grid) {
        myNeighbors = new ArrayList<>();
        myEdgeNeighbors = new ArrayList<>();
        myVertexNeighbors = new ArrayList<>();
        myGrid = grid;
        myRow = row;
        myCol = col;
        setNeighbors(edge);
        setArr(arr);
    }


    //sets ALL neighbors (complete)--> fills in edge and vertex neighbor lists
    private void setNeighbors(Edge edge) {
        setEdgeNeighbors(edge);
        setVertexNeighbors(edge);
    }

    protected abstract void setVertexNeighbors(Edge edge);

    protected abstract void setEdgeNeighbors(Edge edge);

    protected void handleToroidal(int rn, int cn, Edge edge,List<Cell> neighbors) {
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

    private int handleVal(int val, int n, int maxVal) {
        Integer[] pos = {1,2,3};
        if(Arrays.asList(pos).contains(n) && val >=(maxVal-n)){
            return n-(maxVal-val);
        }
        Integer[] neg = {-1,-2,-3};
        if(Arrays.asList(neg).contains(n) && val < (n*-1)){
            return maxVal-((-1*n)-val);
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

    protected List<Cell> getVertexNeighbors(){
        return myVertexNeighbors;
    }
    protected List<Cell> getEdgeNeighbors(){
        return myEdgeNeighbors;
    }
    protected int getCol(){
        return myCol;
    }
    public Cell[] getNeighbors() {
        return myNeighbors.toArray(new Cell[myNeighbors.size()]);
    }
}
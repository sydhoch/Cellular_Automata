package grid;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import cell.Cell;

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

    public Neighborhood(int row, int col, Shape shape, Arrangement arr, Edge edge, Grid grid){
        myNeighbors = new ArrayList<>();
        myEdgeNeighbors = new ArrayList<>();
        myVertexNeighbors = new ArrayList<>();
        setShape(shape);
        setNeighbors(edge);
        setArr(arr);
        myRow = row;
        myCol = col;
        myGrid = grid;

    }

    //sets nums
    private void setShape(Shape shape){
        if(shape.equals(Shape.TRIANGLE)){
            myNumEdgeNeighbors = 3;
            myNumVertexNeighbors = 9;
        }
        if(shape.equals(Shape.RECTANGLE)){
            myNumEdgeNeighbors = 4;
            myNumVertexNeighbors = 4;
        }
        if(shape.equals(Shape.HEXAGON)){
            myNumEdgeNeighbors = 6;
            myNumVertexNeighbors = 0;
        }
        myNumNeighbors = myNumEdgeNeighbors + myNumVertexNeighbors;
    }

    //sets ALL neighbors (complete)--> fills in edge and vertex neighbor lists
    private void setNeighbors(Edge edge){
        setEdgeNeighbors(edge);
        setTriangleVertexes(edge);
    }

    private void setVertexNeighbors(Edge edge){
        if(myNumVertexNeighbors==4){
            setSquareVertexes(edge);
        }if(myNumVertexNeighbors==9){
            setTriangleVertexes(edge);
        }
    }

    private void setSquareVertexes(Edge edge){
        handleToroidal(-1,-1,edge);
        handleToroidal(-1,1,edge);
        handleToroidal(1,1,edge);
        handleToroidal(1,-1,edge);
    }

    private void setTriangleVertexes(Edge edge){
        handleToroidal(0,-2, edge);
        handleToroidal(-1,-2,edge);
        handleToroidal(-1,0, edge);
        handleToroidal(0,2,edge);
        handleToroidal(1,2,edge);
        handleToroidal(1,0,edge);
        if(myCol%2==0){
            handleToroidal(-1,-3, edge);
            handleToroidal(-1,1,edge);
            handleToroidal(1,1,edge);
        }
        else{
            handleToroidal(-1,-1,edge);
            handleToroidal(1,3,edge);
            handleToroidal(1,-1,edge);
        }
    }

    private void setEdgeNeighbors(Edge edge){
        handleToroidal(0,-1,edge);
        handleToroidal(0,1,edge);
        if(myNumEdgeNeighbors==3){
            setTriangleEdges(edge);
        }
        if(myNumEdgeNeighbors==4){
            setSquareEdges(edge);
        }
        if(myNumEdgeNeighbors==6){
            setHexagonEdges(edge);
        }
    }

    private void setTriangleEdges(Edge edge){
        if(myCol%2==0){
            handleToroidal(-1,-1, edge);
        }
        else{
            handleToroidal(1,1,edge);
        }
    }

    private void setSquareEdges(Edge edge){
        handleToroidal(1,0,edge);
        handleToroidal(-1,0,edge);
    }

    private void setHexagonEdges(Edge edge){
        setSquareEdges(edge);
        handleToroidal(-1,-1, edge);
        handleToroidal(1,1, edge);
    }

    private void handleToroidal(int rn, int cn, Edge edge){
        int row=0;
        int col=0;
        boolean toroidalRow = handleVal(row,myRow,rn,myGrid.getHeight());
        boolean toroidalCol = handleVal(col,myCol,cn,myGrid.getWidth());
        if((!toroidalRow && !toroidalCol) || edge==Edge.TOROIDAL){
            myEdgeNeighbors.add(myGrid.getCell(row,col));
        }
    }
    private boolean handleVal(int neighborVal, int val, int n, int maxVal){
        boolean needsToroidal=false;
        if(n==-1 && val==0){
            neighborVal = maxVal-1;
            needsToroidal=true;
        }
        if(n==1 && val==maxVal-1){
            neighborVal = 0;
            needsToroidal=true;
        }
        if(n==-2){
            if(val==1){
                neighborVal=maxVal-1;
                needsToroidal=true;
            }
            if(val==0){
                neighborVal = maxVal-2;
                needsToroidal=true;
            }
        }
        if(n==2){
            if(val==maxVal-2){
                neighborVal=0;
            }
            if(val==maxVal-1){
                neighborVal=1;
            }
        }
        if(n==-3){
            if(val==2){
                neighborVal=maxVal-1;
            }
            if(val==1){
                neighborVal=maxVal-2;
            }
            if(val==1){
                neighborVal=maxVal-3;
            }
        }
        if(n==3){
            if(val==maxVal-3){
                neighborVal=0;
            }
            if(val==maxVal-2){
                neighborVal=1;
            }
            if(val==maxVal-1){
                neighborVal=2;
            }
        }
        else{
            neighborVal = val+n;
        }
        return needsToroidal;
    }





    //extracts neighbors wanted
    private void setArr(Arrangement arr){
        myNeighbors.clear();
        if(arr.equals(Arrangement.COMPLETE)){
            myNeighbors.addAll(myEdgeNeighbors);
            myNeighbors.addAll(myVertexNeighbors);
        }
        if(arr.equals(Arrangement.CARDINAL)){
            myNeighbors.addAll(myEdgeNeighbors);
        }
        if(arr.equals(Arrangement.VERTEX)){
            myNeighbors.addAll(myVertexNeighbors);
        }
    }


    public Cell[] getNeighbors(){
        return myNeighbors.toArray(new Cell[myNeighbors.size()]);
    }
}
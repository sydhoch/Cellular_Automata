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

    public Neighborhood(int row, int col, Shape shape, Arrangement arr, Edge edge, Grid grid){
        setShape(shape);
        setNeighbors(edge);
        setArr(arr);
        myRow = row;
        myCol = myCol;
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
            myNeighbors.addAll(myEdgeNeighbors);
        }
        if(arr.equals(Arrangement.CARDINAL)){
            myNeighbors.addAll(myEdgeNeighbors);
        }
        if(arr.equals(Arrangement.VERTEX)){
            myNeighbors.addAll(myEdgeNeighbors);
        }
    }


    public Cell[] getNeighbors(){
        return myNeighbors.toArray(new Cell[0]);
    }


}


//            if(myRow!=0 && myCol!=0){
//                myEdgeNeighbors.add(myGrid.getCell(myRow-1,myCol-1));
//                //notAddedYet=1;
//            }else if(myCol!=0){
//                myEdgeNeighbors.add(myGrid.getCell(myGrid.getHeight()-1,myCol-1));
//            }else if(myRow!=0){
//                myEdgeNeighbors.add(myGrid.getCell(myRow-1,myGrid.getWidth()-1));
//            }
//            else{
//                myEdgeNeighbors.add(myGrid.getCell(myGrid.getHeight()-1,myGrid.getWidth()-1));
//            }


//if(myCol!=0){
//        myEdgeNeighbors.add(myGrid.getCell(myRow,myCol-1));
//        }
//        handleToroidal(0,-1,edge);
//        else{
//        if(edge==Edge.TOROIDAL){
//        myEdgeNeighbors.add(myGrid.getCell(myRow,myGrid.getWidth()-1));
//        }
//        }
//        if(myCol!=myGrid.getWidth()){
//        myEdgeNeighbors.add(myGrid.getCell(myRow,myCol+1));
//        }
//        else{
//        if(edge==Edge.TOROIDAL){
//        myEdgeNeighbors.add(myGrid.getCell(myRow,0));
//        }
//        }
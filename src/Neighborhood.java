import cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
    private List<Cell> myNeighbors;
    private List<Cell> myEdgeNeighbors;
    private List<Cell> myVertexNeighbors;

    private int myNumNeighbors;
    private int myNumEdgeNeighbors;
    private int myNumVertexNeighbors;

    public Neighborhood(int row, int col, Shape shape, Arrangement arr, Edge edge){
        setShape(shape);
        setEdge(edge);
        setArr(arr);
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
        if(shape.equals(Shape.PENTAGON)){
            myNumEdgeNeighbors = 6;
            myNumVertexNeighbors = 0;
        }
        myNumNeighbors = myNumEdgeNeighbors + myNumVertexNeighbors;
    }

    //sets ALL neighbors (complete)--> fills in edge and vertex neighbor lists
    private void setEdge(Edge edge){
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

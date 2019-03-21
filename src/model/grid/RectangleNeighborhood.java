package model.grid;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;

public class RectangleNeighborhood extends Neighborhood{
    public RectangleNeighborhood(int row, int col, Arrangement arr, Edge edge, Grid grid){
        super(row,col,arr,edge,grid);
    }

    @Override
    protected void setVertexNeighbors(Edge edge){
        handleToroidal(-1, -1, edge,getVertexNeighbors());
        handleToroidal(-1, 1, edge,getVertexNeighbors());
        handleToroidal(1, 1, edge,getVertexNeighbors());
        handleToroidal(1, -1, edge,getVertexNeighbors());
    }

    @Override
    protected void setEdgeNeighbors(Edge edge){
        handleToroidal(1, 0, edge,getEdgeNeighbors());
        handleToroidal(-1, 0, edge,getEdgeNeighbors());
        handleToroidal(0, -1, edge,getEdgeNeighbors());
        handleToroidal(0, 1, edge,getEdgeNeighbors());
    }
}

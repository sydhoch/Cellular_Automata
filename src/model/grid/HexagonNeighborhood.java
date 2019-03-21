package model.grid;

import Enums.Arrangement;
import Enums.Edge;

public class HexagonNeighborhood extends Neighborhood{
    public HexagonNeighborhood(int row, int col, Arrangement arr, Edge edge, Grid grid){
        super(row,col,arr,edge,grid);
    }

    @Override
    protected void setVertexNeighbors(Edge edge){}

    @Override
    protected void setEdgeNeighbors(Edge edge){
        handleToroidal(0, -1, edge,getEdgeNeighbors());
        handleToroidal(0, 1, edge,getEdgeNeighbors());
        handleToroidal(1, 0, edge,getEdgeNeighbors());
        handleToroidal(-1, 0, edge,getEdgeNeighbors());
        handleToroidal(-1, -1, edge,getEdgeNeighbors());
        handleToroidal(1, 1, edge,getEdgeNeighbors());
    }
}

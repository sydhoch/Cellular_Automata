package grid;

import Enums.Arrangement;
import Enums.Edge;

public class TriangleNeighborhood extends Neighborhood {
    public TriangleNeighborhood(int row, int col, Arrangement arr, Edge edge, Grid grid){
        super(row,col,arr,edge,grid);
    }

    @Override
    protected void setVertexNeighbors(Edge edge){
        handleToroidal(0, -2, edge,getVertexNeighbors());
        handleToroidal(-1, -2, edge,getVertexNeighbors());
        handleToroidal(-1, 0, edge,getVertexNeighbors());
        handleToroidal(0, 2, edge,getVertexNeighbors());
        handleToroidal(1, 2, edge,getVertexNeighbors());
        handleToroidal(1, 0, edge,getVertexNeighbors());
        if (getCol() % 2 == 0) {
            handleToroidal(-1, -3, edge,getVertexNeighbors());
            handleToroidal(-1, 1, edge,getVertexNeighbors());
            handleToroidal(1, 1, edge,getVertexNeighbors());
        } else {
            handleToroidal(-1, -1, edge,getVertexNeighbors());
            handleToroidal(1, 3, edge,getVertexNeighbors());
            handleToroidal(1, -1, edge,getVertexNeighbors());
        }
    }
    @Override
    protected void setEdgeNeighbors(Edge edge){
        handleToroidal(0, -1, edge,getEdgeNeighbors());
        handleToroidal(0, 1, edge,getEdgeNeighbors());
        if (getCol() % 2 == 0) {
            handleToroidal(-1, -1, edge,getEdgeNeighbors());
        } else {
            handleToroidal(1, 1, edge,getEdgeNeighbors());
        }
    }

}

package cell;

import cell.Cell;

public class GoLCell extends Cell {
    //state == 0, dead
    //state == 1, alive
    public GoLCell(int state) {
        super(state);
    }

    /**
     * checks how many neighbors are alive and dies or comes back to life based on this number
     * @param neighbors
     */
    @Override
    public void checkNeighborStatus(Cell[] neighbors){
        int neighborsAlive = getNumOfAliveNeighbors(neighbors);
        if(getState()==1){
            if(neighborsAlive<2 || neighborsAlive>3){
                setNextState(0);
            }
        }
        if(getState()==0){
            if(neighborsAlive==3){
                setNextState(1);
            }
        }
    }

    private int getNumOfAliveNeighbors(Cell[] neighbors){
        int aliveNeighbors=0;
        for(int i=0;i<neighbors.length;i++){
            if(neighbors[i].getState()==1) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }


}

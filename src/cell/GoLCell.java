package cell;

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
        int neighborsAlive = getNeighborsOfState(1,neighbors);
        if(this.getState()==1){
            if(neighborsAlive<2 || neighborsAlive>3){
                setNextState(0);
            }
        }
        if(this.getState()==0){
            if(neighborsAlive==3){
                setNextState(1);
            }
        }
    }


}

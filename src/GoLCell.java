public class GoLCell extends Cell {
    //state == 0, dead
    //state == 1, alive
    public GoLCell(int state,int col, int row) {
        super(state,col,row);
    }

    /**
     * checks how many neighbors are alive and dies or comes back to life based on this number
     * @param neighbors
     */
    @Override
    public void checkNeighborStatus(Object[] neighbors){
        int neighborsAlive = getNumOfAliveNeighbors(neighbors);
        if(getState()==1){
            if(neighborsAlive<2 || neighborsAlive>3){
                changeState();
            }
        }
        if(getState()==0){
            if(neighborsAlive==3){
                changeState();
            }
        }
    }
    @Override
    protected void changeState(){
        if(getState()==1){
            setNextState(0);
        }
        else{
            setNextState(1);
        }

    }

    private int getNumOfAliveNeighbors(Object[] neighbors){
        int aliveNeighbors=0;
        for(int i=0;i<neighbors.length;i++){
            if(neighbors[i].equals(1)) {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }


}

public class GoLCell extends Cell {
    //state == 0, dead
    //state == 1, alive
    public GoLCell(int state) {
        super(state);
    }

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
    public void changeState(){
        if(getState()==1){
            setState(0);
        }
        else{
            setState(1);
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

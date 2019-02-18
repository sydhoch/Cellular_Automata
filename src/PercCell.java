public class PercCell extends Cell {
    //state == 0, open
    //state == 1, full
    //state == 2, closed

    public PercCell(int state,int col, int row) {
        super(state,col,row);
    }

    /**
     * checks if any of the neighbors are full and if so, changes the cells current state to full
     * @param neighbors
     */
    @Override
    public void checkNeighborStatus(Object[] neighbors) {
        for(int i=0;i<neighbors.length;i++){
            if(getState()==0){
                if(neighbors[i].equals(1)){
                    changeState();
                    break;
                }
            }
        }
    }

    @Override
    protected void changeState(){
        setNextState(1);
    }
}

public class PercCell extends Cell {
    //state == 0, open
    //state == 1, full
    //state == 2, closed

    public PercCell(int state) {
        super(state);
    }

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
    public void changeState(){
        setState(1);
    }
}

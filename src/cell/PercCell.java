package cell;

import cell.Cell;

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
    public void checkNeighborStatus(Cell[] neighbors) {
        setNextState(this.getState());
        for(int i=0;i<neighbors.length;i++){
            //System.out.println(i);
            if(this.getState()==0){
                Cell p = neighbors[i];

                if(p.getState()==1){
                    setNextState(1);
                    break;
                }
            }
        }

    }
}

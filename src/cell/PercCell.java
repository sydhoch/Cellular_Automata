package cell;

import java.util.ArrayList;
import java.util.Map;

public class PercCell extends Cell {
    //state == 0, open
    //state == 1, full
    //state == 2, closed

    public PercCell(int state) {
        super(state);
    }

    /**
     * checks if any of the neighbors are full and if so, changes the cells current state to full
     * @param neighbors
     */
    @Override
    public void checkNeighborStatus(Cell[] neighbors, Map<Integer, ArrayList<Cell>> cellStates) {
        for(int i=0;i<neighbors.length;i++){
            if(this.getState()==0){
                if(neighbors[i].getState()==1){
                    setNextState(1);
                    break;
                }
            }
        }

    }
}

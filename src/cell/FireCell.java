package cell;

import java.lang.Math;
import java.util.List;
import java.util.Map;


public class FireCell extends Cell{
    //0 = empty
    //1 = tree
    //2 = burning

    private static final double PROB_CATCH = .15;

    public FireCell(int state){
        super(state);
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors, Map<Integer, List<Cell>> cellStates){
        if(this.getState()==2){
            setNextState(0);
        }
        if(this.getState()==1){
            for(int i=0;i<neighbors.length;i++){
                if(neighbors[i].getState()==2){
                    if(Math.random()>PROB_CATCH){
                        this.setNextState(2);
                    }
                    break;
                }
            }
        }

    }
}

package cell;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class SegCell extends Cell{
    //0 = empty
    //1 = jewish
    //2 = christian

    private static final double THRESHOLD = .30;
    private double mySatisfaction;

    public SegCell(int state){
        super(state);
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors, Map<Integer, ArrayList<Cell>> cellStates){
        ArrayList<Cell> emptyCells = cellStates.get(0);
        setSatisfaction(neighbors);
        if(!isSatisfied()){
            Random random = new Random();
            if(emptyCells.size()!=0){
                Cell empty = emptyCells.get(random.nextInt(emptyCells.size()));
                empty.setNextState(this.getState());
                this.setNextState(0);
                cellStates.get(0).remove(empty);
            }
        }
    }

    private void setSatisfaction(Cell[] neighbors){
        if(this.getState()==0){
            setSatisfaction(1);
        }
        else{
            setSatisfaction(computeSatisfaction(neighbors,this.getState()));
        }
    }

    private double computeSatisfaction(Cell[] neighbors,int type){
        float neighborCount = 0;
        float sameTypeNeighbor = 0;
        for(Cell neighbor:neighbors){
            if(neighbor.getState()!=0) {
                neighborCount++;
            }
            if(neighbor.getState()==type){
                sameTypeNeighbor++;
            }

        }
        if(neighborCount==0){
            return 1;
        }
        return sameTypeNeighbor/neighborCount;
    }

    private void setSatisfaction(double satisfaction){
        mySatisfaction=satisfaction;
    }


    private boolean isSatisfied(){ return mySatisfaction>THRESHOLD; }
}

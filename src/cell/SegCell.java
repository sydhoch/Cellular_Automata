package cell;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SegCell extends Cell{

    private double myThreshold = .30;
    private double mySatisfaction;

    public SegCell(int state){
        super(state);
    }

    @Override
    public void checkNeighborStatus(Cell[] neighbors, Map<Integer, List<Cell>> cellStates){
        List<Cell> emptyCells = cellStates.get(0);
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


    private boolean isSatisfied(){ return mySatisfaction>myThreshold; }

    @Override
    public void setSpecialValue(int d){
        myThreshold = .01*d;
    }

    @Override
    public int getSpecialValue(){
        return (int)(myThreshold*100);
    }
}

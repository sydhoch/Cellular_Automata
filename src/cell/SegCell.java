package cell;

import java.util.ArrayList;

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
    public void checkNeighborStatus(Cell[] neighbors){
        if(this.getState()==0){
            setSatisfaction(1);
            //setSatisfaction(100);
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


    public boolean isSatisfied(){ return mySatisfaction>THRESHOLD; }
    public double getSatisfaction(){
        return mySatisfaction;
    }
}

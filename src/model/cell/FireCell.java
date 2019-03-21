package model.cell;

import java.lang.Math;
import java.util.List;
import java.util.Map;


public class FireCell extends Cell{
    //0 = empty
    //1 = tree
    //2 = burning

    private double myProbCatch = .15;

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
                    if(Math.random()>myProbCatch){
                        this.setNextState(2);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void setSpecialValue(int d){
        myProbCatch = .01*d;
    }

    @Override
    public int getSpecialValue(){
        return (int)(myProbCatch*100);
    }
}

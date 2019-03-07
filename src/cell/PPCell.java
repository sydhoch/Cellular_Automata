package cell;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PPCell extends Cell {
    private static final int BABY = 3; //number at which new fish is born & how long shark can live without food
    private static final int INITIAL_SHARK_ENERGY=2;
    private int myEnergy;
    private int myBabyTime;
    //private boolean nextWasSet;
    public PPCell(int state){
        super(state);
        myBabyTime = 0;
        myEnergy = INITIAL_SHARK_ENERGY;
    }
    //0 = empty
    //1 = fish
    //2 = shark

    @Override
    public void checkNeighborStatus(Cell[] neighbors, Map<Integer, List<Cell>> cellStates) {
        Random random = new Random();
        handleFish(random,cellStates);
        handleShark(random,cellStates);
    }

    private void handleFish(Random random,Map<Integer, List<Cell>> cellStates){
        if (this.getState() == 1 & cellStates.get(1).contains(this)) {
            List<Cell> empty = cellStates.get(0);
            if (empty.size() != 0) {
                handleBabies(cellStates);
                setMoveTo(random, empty);
            }
            cellStates.get(1).remove(this);
        }
    }

    private void handleShark(Random random,Map<Integer, List<Cell>> cellStates){
        if(this.getState()==2 && this.getEnergy()<=0){
            this.setNextState(0);
        }
        if (this.getState() == 2 && this.getEnergy()>0) {
            List<Cell> fish = cellStates.get(1);
            if(fish.size()!=0){
                PPCell moveTo = setMoveTo(random,fish);
                moveTo.setEnergy(this.getEnergy()+1);
                handleBabies(cellStates);
            }
            else{
                List<Cell> empty = cellStates.get(0);
                if (empty.size() != 0) {
                    PPCell moveTo = setMoveTo(random,empty);
                    moveTo.setEnergy(this.getEnergy()-1);
                    handleBabies(cellStates);
                }
            }
        }
    }

    private PPCell setMoveTo(Random random, List<Cell> empty) {
        PPCell moveTo = (PPCell)empty.get(random.nextInt(empty.size()));
        moveTo.setNextState(this.getState());
        moveTo.setBabyTime(this.getBabyTime() + 1);
        empty.remove(moveTo);
        return moveTo;
    }

    private void handleBabies( Map<Integer, List<Cell>> cellStates){
        if(this.getBabyTime()<BABY){
            if(cellStates.get(this.getState()).contains(this)){
                this.setNextState(0);
                cellStates.get(this.getState()).remove(this);
            }
        }
        else{
            if(this.getState()==2){
                this.setEnergy(BABY-1);
            }
            this.setBabyTime(0);
        }
    }

    public void setBabyTime(int num){
        myBabyTime = num;
    }
    public int getBabyTime(){
        return myBabyTime;
    }
    public int getEnergy(){
        return myEnergy;
    }
    public void setEnergy(int energy){
        myEnergy = energy;
    }
}

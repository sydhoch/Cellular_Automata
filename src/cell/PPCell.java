package cell;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PPCell extends Cell {
    private static final int BABY = 3; //number at which new fish is born & how long shark can live without food
    private static final int INITIAL_SHARK_ENERGY=2;
    private int myEnergy;
    private int myBabyTime;
    private boolean nextWasSet;
    public PPCell(int state){
        super(state);
        myBabyTime = 0;
        myEnergy = INITIAL_SHARK_ENERGY;

    }
    //0 = empty
    //1 = fish
    //2 = shark

    @Override
    public void checkNeighborStatus(Cell[] neighbors) {
        //south = neighbors[1]
        //west = neighbors[3]
        //east = neighbors[4]
        //north = neighbors[6]
        //System.out.println(this.getState());
        Cell[] neighbors2 = new Cell[4]; ///make get immediate neighbors in cell class too?
        neighbors2[0] = neighbors[1];
        neighbors2[1] = neighbors[3];
        neighbors2[2] = neighbors[4];
        neighbors2[3] = neighbors[6];

        Random random = new Random();
        List<Cell> cells = new ArrayList<>();
        int count;
        if (this.getState() == 1 & !this.getStateWasSet()) {
            List<Cell> beforeCells = getNeighborsOfState(0, neighbors2);
            count = getCount(cells, beforeCells);
            if (count != 0) {
                handleBabies();
                PPCell moveTo = (PPCell)cells.get(random.nextInt(count));
                moveTo.setNextState(1);
                moveTo.setNextWasSet(true);
                moveTo.setBabyTime(this.getBabyTime() + 1);
            }
        }
        if(this.getState()==2 && this.getEnergy()<=0){
            this.setNextState(0);
            this.setNextWasSet(true);
        }
        if (this.getState() == 2 && this.getEnergy()>0) {
            List<Cell> beforeCells = getNeighborsOfState(1, neighbors2);
            count = getCount(cells, beforeCells);
            if (count != 0) {
                System.out.println("in here");
                PPCell moveTo = (PPCell) cells.get(random.nextInt(count));
                moveTo.setNextState(this.getState());
                moveTo.setNextWasSet(true);
                moveTo.setBabyTime(this.getBabyTime() + 1);
                moveTo.setEnergy(this.getEnergy()+1);
            }
            else{
                beforeCells = getNeighborsOfState(0, neighbors2);
                count = getCount(cells, beforeCells);
                if (count != 0) {
                    PPCell moveTo = (PPCell) cells.get(random.nextInt(count));
                    moveTo.setNextState(this.getState());
                    moveTo.setNextWasSet(true);
                    moveTo.setBabyTime(this.getBabyTime() + 1);
                    moveTo.setEnergy(this.getEnergy()-1);
                }
            }
            handleBabies();
        }
    }

    private int getCount(List<Cell> cells, List<Cell> beforeCells) {
        int count;
        if(beforeCells.size()>0) {
            for (int i = 0; i < beforeCells.size(); i++) {
                if (!((PPCell) beforeCells.get(i)).getStateWasSet()) {
                    cells.add(beforeCells.get(i));
                }
            }
        }
        count = cells.size();
        return count;
    }

    private void handleBabies(){
        if(this.getBabyTime()<BABY){
            System.out.println("in this one");
            if(!this.getStateWasSet()){
                System.out.println("but not this one");
                this.setNextState(0);
                this.setNextWasSet(true);
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

    public boolean getStateWasSet(){
        return nextWasSet;
    }
    public void setNextWasSet(boolean value){
        nextWasSet=value;
    }
}

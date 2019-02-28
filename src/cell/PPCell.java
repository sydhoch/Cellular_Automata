package cell;

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
            for(int i=0;i<beforeCells.size();i++){
                if(!((PPCell)beforeCells.get(i)).getStateWasSet()){
                    cells.add(beforeCells.get(i));
                }
            }
            count = cells.size();
            if (count != 0) {
                PPCell moveTo = (PPCell)cells.get(random.nextInt(count));
                moveTo.setNextState(1);
                moveTo.setNextWasSet(true);
                moveTo.setBabyTime(this.getBabyTime() + 1);
                handleBabies();
            }
        }
        if (this.getState() == 2&& this.getEnergy()>0) {
            cells = getNeighborsOfState(1, neighbors2);
            count = cells.size();
            if (count != 0) {
                PPCell moveTo = (PPCell) cells.get(random.nextInt(count));
                moveTo.setNextState(this.getState());
                moveTo.setNextWasSet(true);
                moveTo.setBabyTime(this.getBabyTime() + 1);
                moveTo.setEnergy(this.getEnergy()+1);
            }
            else{
                List<Cell> beforeCells = getNeighborsOfState(0, neighbors2);
                for(int i=0;i<beforeCells.size();i++){
                    if(((PPCell)beforeCells.get(i)).getStateWasSet()==false){
                        cells.add(cells.get(i));
                    }
                }
                count = cells.size();
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

    private void handleBabies(){
        if(this.getBabyTime()<BABY){
            if((this.getStateWasSet()==false)){
                this.setNextState(0);
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

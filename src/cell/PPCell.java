package cell;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PPCell extends Cell {
    private static final int BABY = 3; //number at which new fish is born & how long shark can live without food
    private static final int INITIAL_SHARK_ENERGY=2;
    public PPCell(int state){
        super(state);
        setTimeAlive(0);
        setEnergy(INITIAL_SHARK_ENERGY);
    }
    //0 = empty
    //1 = fish
    //2 = shark

    @Override
    public void checkNeighborStatus(Cell[] neighbors, Map<Integer, List<Cell>> cellStates) {
        Random random = new Random();
        //getNeighborsOfState(1,neighbors);
        handleFish(random,cellStates,neighbors);
        handleShark(random,cellStates,neighbors);
    }

    private List<Cell> checkNeighborsNotSetAlready(List<Cell> neighborsOfState,List<Cell> stillValid){
        neighborsOfState.retainAll(stillValid);
        return neighborsOfState;
    }

    private void handleFish(Random random,Map<Integer, List<Cell>> cellStates, Cell[] neighbors){
        List<Cell> neighborsNotSet = checkNeighborsNotSetAlready(getNeighborsOfState(0,neighbors),cellStates.get(1));
        if (this.getState() == 1 & cellStates.get(1).contains(this)) {
            List<Cell> empty = neighborsNotSet;
            if (empty.size() != 0) {
                handleBabies(cellStates);
                setMoveTo(random, empty);
            }
            handleBabies(cellStates);
        }
    }

    private void handleShark(Random random,Map<Integer, List<Cell>> cellStates, Cell[] neighbors){
        List<Cell> neighborsNotSetFish = checkNeighborsNotSetAlready(getNeighborsOfState(1,neighbors),cellStates.get(1));
        List<Cell> neighborsNotSetEmpty = checkNeighborsNotSetAlready(getNeighborsOfState(0,neighbors),cellStates.get(1));
        if(this.getState()==2 && this.getEnergy()<=0){
            this.setNextState(0);
        }
        if (this.getState() == 2 && this.getEnergy()>0) {
            List<Cell> fish = neighborsNotSetFish;
            if(fish.size()!=0){
                Cell moveTo = setMoveTo(random,fish);
                moveTo.setEnergy(this.getEnergy()+1);
                handleBabies(cellStates);
            }
            else{
                List<Cell> empty = neighborsNotSetEmpty;
                if (empty.size() != 0) {
                    Cell moveTo = setMoveTo(random,empty);
                    moveTo.setEnergy(this.getEnergy()-1);
                    handleBabies(cellStates);
                }
            }
        }
    }

    private Cell setMoveTo(Random random, List<Cell> empty) {
        Cell moveTo = empty.get(random.nextInt(empty.size()));
        moveTo.setNextState(this.getState());
        moveTo.setTimeAlive(this.getTimeAlive() + 1);
        empty.remove(moveTo);
        return moveTo;
    }

    private void handleBabies( Map<Integer, List<Cell>> cellStates){
        if(this.getTimeAlive()<BABY){
            if(cellStates.get(this.getState()).contains(this)){
                this.setNextState(0);
                cellStates.get(this.getState()).remove(this);
            }
        }
        else{
            if(this.getState()==2){
                this.setEnergy(BABY-1);
            }
            this.setTimeAlive(0);
        }
    }
}

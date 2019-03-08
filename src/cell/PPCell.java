package cell;
import java.util.ArrayList;
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
        handleFish(random,neighbors);
        handleShark(random,neighbors);
    }

    private List<Cell> checkNeighborsNotSetAlready(List<Cell> neighborsOfState){
        List<Cell> cellsAvailable = new ArrayList<>();
        for(Cell cell: neighborsOfState){
            if(cell.getState()==cell.getNextState()){
                cellsAvailable.add(cell);
            }
        }
        return cellsAvailable;
    }

    private void handleFish(Random random,Cell[] neighbors){
        List<Cell> empty = checkNeighborsNotSetAlready(getNeighborsOfState(0,neighbors));
        if (this.getState() == 1 & this.getNextState()!=2) {
            if (empty.size() != 0){
                setMoveTo(random, empty);
                handleBabies();
            }
        }
    }

    private void handleShark(Random random, Cell[] neighbors){
        List<Cell> empty = checkNeighborsNotSetAlready(getNeighborsOfState(0,neighbors));
        List<Cell> fish = checkNeighborsNotSetAlready(getNeighborsOfState(1,neighbors));
        if(this.getState()==2 && this.getEnergy()<=0){
            this.setNextState(0);
        }
        if (this.getState() == 2 && this.getEnergy()>0) {
            if(fish.size()!=0){
                Cell moveTo = setMoveTo(random,fish);
                moveTo.setEnergy(this.getEnergy()+1);
                handleBabies();
            }
            else{
                if (empty.size() != 0) {
                    Cell moveTo = setMoveTo(random,empty);
                    moveTo.setEnergy(this.getEnergy()-1);
                    handleBabies();
                }
            }
        }
    }

    private Cell setMoveTo(Random random, List<Cell> empty) {
        Cell moveTo = empty.get(random.nextInt(empty.size()));
        moveTo.setNextState(this.getState());
        moveTo.setTimeAlive(this.getTimeAlive() + 1);
        return moveTo;
    }

    private void handleBabies(){
        if(this.getTimeAlive()<BABY){
            this.setNextState(0);
        }
        else{
            if(this.getState()==2){
                this.setEnergy(BABY-1);
            }
            this.setTimeAlive(0);
        }
    }
}

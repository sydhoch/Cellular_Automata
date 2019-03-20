package model.cell;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public abstract class Cell extends Object{
    private int myState;
    private int myNextState;
    private int myEnergy;
    private int myTimeAlive;

    public Cell(int state){
        myState = state;
        setNextState(state);
    }

    /**
     * updates the model.grid.cell to its next state
     */
    public void updateCell(){
        myState = myNextState;
    }

    /**
     *
     * @returns the image of the model.grid.cell
     */
//    public Rectangle getRectangle(){
//        return myCellImage;
//    }

    /**
     * Checks the neighbors and changes the state of the current model.grid.cell if necessary
     * @param neighbors
     */
    //public abstract void checkNeighborStatus(Cell[] neighbors);

    public abstract void checkNeighborStatus(Cell[] neighbors, Map<Integer, List<Cell>> cellStates);

    protected List<Cell> getNeighborsOfState(int state, Cell[] neighbors){
        List<Cell> cellList = new ArrayList<>();
        for(int i=0;i<neighbors.length;i++){
            if(neighbors[i].getState()==state){
                cellList.add(neighbors[i]);
            }
        }
        return cellList;
    }

    protected void setTimeAlive(int time){
        myTimeAlive=time;
    }
    protected int getTimeAlive(){
        return myTimeAlive;
    }
    protected void setEnergy(int energy){
        myEnergy=energy;
    }
    protected int getEnergy(){
        return myEnergy;
    }

    public void setNextState(int nextState){
        myNextState = nextState;
    }

    public int getState(){
        return myState;
    }

    protected int getNextState(){
        return myNextState;
    }

    public void setSpecialValue(int d){
        setEnergy(d);
    }
    public int getSpecialValue(){
        return getEnergy();
    }
}

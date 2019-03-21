package model.cell;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 * The abstract class Cell represents one of the cells in the grid.
 * Depends on Object class so we can use Cell arrays
 * Example: we want to make a cell of state 1 we put 1 has the parameter
 *
 * @author sydneyhochberg
 */
public abstract class Cell extends Object{
    private int myState;
    private int myNextState;
    private int myEnergy;
    private int myTimeAlive;
    private int mySpecialValue;

    /**
     * Sets the cells state to be the parameter integer.
     * Sets the next state of the cell to be the current state as a default before checkNeighborStatus is called
     * which may change the next state depending on the states of the neighbors.
     * @param state
     */
    protected Cell(int state){
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
<<<<<<< HEAD:src/cell/Cell.java
     * Checks the neighbors and changes the state of the current cell if necessary
     * @param neighbors is used to see the states of the cell's neighbors
     * @param cellStates is used in simulations that need to see the states of cells who are not the cells neighbors
     *                    The key is a cell state and the value is all of the cells in that state
=======
     * Checks the neighbors and changes the state of the current model.grid.cell if necessary
     * @param neighbors
>>>>>>> master:src/model/cell/Cell.java
     */
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

    /**
     * Sets the next state that the cell will be after updateCell() is called in the next step.
     * @param nextState
     */
    public void setNextState(int nextState){
        myNextState = nextState;
    }

    /**
     *
     * @returns the state of the cell
     */
    public int getState(){
        return myState;
    }

    protected int getNextState(){
        return myNextState;
    }

    /**
     * sets the special value in simulations (energy in PPCell, probability in FireCell, etc.)
     * @param d
     */
    public void setSpecialValue(int d){
        mySpecialValue=d;
    }

    /**
     *
     * @returns the special value for the front end to use
     */
    public int getSpecialValue(){
        return mySpecialValue;
    }
}

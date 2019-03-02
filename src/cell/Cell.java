package cell;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Cell extends Object{
    private int myState;
    private int myNextState;

    public Cell(int state){
        myState = state;
        setNextState(state);
    }

    /**
     * updates the cell to its next state
     */
    public void updateCell(){
        myState = myNextState;
    }

    /**
     *
     * @returns the image of the cell
     */
//    public Rectangle getRectangle(){
//        return myCellImage;
//    }

    /**
     * Checks the neighbors and changes the state of the current cell if necessary
     * @param neighbors
     */
    public abstract void checkNeighborStatus(Cell[] neighbors);

    //public abstract void checkNeighborStatus(Cell[] neighbors, HashMap<Integer, ArrayList<Cell>> cellStates);

    protected List<Cell> getNeighborsOfState(int state, Cell[] neighbors){
        List<Cell> cellList = new ArrayList<>();
        for(int i=0;i<neighbors.length;i++){
            if(neighbors[i].getState()==state){
                cellList.add(neighbors[i]);
            }
        }
        return cellList;
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
}

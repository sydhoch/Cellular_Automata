package cell;

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


    protected void setNextState(int nextState){
        myNextState = nextState;
    }

    public int getState(){
        return myState;
    }

    protected int getNextState(){
        return myNextState;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Cell)){
            return false;
        }
        else{
            return this.getState() == ((Cell) obj).getState();
        }
    }
}

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public abstract class Cell extends Object{
    private int myState;
    private int myNextState;
    private Rectangle myCellImage;

    private static int CELL_SIZE = 10;
    private static String ZERO_COLOR = "BLUE";
    private static String ONE_COLOR = "RED";
    private static String TWO_COLOR = "YELLOW";

    public Cell(int state,int col, int row){
        myCellImage = new Rectangle(col*CELL_SIZE,row*CELL_SIZE,CELL_SIZE,CELL_SIZE);
        myState = state;
        changeColor(state);
    }

    public void updateCell(){
        myState = myNextState;
        changeColor(myNextState);
    }

    /**
     *
     * @returns the image of the cell
     */
    public Rectangle getRectangle(){
        return myCellImage;
    }

    /**
     * Checks the neighbors and changes the state of the current cell if necessary
     * @param neighbors
     */
    public abstract void checkNeighborStatus(Object[] neighbors);

    protected abstract void changeState();


    private void changeColor(int state){
        if(state==0){
            myCellImage.setFill(Color.valueOf(ZERO_COLOR));
        }
        else if(state==1){
            myCellImage.setFill(Color.valueOf(ONE_COLOR));
        }
        else{
            myCellImage.setFill(Color.valueOf(TWO_COLOR));
        }

    }

    public void setNextState(int nextState){
        myNextState = nextState;
    }

    protected int getState(){
        return myState;
    }

    protected boolean equals(Cell o) {
        return this.getState() == o.getState();
    }

}

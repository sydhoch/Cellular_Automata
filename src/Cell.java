import javafx.scene.paint.Paint;
import java.util.Objects;

public abstract class Cell extends Object{
    private int myState;
    private Paint myColor;

    public Cell(int state){
        myState = state;
    }

    public int getState(){
        return myState;
    }

    public abstract void checkNeighborStatus(Object[] neighbors);

    public abstract void changeState();

    protected void setState(int state){
        myState = state;
    }

    protected boolean equals(Cell o) {
        return this.getState() == o.getState();
    }

    private void setColor(String color){
        myColor.valueOf(color);
    }
}

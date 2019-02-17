import javafx.scene.paint.Paint;
import java.util.Objects;

public abstract class Cell extends Object{
    private int myState;
    private Paint myColor;
    private static String zeroColor = "BLUE";
    private static String oneColor = "RED";
    private static String twoColor = "YELLOW";

    public Cell(int state){
        myState = state;
        changeColor(state);
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

    private void changeColor(int state){
        if(state==0){
            setColor(zeroColor);
        }
        else if(state==1){
            setColor(oneColor);
        }
        else{
            setColor(twoColor);
        }

    }
    public Paint getColor(){
        return myColor;
    }

}

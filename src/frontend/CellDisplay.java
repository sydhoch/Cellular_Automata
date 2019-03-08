package frontend;

import Enums.SimType;
import cell.Cell;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public abstract class CellDisplay {
    private int mySize;
    private int myGridHeight;
    private int myGridWidth;
    private String[] myColors;

    public CellDisplay(){}

    public CellDisplay(int size, int height, int width, String[] colors){
        mySize = size;
        myColors = colors;
        myGridHeight = height;
        myGridWidth = width;
        setSize(mySize, myGridHeight, myGridWidth);
    }

    protected void removeFromScreen(Group myRoot){
        List<Node> toRemove = new ArrayList<>();
        for (Node n : myRoot.getChildren()) {
            remove(n, toRemove);
        }
        myRoot.getChildren().removeAll(toRemove);
    }

    protected String[] getColors(){
        return myColors;
    }

    public void changeColors(String[] colors){
        myColors = colors;
    }
    protected abstract Node setView(int i, int j, int state);
    protected abstract void remove(Node n, List<Node> toRemove);
    protected abstract void setSize(int size, int height, int width);

}

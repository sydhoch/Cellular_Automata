package frontend;

import Enums.SimType;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class RectangleDisplay extends CellDisplay {
    private int myCellHeight;
    private int myCellWidth;
    private Paint[] myColors;


    public RectangleDisplay(int size, int height, int width, SimType s, Paint[] colors){
        super(size, height, width, s, colors);
        myColors = super.getColors();
    }

    protected void remove(Node n, List<Node> toRemove) {
        if (n instanceof Rectangle) {
            toRemove.add(n);
        }
    }

    protected Node setView(int i, int j, int state, SimType s){
        myColors = super.getColors();
        Rectangle rect = new Rectangle(myCellWidth * i, myCellHeight * j, myCellWidth, myCellHeight);
        rect.setFill(myColors[state]);
        return rect;
    }

    protected void setSize(int size, int height, int width){
        myCellWidth = size / height;
        myCellHeight = size / width;
    }


}

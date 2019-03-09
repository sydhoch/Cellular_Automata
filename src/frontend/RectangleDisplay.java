package frontend;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class RectangleDisplay extends ShapeDisplay {
    private int myCellHeight;
    private int myCellWidth;
    private Paint[] myColors;


    public RectangleDisplay(int size, int height, int width, String[] colors,boolean gridOutline){
        super(size, height, width, colors,gridOutline);
        myColors = convertColors(getColors());
    }

    protected void remove(Node n, List<Node> toRemove) {
        if (n instanceof Rectangle) {
            toRemove.add(n);
        }
    }

    protected Node setView(int i, int j, int state){
        myColors = convertColors(getColors());
        Rectangle rect = new Rectangle(myCellWidth * j, myCellHeight * i, myCellWidth, myCellHeight);
        rect.setFill(myColors[state]);
        handleGridOutline(rect);
//        if(getGridOutline()){
//            rect.setStroke(Color.BLACK);
//            rect.setStrokeWidth(2);
//        }
        return rect;
    }

    protected void setSize(int size, int height, int width){
        myCellHeight = size / height;
        myCellWidth = size / width;
    }


}

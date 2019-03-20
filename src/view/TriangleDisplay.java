package view;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.List;

public class TriangleDisplay extends ShapeDisplay {
    private int myCellHeight;
    private int myCellWidth;
    private Paint[] myColors;

    public TriangleDisplay(int size, int height, int width, String[] colors){
        super(size, height, width, colors);
        myColors = convertColors(getColors());;
    }

    public Node setView(int x, int y, int state){
        myColors = convertColors(getColors());;
        int pointX;
        int pointY;
        int leftX;
        int leftY;
        int rightX;
        int rightY;

        if(y%2 == 0){
            pointX = (y)*myCellWidth;
            pointY = (x)*myCellHeight;
            leftX = (y)*myCellWidth;
            leftY = ((x+1))*myCellHeight;
            rightX = ((y+2))*myCellWidth;
            rightY = (x)*myCellHeight;
        }
        else{
            pointX = (y+1)*myCellWidth;
            pointY = (x+1)*myCellHeight;
            leftX = (y-1)*myCellWidth;
            leftY = ((x+1))*myCellHeight;
            rightX = ((y+1))*myCellWidth;
            rightY = (x)*myCellHeight;
        }
        Polygon tri = new Polygon(pointX, pointY, leftX, leftY, rightX, rightY);
        tri.setFill(myColors[state]);
        return tri;
    }

    public void setSize(int size, int height, int width){
        myCellHeight = size / height;
        if(width % 2 == 0) {
            myCellWidth = size / width;
        }
        else{
            myCellWidth = size / (width+1);
        }
    }

    public void remove(Node n, List<Node> toRemove) {
        if (n instanceof Polygon) {
            toRemove.add(n);
        }
    }
}

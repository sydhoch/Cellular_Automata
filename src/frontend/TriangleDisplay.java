package frontend;

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

    protected Node setView(int x, int y, int state){
        myColors = convertColors(getColors());;
        int pointX;
        int pointY;
        int leftX;
        int leftY;
        int rightX;
        int rightY;

        if(y%2 == 0){
            pointX = (y)*myCellWidth/2;
            pointY = (x)*myCellHeight*2;
            leftX = (y)*myCellWidth/2;
            leftY = ((x+1))*myCellHeight*2;
            rightX = ((y+2))*myCellWidth/2;
            rightY = (x)*myCellHeight*2;
        }
        else{
            pointX = (y+1)*myCellWidth/2;
            pointY = (x+1)*myCellHeight*2;
            leftX = (y-1)*myCellWidth/2;
            leftY = ((x+1))*myCellHeight*2;
            rightX = ((y+1))*myCellWidth/2;
            rightY = (x)*myCellHeight*2;
        }
        Polygon tri = new Polygon(pointX, pointY, leftX, leftY, rightX, rightY);
        tri.setFill(myColors[state]);
        return tri;
    }

    protected void setSize(int size, int height, int width){
        myCellWidth = size / height;
        myCellHeight = size / width;
    }

    protected void remove(Node n, List<Node> toRemove) {
        if (n instanceof Polygon) {
            toRemove.add(n);
        }
    }
}

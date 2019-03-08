package frontend;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.List;

public class HexagonDisplay extends ShapeDisplay {
    private double myCellHeight;
    private double myCellWidth;
    private Paint[] myColors;
    private int myHeight;
    private int myWidth;

    public HexagonDisplay(int size, int height, int width, String[] colors) {
        super(size, height, width, colors);
        myColors = convertColors(getColors());
        myHeight = height;
        myWidth = width;
    }

    protected Node setView(int x, int y, int state) {
        myColors = convertColors(getColors());
        double point1X = .25*myCellWidth+ y*myCellWidth;
        double point1Y = myCellHeight*(myHeight-x+1)- y*myCellHeight/2;
        double point2X = point1X + myCellWidth*3/4;
        double point2Y = point1Y;
        double point3X = point2X + myCellWidth / 4;
        double point3Y = point2Y + myCellHeight / 2;
        double point4X = point2X;
        double point4Y = point2Y + myCellHeight;
        double point5X = point1X;
        double point5Y = point1Y + myCellHeight;
        double point6X = point1X - myCellWidth / 4;
        double point6Y = point2Y + myCellHeight / 2;

        Polygon hex = new Polygon(point1X, point1Y,
                point2X, point2Y,
                point3X, point3Y,
                point4X, point4Y,
                point5X, point5Y,
                point6X, point6Y);
        hex.setFill(myColors[state]);
        return hex;
    }

//        protected Node setView(int x, int y, int state) {
//            myColors = convertColors(getColors());
//            double xOffset = myCellWidth*y /4 *3;
//            double yOffset = x * myCellHeight;
//            if(y % 2 != 0){
//                yOffset = yOffset +  + (myCellHeight / 2);
//            }
//            double point1X = yOffset;
//            double point1Y = myCellWidth/4 + xOffset;
//            double point2X = myCellHeight/2 + yOffset;
//            double point2Y = xOffset;
//            double point3X = myCellHeight + yOffset;
//            double point3Y = myCellWidth/4 + xOffset;
//            double point4X = myCellHeight + yOffset;
//            double point4Y = myCellWidth/4*3 + xOffset;
//            double point5X = myCellHeight/2+ yOffset;
//            double point5Y = myCellWidth + xOffset;
//            double point6X = yOffset;
//            double point6Y = myCellWidth/4*3 + xOffset;
//
//
//            Polygon hex = new Polygon(point1X, point1Y,
//                    point2X, point2Y,
//                    point3X, point3Y,
//                    point4X, point4Y,
//                    point5X, point5Y,
//                    point6X, point6Y);
//            hex.setFill(myColors[state]);
//            return hex;
//        }

    @Override
    protected void setSize(int size, int height, int width) {
        myCellHeight = size/(height+(width-1)*.5);
        myCellWidth = size/(width+(width-1)*.5);
    }

    protected void remove(Node n, List<Node> toRemove) {
        if (n instanceof Polygon) {
            toRemove.add(n);
        }
    }
}

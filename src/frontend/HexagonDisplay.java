package frontend;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.List;

public class HexagonDisplay extends ShapeDisplay {
    private double myCellHeight;
    private double myCellWidth;
    private Paint[] myColors;
    private int myWidth;

    public HexagonDisplay(int size, int height, int width, String[] colors) {
        super(size, height, width, colors);
        myColors = convertColors(getColors());
        myWidth = width;
    }

    protected Node setView(int x, int y, int state) {
        myColors = convertColors(getColors());
        double point1X = myCellWidth * (1.25*(x+.25));
        double point1Y = y * myCellHeight + (myCellHeight * (myWidth - 1 - x) / 2);
        double point2X = point1X + myCellWidth;
        double point2Y = point1Y;
        double point3X = point2X + myCellWidth / 4;
        double point3Y = point2Y + myCellHeight / 2;
        double point4X = point2X;
        double point4Y = point2Y + myCellHeight;
        double point5X = point1X;
        double point5Y = point1Y + myCellHeight;
        double point6X = point1X - myCellWidth / 4;
        double point6Y = point2Y + myCellHeight / 2;

        Polygon tri = new Polygon(point1X, point1Y,
                point2X, point2Y,
                point3X, point3Y,
                point4X, point4Y,
                point5X, point5Y,
                point6X, point6Y);
        tri.setFill(myColors[state]);
        return tri;
    }

    @Override
    protected void setSize(int size, int height, int width) {
        myCellWidth = size / height * .75;
        myCellHeight = size / width * .75;
    }

    protected void remove(Node n, List<Node> toRemove) {
        if (n instanceof Polygon) {
            toRemove.add(n);
        }
    }
}

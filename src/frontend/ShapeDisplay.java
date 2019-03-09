package frontend;

//import Enums.Shape;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import java.awt.Rectangle;
import java.util.List;

public abstract class ShapeDisplay extends CellDisplay {

    public ShapeDisplay(int size, int height, int width, String[] colors,boolean gridOutline){
        super(size, height, width, colors,gridOutline);

    }
    protected Paint[] convertColors(String[] colors){
        Paint[] p = new Paint[colors.length];
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] != null) {
                p[i] = Paint.valueOf(colors[i]);
            }
            else{
                p[i] = Color.WHITE;
            }
        }
        return p;
    }
    protected abstract Node setView(int i, int j, int state);
    protected abstract void remove(Node n, List<Node> toRemove);

    protected void handleGridOutline(Shape shape){
        if(getGridOutline()){
            shape.setStroke(Color.BLACK);
            shape.setStrokeWidth(2);
        }
    }
}

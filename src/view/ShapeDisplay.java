package view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.List;

public abstract class ShapeDisplay extends CellDisplay {

    public ShapeDisplay(int size, int height, int width, String[] colors){
        super(size, height, width, colors);

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
    public abstract Node setView(int i, int j, int state);
    public abstract void remove(Node n, List<Node> toRemove);
}

/**
 * This class is the abstract display object.
 *
 * @author Sara Behn
 * @author Sydney Hochberg
 * @author Arilia Frederick
 */
package view;

import javafx.scene.Group;
import javafx.scene.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class CellDisplay {
    private int mySize;
    private int myGridHeight;
    private int myGridWidth;
    private String[] myColors;
    private boolean myGridOutline;

<<<<<<< HEAD:src/view/CellDisplay.java
    protected CellDisplay(int size, int height, int width, String[] colors) {
=======
    public CellDisplay() {
    }

    public CellDisplay(int size, int height, int width, String[] colors,boolean gridOutline) {
        myGridOutline=gridOutline;
>>>>>>> da42c476fed530810561b29f4a9f02f9a64f929e:src/frontend/CellDisplay.java
        mySize = size;
        myColors = colors;
        myGridHeight = height;
        myGridWidth = width;
        setSize(mySize, myGridHeight, myGridWidth);
    }

    public void removeFromScreen(Group myRoot) {
        List<Node> toRemove = new ArrayList<>();
        for (Node n : myRoot.getChildren()) {
            remove(n, toRemove);
        }
        myRoot.getChildren().removeAll(toRemove);
    }

    String[] getColors() {
        return myColors;
    }

    public void changeColors(String[] colors) {
        myColors = colors;
    }

    public abstract Node setView(int i, int j, int state);

    public abstract void remove(Node n, List<Node> toRemove);

    public abstract void setSize(int size, int height, int width);

    protected boolean getGridOutline(){
        return myGridOutline;
    }

}

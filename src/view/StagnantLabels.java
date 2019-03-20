package view;

import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StagnantLabels implements DisplayObject {
    private static final String LOAD_LABEL = "LoadLabel";
    private static final String SPEED_LABEL = "SpeedLabel";
    private static final String COLOR_LABEL = "ColorLabel";
    private static final String SHAPE_LABEL = "ShapeLabel";

    private ResourceBundle myResources;
    private List<Node> myLabels;

    public StagnantLabels() {
        myResources = ResourceBundle.getBundle(RESOURCES);
        myLabels = new ArrayList<>();
        setObjects();
    }

    public void setObjects() {
        for (int i = 0; i < SIMULATION_TYPES.length; i++) {
            myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[1 + i], myResources.getString(SIMULATION_TYPES[i].toString())));
        }
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[0], myResources.getString(LOAD_LABEL)));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[12], myResources.getString(SPEED_LABEL)));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[16], myResources.getString(COLOR_LABEL)));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[21], myResources.getString(SHAPE_LABEL)));
    }

    public List<Node> getObjects() {
        return myLabels;
    }

}

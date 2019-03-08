package frontend;

import Enums.SimType;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StagnantLabels {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String SIDEBAR_RESOURCE = "SideBar";
    private static final int[] COLUMN_POSITION = {510, 610, 710, 740, 770};
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500, 520, 540, 560, 580, 600};
    private static final SimType[] SIMULATION_TYPES = {SimType.FIRE, SimType.GOL, SimType.PERC, SimType.PP, SimType.RPS, SimType.SEG};
    private static final String LOAD_LABEL = "LoadLabel";
    private static final String SPEED_LABEL = "SpeedLabel";
    private static final String COLOR_LABEL = "ColorLabel";
    private static final String SHAPE_LABEL = "ShapeLabel";

    private ResourceBundle myResources;
    private List<Text> myLabels;

    public StagnantLabels(){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myLabels = new ArrayList<>();
        setLabels();
    }

    private void setLabels(){
        for (int i = 0; i < SIMULATION_TYPES.length; i++) {
            myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[1 + i], myResources.getString(SIMULATION_TYPES[i].toString())));
        }
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[0], myResources.getString(LOAD_LABEL)));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[12], myResources.getString(SPEED_LABEL)));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[16], myResources.getString(COLOR_LABEL)));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[21], myResources.getString(SHAPE_LABEL)));
    }

    List<Text> getLabels(){
        return myLabels;
    }

}

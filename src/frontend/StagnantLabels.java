package frontend;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StagnantLabels {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String SIDEBAR_RESOURCE = "Clickable";
    private static final int[] COLUMN_POSITION = {510, 610, 710, 740, 770};
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500};

    private ResourceBundle myResources;
    private List<Text> myLabels;

    public StagnantLabels(){
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myLabels = new ArrayList<>();
        setLabels();
    }

    private void setLabels(){
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[0], myResources.getString("LoadLabel")));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[12], myResources.getString("SpeedLabel")));
        myLabels.add(new Text(COLUMN_POSITION[0], ROW_POSITION[16], myResources.getString("ColorLabel")));
    }

    List<Text> getLabels(){
        return myLabels;
    }

}

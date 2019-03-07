package frontend;

import Enums.SimType;
import grid.Grid;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Enums.SimType.*;

public class Clickable {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String SIDEBAR_RESOURCE = "SideBar";
    private static final String PAUSE_RESUME_LABEL = "PauseResumeButton";
    private static final String RESTART_LABEL = "RestartButton";
    private static final String STEP_THROUGH_LABEL = "StepThroughButton";
    private static final String IMAGES_LABEL = "Images";
    private static final String COLOR_LABEL = "ColorScheme";
    private static final String FILE_MIDDLE_NAME = "-grid-";
    private static final String CSV_EXTENSION = ".csv";
    private static final int MIN_SPEED = 0;
    private static final int MAX_SPEED= 2;

    private static final int[] COLUMN_POSITION = {510, 610, 710, 740, 770};
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500};
    private static final String[][] PAINT_COLORS = {{"BLUE", "CYAN", "SKYBLUE"}, {"RED", "MISTYROSE", "MAROON"}, {"GREEN", "DARKGREEN", "LIGHTGREEN"}};
    private static final SimType[] SIMULATION_TYPES = {FIRE, GOL, PERC, PP, RPS, SEG};

    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;
    private boolean myStepThrough;
    private String[] myColors;
    private List<Node> myButtons;

    public Clickable(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myGrid = grid;
        myAnimation = animation;
        myStepThrough = false;
        myColors = PAINT_COLORS[0];
        makeButtons();
    }

    private void addLoadingButtons() {
        for (int i = 0; i < SIMULATION_TYPES.length; i++) {
            for (int j = 0; j < 3; j++) {
                SimType s = SIMULATION_TYPES[i];
                int num = j;
                addButton(COLUMN_POSITION[2 + j], ROW_POSITION[1 + i], String.valueOf(j), e -> setGrid(s, num+1));
            }
        }
    }

    private void addTimelineButtons() {
        addButton(COLUMN_POSITION[0], ROW_POSITION[8], PAUSE_RESUME_LABEL, e -> pauseOrResume());
        addButton(COLUMN_POSITION[2], ROW_POSITION[8], RESTART_LABEL, e -> setGrid(myGrid.getType(), myGrid.getSimNum()));
        addButton(COLUMN_POSITION[0], ROW_POSITION[10], STEP_THROUGH_LABEL, e -> stepThrough());
    }


    private void addSpeeds() {
        Slider slider = new Slider(MIN_SPEED, MAX_SPEED, getSpeed());
        slider.setLayoutX(COLUMN_POSITION[0]);
        slider.setLayoutY(ROW_POSITION[13]);
        slider.setMajorTickUnit(.5);
        slider.setShowTickLabels(true);
        slider.setOnMouseClicked(e -> setSpeed(slider.getValue()));
        myButtons.add(slider);
    }

    private void addColorButtons() {
        for (int i = 0; i < 3; i++) {
            String[] paintColors = PAINT_COLORS[i];
            addButton(COLUMN_POSITION[0], ROW_POSITION[17 + i], COLOR_LABEL + i, e -> setColors(paintColors));
        }
    }

    void setColors(String[] paints) {
        myColors = paints;
    }

    String[] getColors() {
        return myColors;
    }


    private void addButton(int xpos, int ypos, String property, EventHandler<MouseEvent> handler) {
        Text result = new Text(xpos, ypos, myResources.getString(property));
        result.setOnMouseClicked(handler);
        myButtons.add(result);
    }

    private void setGrid(SimType simType, int simNum) {
        String gridName = simType.toString().toLowerCase() + FILE_MIDDLE_NAME + simNum + CSV_EXTENSION;
        myGrid = new Grid(gridName, myGrid.getArr(), myGrid.getShape(), myGrid.getEdge());
    }

    public Grid getGrid() {
        return myGrid;
    }

    private void makeButtons() {
        myButtons = new ArrayList<>();
        addLoadingButtons();
        addTimelineButtons();
        addSpeeds();
        addColorButtons();
    }

    List<Node> getButtons(){
        return myButtons;
    }

    private void pauseOrResume() {
        if (myAnimation.getStatus().equals(Animation.Status.RUNNING)) {
            myAnimation.pause();
        } else {
            myAnimation.play();
        }
    }

    private void stepThrough() {
        if (myStepThrough) {
            myAnimation.play();
        } else {
            myAnimation.pause();
        }
        myStepThrough = !myStepThrough;
    }

    private void setSpeed(double d) {
        myAnimation.setRate(d);
    }

    private double getSpeed() {
        return myAnimation.getRate();
    }

    boolean isStepThrough() {
        return myStepThrough;
    }

}



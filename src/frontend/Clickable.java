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

public class Clickable {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String SIDEBAR_RESOURCE = "SideBar";
    private static final int[] COLUMN_POSITION = {510, 610, 710, 740, 770};
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500};
    private static final Paint[][] PAINT_COLORS = {{Color.BLUE, Color.CYAN, Color.SKYBLUE}, {Color.RED, Color.MISTYROSE, Color.MAROON}};
    private static final SimType[] SIMULATION_TYPES = {SimType.FIRE, SimType.GOL, SimType.PERC, SimType.PP, SimType.RPS, SimType.SEG};

    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;
    private boolean myStepThrough;
    private Paint[] myColors;
    private boolean myImages;

    public Clickable(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myGrid = grid;
        myAnimation = animation;
        myStepThrough = false;
        myColors = PAINT_COLORS[0];
        myImages = true;
    }

    private void addLoadingButtons(List<Node> shapes) {
        for (int i = 0; i < SIMULATION_TYPES.length; i++) {
            shapes.add(new Text(COLUMN_POSITION[0], ROW_POSITION[1 + i], myResources.getString(SIMULATION_TYPES[i].toString())));
            for (int j = 0; j < 3; j++) {
                SimType s = SIMULATION_TYPES[i];
                int num = j;
                shapes.add(makeButton(COLUMN_POSITION[2 + j], ROW_POSITION[1 + i], String.valueOf(j), e -> setGrid(s, num+1)));
            }
        }
    }

    private void addTimelineButtons(List<Node> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[8], "PauseResumeButton", e -> pauseOrResume()));
        shapes.add(makeButton(COLUMN_POSITION[2], ROW_POSITION[8], "RestartButton", e -> setGrid(myGrid.getType(), myGrid.getSimNum())));
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[10], "StepThroughButton", e -> stepThrough()));
    }


    private void addSpeeds(List<Node> shapes) {
        Slider slider = new Slider(0, 2, getSpeed());
        slider.setLayoutX(COLUMN_POSITION[0]);
        slider.setLayoutY(ROW_POSITION[13]);
        slider.setMajorTickUnit(.5);
        slider.setShowTickLabels(true);
        slider.setOnMouseClicked(e -> setSpeed(slider.getValue()));
        //slider.setOnDragDetected(e -> setSpeed(slider, slider.getValue()));
        //slider.setOnDragOver(e -> setSpeed(slider, slider.getValue()));
        shapes.add(slider);
    }

    private void addColorButtons(List<Node> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[17], "Images", e -> setImages()));
        for (int i = 0; i < 2; i++) {
            Paint[] paintColors = PAINT_COLORS[i];
            shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[18 + i], "ColorScheme" + i, e -> setColors(paintColors)));
        }
    }

    void setColors(Paint[] paints) {
        myImages = false;
        myColors = paints;
    }

    Paint[] getColors() {
        return myColors;
    }

    private void setImages() {
        myImages = true;
    }

    boolean getImages() {
        return myImages;
    }

    private Text makeButton(int xpos, int ypos, String property, EventHandler<MouseEvent> handler) {
        Text result = new Text(xpos, ypos, myResources.getString(property));
        result.setOnMouseClicked(handler);
        return result;
    }

    private void setGrid(SimType simType, int simNum) {
        String gridName = simType.toString().toLowerCase() + "-grid-" + simNum + ".csv";
        myGrid = new Grid(gridName); //issues...
    }

    public Grid getGrid() {
        return myGrid;
    }

    List<Node> getButtons() {
        List<Node> buttons = new ArrayList<>();
        addLoadingButtons(buttons);
        addTimelineButtons(buttons);
        addSpeeds(buttons);
        addColorButtons(buttons);
        return buttons;
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



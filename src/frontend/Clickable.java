package frontend;

import Enums.Shape;
import Enums.SimType;
import grid.Grid;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Clickable implements DisplayObject{
    private static final String PAUSE_RESUME_LABEL = "PauseResumeButton";
    private static final String RESTART_LABEL = "RestartButton";
    private static final String STEP_THROUGH_LABEL = "StepThroughButton";
    private static final String COLOR_LABEL = "ColorScheme";
    private static final String SHAPE_LABEL = "Shape";
    private static final int MIN_SPEED = 0;
    private static final int MAX_SPEED = 2;
    private static final int NUM_SHAPES = 4;
    private static final String[][] PAINT_COLORS = {{"BLUE", "CYAN", "SKYBLUE"}, {"RED", "MISTYROSE", "MAROON"}, {"GREEN", "DARKGREEN", "LIGHTGREEN"}};

    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;
    private boolean myStepThrough;
    private String[] myColors;
    private List<Node> myButtons;
    private Shape myShape;
    private String myPropertyName;

    public Clickable(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle(RESOURCES);
        myGrid = grid;
        myAnimation = animation;
        myStepThrough = false;
        myColors = PAINT_COLORS[0];
        myShape = grid.getShape();
        setObjects();
    }

    private void addLoadingButtons() {
        for (int i = 0; i < SIMULATION_TYPES.length; i++) {
            for (int j = 0; j < 3; j++) {
                SimType s = SIMULATION_TYPES[i];
                int num = j;
                addButton(COLUMN_POSITION[2 + j], ROW_POSITION[1 + i], String.valueOf(j), e -> setGrid(s, num + 1));
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
        for (int i = 0; i < myColors.length; i++) {
            String[] paintColors = PAINT_COLORS[i];
            addButton(COLUMN_POSITION[0], ROW_POSITION[17 + i], COLOR_LABEL + i, e -> setColors(paintColors));
        }
    }

    private void addShapeButtons() {
        for (int i = 0; i < NUM_SHAPES; i++) {
            int num = i;
            addButton(COLUMN_POSITION[0], ROW_POSITION[22 + i], SHAPE_LABEL + i, e -> changeShape(num));
        }
    }

    private void changeShape(int i) {
        myShape = Shape.valueOf(myResources.getString(SHAPE_LABEL + i).toUpperCase());
    }

    public Shape getShape() {
        return myShape;
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
        myGrid = null;
        myPropertyName = simType.toString() + simNum;
    }

    public String getPropertyName(){
        return myPropertyName;
    }

    public Grid getGrid() {
        return myGrid;
    }

    public void setObjects() {
        myButtons = new ArrayList<>();
        addLoadingButtons();
        addTimelineButtons();
        addSpeeds();
        addColorButtons();
        addShapeButtons();
    }

    public List<Node> getObjects() {
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



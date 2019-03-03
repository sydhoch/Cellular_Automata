import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserInteraction {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String SIDEBAR_RESOURCE = "SideBar";
    private static final int[] COLUMN_POSITION = {510, 610, 710, 740, 770};
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500};
    private static final Paint[][] PAINT_COLORS = {{Color.BLUE, Color.CYAN, Color.SKYBLUE}, {Color.RED, Color.MISTYROSE, Color.MAROON}};
    private static final Double[] SPEED = {.5, 1.0, 2.0};
    private static final SimType[] SIMULATION_TYPES = {SimType.FIRE, SimType.GOL, SimType.PERC, SimType.PP, SimType.RPS, SimType.SEG};

    private List<Shape> myButtons;
    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;
    private boolean myStepThrough;
    private Paint[] myColors;
    private boolean myImages;

    public UserInteraction(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myGrid = grid;
        myAnimation = animation;
        myButtons = setButtons();
        myStepThrough = false;
        myColors = PAINT_COLORS[0];
        myImages = true;
    }

    private List<Shape> setButtons() {
        List<Shape> buttons = new ArrayList<>();
        addLoadingButtons(buttons);
        addTimelineButtons(buttons);
        addSpeedButtons(buttons);
        addColorButtons(buttons);
        addSimInfo(buttons);
        return buttons;
    }

    private void addLoadingButtons(List<Shape> shapes) {
        shapes.add(new Text(COLUMN_POSITION[0], ROW_POSITION[0], "LoadLabel"));
        for (int i = 0; i < SIMULATION_TYPES.length; i++) {
            shapes.add(new Text(COLUMN_POSITION[0], ROW_POSITION[1 + i], SIMULATION_TYPES[i].toString()));
            for (int j = 0; j < 3; j++) {
                int type = i;
                int num = j;
                shapes.add(makeButton(COLUMN_POSITION[2 + j], ROW_POSITION[1 + i], String.valueOf(j), e -> setGrid(type, num)));
            }
        }
    }

    private void addTimelineButtons(List<Shape> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[8], "PauseResumeButton", e -> pauseOrResume()));
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[10], "StepThroughButton", e -> stepThrough()));
    }


    private void addSpeedButtons(List<Shape> shapes) {
        shapes.add(new Text(COLUMN_POSITION[0], ROW_POSITION[12], "SpeedLabel"));
        for (int i = 0; i < 3; i++) {
            int speed = i;
            shapes.add(makeButton(COLUMN_POSITION[i], ROW_POSITION[13], "Speed" + String.valueOf(i), e -> setSpeed(speed)));
        }
    }

    private void addColorButtons(List<Shape> shapes) {
        shapes.add(new Text(COLUMN_POSITION[0], ROW_POSITION[15], "ColorLabel"));
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[16], "Images", e -> setImages()));
        for (int i = 0; i < 2; i++) {
            Paint[] paintColors = PAINT_COLORS[i];
            shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[17 + i], "ColorScheme" + String.valueOf(i), e -> setColors(paintColors)));
        }
    }

    private void addSimInfo(List<Shape> shapes){
        for(int i = 0; i < 3; i++) {
            shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[20+i], myGrid.getType().toString() + i, null));
            shapes.add(new Text(COLUMN_POSITION[3], ROW_POSITION[20+i], String.valueOf(myGrid.getCellsInState(i).size())));
        }
    }

    public void setColors(Paint[] paints) {
        myImages = false;
        myColors = paints;
    }

    public Paint[] getColors() {
        return myColors;
    }

    private void setImages() {
        myImages = true;
    }

    public boolean getImages() {
        return myImages;
    }

    private Text makeButton(int xpos, int ypos, String property, EventHandler<MouseEvent> handler) {
        Text result = new Text(xpos, ypos, myResources.getString(property));
        if (handler != null) {
            result.setOnMouseClicked(handler);
        }
        return result;
    }

    private void setGrid(int simType, int simNum) {
        String gridName = SIMULATION_TYPES[simType].toString().toLowerCase() + "-grid-" + String.valueOf(simNum+1) + ".csv";
            myGrid = new Grid(gridName);
    }

    public Grid getGrid() {
        return myGrid;
    }


    public List<Shape> getButtons() {
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

    private void setSpeed(int i) {
        myAnimation.setRate(SPEED[i]);
    }

    public boolean isStepThrough() {
        return myStepThrough;
    }
}



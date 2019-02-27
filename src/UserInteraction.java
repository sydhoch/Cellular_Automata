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
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360};
    private static final Paint[][] PAINT_COLORS = {{Color.BLUE, Color.CYAN, Color.SKYBLUE}, {Color.GOLD, Color.PURPLE, Color.ROYALBLUE}, {Color.PALEGREEN, Color.LIGHTSALMON, Color.LIGHTGOLDENRODYELLOW}};
    private static final Double[] SPEED = {.5, 1.0, 2.0};
    private static final String[] SIMULATION_TYPES = {"Fire", "GoL", "Perc", "RPS", "Seg"};

    private List<Shape> myButtons;
    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;
    private boolean myStepThrough;
    private Paint[] myColors;

    public UserInteraction(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myGrid = grid;
        myAnimation = animation;
        myButtons = setButtons();
        myStepThrough = false;
        myColors = PAINT_COLORS[0];
    }

    private List<Shape> setButtons() {
        List<Shape> buttons = new ArrayList<>();
        addLoadingButtons(buttons);
        addTimelineButtons(buttons);
        addSpeedButtons(buttons);
        addColorButtons(buttons);
        return buttons;
    }

    private void addLoadingButtons(List<Shape> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[0], "LoadLabel", null));
        for (int i = 0; i < 5; i++) {
            shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[1 + i], SIMULATION_TYPES[i], null));
            for (int j = 0; j < 3; j++) {
                int type = i;
                int num = j;
                shapes.add(makeButton(COLUMN_POSITION[2 + j], ROW_POSITION[1 + i], String.valueOf(j), e -> setGrid(type, num)));
            }
        }
    }

    private void addTimelineButtons(List<Shape> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[7], "PauseResumeButton", e -> pauseOrResume()));
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[9], "StepThroughButton", e -> stepThrough()));
    }


    private void addSpeedButtons(List<Shape> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[11], "SpeedLabel", null));
        for (int i = 0; i < 3; i++) {
            int speed = i;
            shapes.add(makeButton(COLUMN_POSITION[i], ROW_POSITION[12], "Speed" + String.valueOf(i), e -> setSpeed(speed)));
        }
    }

    private void addColorButtons(List<Shape> shapes) {
        shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[14], "ColorLabel", null));
        for (int i = 0; i < 3; i++) {
            Paint[] paintColors = PAINT_COLORS[i];
            shapes.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[15 + i], "ColorScheme" + String.valueOf(i), e -> setColors(paintColors)));
        }
    }

    private void setColors(Paint[] paints) {
        myColors = paints;
    }

    public Paint[] getColors() {
        return myColors;
    }

    private Text makeButton(int xpos, int ypos, String property, EventHandler<MouseEvent> handler) {
        Text result = new Text(xpos, ypos, myResources.getString(property));
        if (handler != null) {
            result.setOnMouseClicked(handler);
        }
        return result;
    }

    private void setGrid(int simType, int simNum) {
        String gridName = SIMULATION_TYPES[simType].toLowerCase() + "-grid-" + String.valueOf(simNum) + ".csv";
        myGrid = new Grid(gridName); //doesn't work (need to stop animation?)
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

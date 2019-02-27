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
    private static final int[] COLUMN_POSITION = {510, 610, 710};
    private static final int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360};
    private static final Paint[][] PAINT_COLORS = {{Color.BLUE, Color.CYAN, Color.SKYBLUE}, {Color.GOLD, Color.PURPLE, Color.ROYALBLUE}, {Color.PALEGREEN, Color.LIGHTSALMON, Color.LIGHTGOLDENRODYELLOW}};
    private static final Double[] SPEED = {.5, 1.0, 2.0};

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

    private List<Shape> setButtons(){
        List<Shape> buttons = new ArrayList<>();
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[0], "LoadLabel", null));
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[1],"GoLSim1", e-> setGrid()));
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[2],"GoLSim2", e-> setGrid()));
        //add rest of load buttons
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[5], "PauseResumeButton", e -> pauseOrResume()));
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[7], "StepThroughButton", e -> stepThrough()));
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[9], "SpeedLabel", null));
        for(int i = 0; i < 3; i++) {
            int speed = i;
            buttons.add(makeButton(COLUMN_POSITION[i], ROW_POSITION[10], "Speed" + String.valueOf(i), e -> setSpeed(speed)));
        }
        buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[12], "ColorLabel", null));
        for(int i = 0; i < 3; i++){
            Paint[] paintColors = PAINT_COLORS[i];
            buttons.add(makeButton(COLUMN_POSITION[0], ROW_POSITION[13+i], "ColorScheme" + String.valueOf(i), e -> setColors(paintColors)));
        }
        return buttons;
    }

    private void setColors(Paint[] paints){
        myColors = paints;
    }

    public Paint[] getColors(){
        return myColors;
    }

    private Text makeButton (int xpos, int ypos, String property, EventHandler<MouseEvent> handler) {
        Text result = new Text(xpos, ypos, myResources.getString(property));
        if(handler != null) {
            result.setOnMouseClicked(handler);
        }
        return result;
    }

    private void setGrid(){
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.getExtensionFilters().add(
//                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
        myGrid = new Grid("gol-grid-1.csv"); //doesn't work (need to stop animation?)
    }

    public Grid getGrid(){
        return myGrid;
    }


    public List<Shape> getButtons(){
        return myButtons;
    }

    private void pauseOrResume(){
        if(myAnimation.getStatus().equals(Animation.Status.RUNNING)){
            myAnimation.pause();
        }
        else {
            myAnimation.play();
        }
    }

    private void stepThrough(){
        if(myStepThrough){
            myAnimation.play();
        }
        else {
            myAnimation.pause();
        }
        myStepThrough = !myStepThrough;
    }

    private void setSpeed(int i){
        myAnimation.setRate(SPEED[i]);
    }

    public boolean isStepThrough(){
        return myStepThrough;
    }

}



























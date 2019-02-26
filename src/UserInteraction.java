import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.SPACE;

public class UserInteraction {
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String SIDEBAR_RESOURCE = "SideBar";
    private static final int[] colPos = {510, 610, 710};
    private static final int[] rowPos = {20, 40, 60, 80, 100, 120, 140, 160, 180};


    private List<Shape> myButtons;
    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;
    private boolean myStepThrough;

    public UserInteraction(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SIDEBAR_RESOURCE);
        myGrid = grid;
        myAnimation = animation;
        myButtons = setButtons();
        myStepThrough = false;
    }

    private List<Shape> setButtons(){
        List<Shape> buttons = new ArrayList<>();
        buttons.add(makeButton(colPos[0], rowPos[0], "LoadLabel", null));
        buttons.add(makeButton(colPos[0], rowPos[1],"GoLSim1", e-> setGrid()));
        buttons.add(makeButton(colPos[0], rowPos[2],"GoLSim2", e-> setGrid()));
        //add rest of load buttons
        buttons.add(makeButton(colPos[0], rowPos[5], "PauseResumeButton", e -> pauseOrResume()));
        buttons.add(makeButton(colPos[1], rowPos[5], "StepThroughButton", e -> stepThrough()));
        buttons.add(makeButton(colPos[0], rowPos[7], "SpeedLabel", null));
        buttons.add(makeButton(colPos[0], rowPos[8], "HalfSpeed", e -> slowDown()));
        buttons.add(makeButton(colPos[1], rowPos[8], "NormalSpeed", e -> resetSpeed()));
        buttons.add(makeButton(colPos[2], rowPos[8], "DoubleSpeed", e -> speedUp()));
        //add color+image views
        return buttons;
    }

    private Text makeButton (int xpos, int ypos, String property, EventHandler<MouseEvent> handler) {
        Text result = new Text(xpos, ypos, myResources.getString(property));
        if(handler != null) {
            result.setOnMouseClicked(handler);
        }
        return result;
    }

    private void setGrid(){
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

    private void speedUp(){
        myAnimation.setRate(2);
    }

    private void slowDown(){
        myAnimation.setRate(.5);
    }

    private void resetSpeed(){
        myAnimation.setRate(1);
    }

    public boolean isStepThrough(){
        return myStepThrough;
    }

}



























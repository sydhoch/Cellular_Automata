import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserInteraction {
    private List<Shape> myButtons;
    private Grid myGrid;
    private Timeline myAnimation;
    private ResourceBundle myResources;

    public UserInteraction(Grid grid, Timeline animation) {
        myResources = ResourceBundle.getBundle("SideBar");
        myGrid = grid;
        myAnimation = animation;
        myButtons = new ArrayList<>();
        Text load = makeButton(510, 20, "Load File:", null);
        Text file1 = makeButton(510, 40,"GoLSim1", e-> setGrid());
        myButtons.add(load);
        myButtons.add(file1);
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

    public void pause(){
        myAnimation.pause();
    }

    public void resume(){
        myAnimation.play();
    }

    public void stepThrough(){

    }

    public void speedUp(){
        myAnimation.setRate(2);
    }

    public void slowDown(){
        myAnimation.setRate(.5);
    }

    public void resetSpeed(){
        myAnimation.setRate(1);
    }
}



























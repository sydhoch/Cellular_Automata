import cell.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

import static javafx.scene.input.KeyCode.SPACE;


public class Play extends Application {



    private static final Paint BACKGROUND = Color.GREY;
    private static final String FILE_NAME = "gol-grid-2.csv";
    private static final String TITLE = "Cell Simulation";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int SIM_SIZE = 500;
    private static final int WINDOW_WIDTH = SIM_SIZE + 300;
    private static Paint ZERO_COLOR = Color.BLUE;
    private static Paint ONE_COLOR = Color.RED;
    private static Paint TWO_COLOR = Color.YELLOW;

    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;
    private boolean stepThroughMode;
    private Timeline myAnimation;



    public void start(Stage stage) {
        myGrid = new Grid(FILE_NAME);
        myRoot = new Group();
        myScene = setUpGame(WINDOW_WIDTH, SIM_SIZE, BACKGROUND);
        stepThroughMode = false;
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private Scene setUpGame(int width, int height, Paint background) {
        Scene scene = new Scene(myRoot, width, height, background);
        displayStates();
        return scene;
    }

    private void displayStates() {
        myRoot.getChildren().clear();
        setButtons();
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myRoot.getChildren().add(setRectangle(i, j));
            }
        }
    }

    private void setButtons(){

    }

    private void load(){
        myGrid = new Grid("");
    }

    private void pause(){
        myAnimation.pause();
    }

    private void resume(){
        myAnimation.play();
    }

    private void stepThrough(){

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

    private Rectangle setRectangle(int i, int j) {
        int cellHeight = SIM_SIZE /myGrid.getHeight();
        int cellWidth = SIM_SIZE /myGrid.getWidth();
        Rectangle ret = new Rectangle(cellHeight*i, cellWidth*j, cellHeight, cellWidth);
        ret.setFill(setColor(myGrid.getCell(i, j).getState()));
        return ret;
    }

    private Paint setColor(int state){
        if(state==0){
            return ZERO_COLOR;
        }
        else if(state==1){
           return ONE_COLOR;
        }
        else{
            return TWO_COLOR;
        }

    }


    private void step(double elapsedTime) {
        setNextStates();
        updateStates();
        displayStates();
    }

    private void setNextStates() {
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                Cell[] neighbors = myGrid.setNeighbors(i, j);
                myGrid.getCell(i, j).checkNeighborStatus(neighbors);
            }
        }
    }

    private void updateStates() {
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myGrid.getCell(i, j).updateCell();
            }
        }
    }

    private void handleKeyInput(KeyCode code) {
        if(code.equals(SPACE)){

        }
    }


}


import cell.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import static javafx.scene.input.KeyCode.SPACE;


public class Play {


    private static final Paint BACKGROUND = Color.GREY;
    private static final String FILE_NAME = "pp-grid-1.csv";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int SIM_SIZE = 500;
    private static final int WINDOW_WIDTH = SIM_SIZE + 300;
    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String STYLESHEET = "default.css";


    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;
    private Timeline myAnimation;
    private UserInteraction mySideBar;
    private Paint[] myColors;


    public Play() {
        GridMaker maker = new GridMaker(FILE_NAME);
        if(maker.getGameType().equals("Seg")){
            myGrid = new SegGrid(FILE_NAME);
        }
        if(maker.getGameType().equals("PP")){
            myGrid = new PPGrid(FILE_NAME);
        }
        else{
            myGrid = new Grid(FILE_NAME);
        }
        myRoot = new Group();
        myScene = setUpGame(WINDOW_WIDTH, SIM_SIZE, BACKGROUND);
        myAnimation = new Timeline();
        mySideBar = new UserInteraction(myGrid, myAnimation);
        displayStates();
    }

    public Scene getScene(){
        return myScene;
    }

    public void startAnimation(){
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private Scene setUpGame(int width, int height, Paint background) {
        Scene scene = new Scene(myRoot, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        //System.out.println(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + STYLESHEET));//.toExternalForm());
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + STYLESHEET).toExternalForm());
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

    private void setButtons() {
        myRoot.getChildren().addAll(mySideBar.getButtons());
        myGrid = mySideBar.getGrid();
        myColors = mySideBar.getColors();
    }

    private Rectangle setRectangle(int i, int j) {
        int cellHeight = SIM_SIZE / myGrid.getHeight();
        int cellWidth = SIM_SIZE / myGrid.getWidth();
        Rectangle ret = new Rectangle(cellHeight * i, cellWidth * j, cellHeight, cellWidth);
        ret.setFill(setCellColor(myGrid.getCell(i, j).getState()));
        return ret;
    }

    private Paint setCellColor(int state) {
        return myColors[state];
    }


    protected void step(double elapsedTime) {
        myGrid.setNextStates();
        myGrid.updateStates();
        displayStates();
    }



    private void handleKeyInput(KeyCode code) {
        if (code.equals(SPACE) && mySideBar.isStepThrough()) {
            step(0);
        }
    }


}


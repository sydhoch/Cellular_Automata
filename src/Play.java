import cell.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Play extends Application {


    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.GREY;
    private static final String FILE_NAME = "perc-grid-2.csv";
    private static final String TITLE = "Cell Simulation";
    private static final int WINDOW_SIZE = 500;
    private static Paint ZERO_COLOR = Color.WHITE;
    private static Paint ONE_COLOR = Color.BLUE;
    private static Paint TWO_COLOR = Color.BLACK;

    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;


    public void start(Stage stage) {
        myGrid = new Grid(FILE_NAME);
        myRoot = new Group();
        myScene = setUpGame(WINDOW_SIZE, WINDOW_SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setUpGame(int width, int height, Paint background) {
        Scene scene = new Scene(myRoot, width, height, background);
        displayStates();
        return scene;
    }

    private void displayStates() {
        myRoot.getChildren().clear();
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myRoot.getChildren().add(setRectangle(i, j));
            }
        }
    }

    private Rectangle setRectangle(int i, int j) {
        int cellHeight = WINDOW_SIZE/myGrid.getHeight();
        int cellWidth = WINDOW_SIZE/myGrid.getWidth();
        Rectangle ret = new Rectangle(cellHeight*i, cellWidth*j, cellHeight, cellWidth);
        ret.setFill(setCellColor(myGrid.getCell(i, j).getState()));
        return ret;
    }

    private Paint setCellColor(int state){
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
}


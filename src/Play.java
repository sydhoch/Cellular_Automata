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
    private static final String FILE_NAME = "gol-grid-2.csv";
    private static final String TITLE = "Cell Simulation";
    private static final int CELL_SIZE = 100;

    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;


    public void start(Stage stage) {
        myGrid = new Grid(FILE_NAME);
        myRoot = new Group();
        myGrid.getGrid(FILE_NAME);
        myScene = setUpGame(myGrid.getWidth() * CELL_SIZE, myGrid.getHeight() * CELL_SIZE, BACKGROUND);
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
                myRoot.getChildren().add(myGrid.getCell(i, j).getRectangle());
            }
        }
    }

    private void step(double elapsedTime) {
        setAndUpdateStates();
        displayStates();
    }



    public void setAndUpdateStates() {
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                Cell[] neighbors = myGrid.setNeighbors(i, j);
                myGrid.getCell(i, j).checkNeighborStatus(neighbors);
            }
        }
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myGrid.getCell(i, j).updateCell();
            }
        }
    }
}

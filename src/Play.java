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


public abstract class Play extends Application {

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.GREY;
    private static final String FILE_NAME = " ";
    private static final String TITLE = "Cell Simulation";
    private static final int CELL_SIZE = 10;


    private Grid myGrid;
    private Scene myScene;
    private Group myRoot;

    public void start(Stage stage){
        myGrid = new Grid(FILE_NAME);
        myRoot = new Group();
        myScene = setupGame(myGrid.getWidth()*CELL_SIZE, myGrid.getHeight()*CELL_SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setUpGame(int width, int height, Paint background){
        Scene scene = new Scene(myRoot, width, height, background);
        displayStates();
        return scene;
    }

    private void displayStates(){
        myRoot.getChildren().removeAll();
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                Rectangle r = new Rectangle(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                r.setFill(myGrid.getCell(i, j).getColor());
                myRoot.getChildren().add(r);
            }
        }
    }

    private void step(double elapsedTime){
        setAndUpdateStates(myGrid);
        displayStates();
        if(checkEnd(myGrid)){
           //end game
        }
    }

    public abstract void setAndUpdateStates(Grid grid);
    public abstract int [] setNeighbors(int row, int col, Grid grid);
    public abstract boolean checkEnd(Grid grid);

    public static void main(String[] args) {
        launch(args);
    }

}

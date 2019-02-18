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

import java.util.Arrays;
import java.util.Scanner;


public abstract class Play extends Application {

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.GREY;
    private static final String FILE_NAME = "gol-grid-.csv";
    private static final String TITLE = "Cell Simulation";
    private static final int CELL_SIZE = 10;


    private int gridWidth;
    private int gridHeight;
    private Scene myScene;
    private Group myRoot;
    private Cell[][] myGrid;


    //going to be called by the createCellGrid method
    public int[][] readFile(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        s.useDelimiter(",");
        var gameType = s.next();
        System.out.println(gameType);
        s.nextLine();
        gridWidth = s.nextInt();
        System.out.println(gridWidth);
        gridHeight = s.nextInt();
        System.out.println(gridHeight);
        int[][] myGrid = new int[gridWidth][gridHeight];
        s.nextLine();
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                myGrid[i][j] = s.nextInt();
            }
            s.nextLine();
        }
        return myGrid;
    }

    public void start(Stage stage){
        myGrid = new Grid();
        myRoot = new Group();
        myGrid.getGrid(FILE_NAME);
        myScene = setUpGame(myGrid.getWidth()*CELL_SIZE, myGrid.getHeight()*CELL_SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
      //  var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
       // animation.getKeyFrames().add(frame);
        animation.play();
    }

    private Scene setUpGame(int width, int height, Paint background){
        Scene scene = new Scene(myRoot, width, height, background);
    //    displayStates();
        return scene;
    }
/*

    private void displayStates(){
        myRoot.getChildren().removeAll();
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                Rectangle r = new Rectangle(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                r.setFill(myGrid[i][j].getColor());
                myRoot.getChildren().add(r);
            }
        }
    }

    private void step(double elapsedTime){
        setAndUpdateStates();
        displayStates();
        if(checkEnd(myGrid)){
           //end game
        }
    }
*/

    public Cell [] setNeighbors(int row, int col){
//        int gridTop = 0;
//        int gridBottom = gridHeight-1;
//        int gridLeft = 0;
//        int gridRight = gridHeight-1;
//
//        Cell[] neighbors = new int[8];
//        //neighbors[0]
//        if(row == gridTop && col == gridLeft){
//           neighbors[0] = myGrid[gridBottom][gridRight].getState();
//
//        }
//        else if(row == gridTop){
//
//        }
//
//        return neighbors;
        return null;
    }

    public void setAndUpdateStates() {
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                Cell[] neighbors = setNeighbors(i, j);
                myGrid[i][j].checkNeighborStatus(neighbors);
                myGrid[i][j].updateState();
            }
        }
    }

    public abstract Cell[][] createCellGrid(String file);

    public abstract boolean checkEnd(Cell[][] grid);

    public static void main(String[] args) {
        launch(args);
    }

}

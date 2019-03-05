package frontend;

import Enums.SimType;
import grid.Grid;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.SPACE;


public class Play {
    private static final String FILE_NAME = "perc-grid-1.csv";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int SIM_SIZE = 500;
    private static final int WINDOW_SIZE = SIM_SIZE + 300;

    private static final String DEFAULT_RESOURCE_PACKAGE = "/Resources/";
    private static final String STYLESHEET = "default.css";
    private static final String IMAGES_RESOURCE = "Images";
    private static final String IMAGE_FOLDER = "images/";
    private static final String SETTINGS = "Test";


    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;
    private Timeline myAnimation;
    private SideBar mySideBar;
    private Paint[] myColors;
    private boolean myImage;
    private int myCellHeight;
    private int myCellWidth;
    private BottomGraph myBottomGraph;
    private ResourceBundle myImages;
    private ResourceBundle mySettings;
    private int myNumSteps;


    public Play() {
        myGrid = new Grid(FILE_NAME);
        myRoot = new Group();
        myScene = setUpGame(WINDOW_SIZE, WINDOW_SIZE);
        myAnimation = new Timeline();
        mySideBar = new SideBar(myGrid, myAnimation);
        myBottomGraph = new BottomGraph(myGrid);
        myImages = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + IMAGES_RESOURCE);
        mySettings = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SETTINGS);
        myNumSteps = 1;
        setButtons();
        setDefaultImages();
        displayStates();
    }

    public Scene getScene() {
        return myScene;
    }

    public void startAnimation() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private Scene setUpGame(int width, int height) {
        Scene scene = new Scene(myRoot, width, height);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.setOnMouseClicked(e -> updateButtons());
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + STYLESHEET).toExternalForm());
        return scene;
    }

    private void displayStates() {
        removeFromScreen(new Rectangle());
        removeFromScreen(new ImageView());
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myRoot.getChildren().add(setView(i, j));
            }
        }
    }

    private void removeFromScreen(Node remove) {
        List<Node> toRemove = new ArrayList<>();
        for (Node n : myRoot.getChildren()) {
            if (n.getClass().equals(remove.getClass())) {
                toRemove.add(n);

            }
        }
        myRoot.getChildren().removeAll(toRemove);
    }

    private void setButtons() {
        myRoot.getChildren().addAll(mySideBar.getButtons());
        myRoot.getChildren().add(myBottomGraph.getGraph());
        updateButtons();
    }

    private void updateButtons(){
        myGrid = mySideBar.getGrid();
        myImage = mySideBar.getImages();
        myNumSteps = 1;
        myBottomGraph = new BottomGraph(myGrid);
        removeFromScreen(myBottomGraph.getGraph());
        myRoot.getChildren().add(myBottomGraph.getGraph());
    }

    private Node setView(int i, int j) {
        myCellWidth = SIM_SIZE / myGrid.getHeight();
        myCellHeight = SIM_SIZE / myGrid.getWidth();
        Node n;
        if (myImage) {
            n = setImage(i, j);
        } else {
            myColors = mySideBar.getColors();
            n = setRectangle(i, j);
        }
        n.setOnMouseClicked(e -> changeCellState(i, j));
        return n;
    }

    private void changeCellState(int row, int col) {
        if (myAnimation.getStatus().equals(Animation.Status.PAUSED)) {
            int currState = myGrid.getCell(row, col).getState();
            int nextState;
            if (myGrid.getType() == SimType.GOL) {
                if (currState == 0) {
                    nextState = 1;
                } else {
                    nextState = 0;
                }
            } else {
                if (currState == 0 || currState == 1) {
                    nextState = currState + 1;
                } else {
                    nextState = 0;
                }
            }
            myGrid.updateStates();
            myGrid.getCell(row, col).setNextState(nextState);
            displayStates();
        }
    }

    private void setDefaultImages() {
        myImage = !mySettings.containsKey("Color0");
        Paint[] userColors = new Paint[3];
        for (int i = 0; i < 3; i++) {
            if (mySettings.containsKey("Color" + i)) {
                userColors[i] = Paint.valueOf(mySettings.getString("Color" + i));
            }
        }
        mySideBar.setColors(userColors);
    }

    private Rectangle setRectangle(int i, int j) {
        Rectangle rect = new Rectangle(myCellWidth * i, myCellHeight * j, myCellWidth, myCellHeight);
        rect.setFill(myColors[myGrid.getCell(i, j).getState()]);
        return rect;
    }

    private ImageView setImage(int i, int j) {
        String image_file = IMAGE_FOLDER + myImages.getString(myGrid.getType().toString() + myGrid.getCell(i, j).getState());
        System.out.println(image_file);
        Image preImage = new Image(this.getClass().getClassLoader().getResourceAsStream(image_file));
        ImageView img = new ImageView(preImage);
        img.setX(myCellWidth * i);
        img.setY(myCellHeight * j);
        img.setFitWidth(myCellWidth);
        img.setFitHeight(myCellHeight);
        return img;
    }

    private void step(double elapsedTime) {
        myGrid.setNextStates();
        myGrid.updateStates();
        myBottomGraph.updateGraph(myNumSteps);
        displayStates();
        myNumSteps++;
    }


    private void handleKeyInput(KeyCode code) {
        if (code.equals(SPACE) && mySideBar.isStepThrough()) {
            step(0);
        }
    }

}


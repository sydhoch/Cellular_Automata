package frontend;

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

    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int SIM_SIZE = 500;
    private static final int WINDOW_SIZE = SIM_SIZE + 300;

    private static final String DEFAULT_RESOURCE_PACKAGE = "/Resources/";
    private static final String STYLESHEET = "default.css";
    private static final String IMAGES_RESOURCE = "Images";
    private static final String IMAGE_FOLDER = "images/";
    private static final String CONFIGURATION_FILE = "Test";
    private static final String FILE_CONFIG_LABEL = "CSVFileName";
    private static final String NEIGHBOORHOD_CONFIG_LABEL = "NeighborhoodType";
    private static final String CELLSHAPE_CONFIG_LABEL = "CellShape";
    private static final String EDGE_CONFIG_LABEL = "EdgePolicies";
    private static final String COLOR_LABEL = "Color";
    private static final int STEP_COUNT_START = 1;
    private static final int MAX_STATES= 3;

    private String fileName;
    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;
    private Timeline myAnimation;
    private Clickable mySideBar;
    private StagnantLabels myLabels;
    private Paint[] myColors;
    private boolean myImage;
    private int myCellHeight;
    private int myCellWidth;
    private GridGraph myGridGraph;
    private ResourceBundle myImages;
    private ResourceBundle myConfiguration;
    private int myNumSteps;


    public Play() {
        myImages = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + IMAGES_RESOURCE);
        myConfiguration = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + CONFIGURATION_FILE);
        fileName = myConfiguration.getString(FILE_CONFIG_LABEL);
        String neighborhoodType = myConfiguration.getString(NEIGHBOORHOD_CONFIG_LABEL);
        String cellShape = myConfiguration.getString(CELLSHAPE_CONFIG_LABEL);
        String edgePolicy = myConfiguration.getString(EDGE_CONFIG_LABEL);
        myGrid = new Grid(fileName, neighborhoodType, cellShape, edgePolicy);
        myRoot = new Group();
        myScene = setUpGame(WINDOW_SIZE, WINDOW_SIZE);
        myAnimation = new Timeline();
        mySideBar = new Clickable(myGrid, myAnimation);
        myLabels = new StagnantLabels();
        myGridGraph = new GridGraph(myGrid);
        myNumSteps = STEP_COUNT_START;
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
        myRoot.getChildren().addAll(myLabels.getLabels());
        myRoot.getChildren().addAll(mySideBar.getButtons());
        myRoot.getChildren().add(myGridGraph.getGraph());
        updateButtons();
    }

    private void updateButtons() {
        if (myGrid != mySideBar.getGrid()) {
            myGrid = mySideBar.getGrid();
            myGridGraph = new GridGraph(myGrid);
            removeFromScreen(myGridGraph.getGraph());
            myRoot.getChildren().add(myGridGraph.getGraph());
        }
        myImage = mySideBar.getImages();
        myNumSteps = STEP_COUNT_START;
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
            int lastState = myGrid.getType().getNumStates()-1;
            if (currState == lastState) {
                nextState = 0;
            } else {
                nextState = currState + 1;
            }
            myGrid.updateStates();
            myGrid.getCell(row, col).setNextState(nextState);
            displayStates();
        }
    }

    private void setDefaultImages() {
        myImage = !myConfiguration.containsKey(COLOR_LABEL + 0);
        Paint[] userColors = new Paint[MAX_STATES];
        for (int i = 0; i < userColors.length; i++) {
            if (myConfiguration.containsKey(COLOR_LABEL + i)) {
                userColors[i] = Paint.valueOf(myConfiguration.getString(COLOR_LABEL + i));
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
        myGridGraph.updateGraph(myNumSteps);
        displayStates();
        myNumSteps++;
    }


    private void handleKeyInput(KeyCode code) {
        if (code.equals(SPACE) && mySideBar.isStepThrough()) {
            step(0);
        }
    }

}


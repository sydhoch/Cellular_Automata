/**
 * This class runs the simulation. It depends on all of the cell, grid, frontend classes, and the resource bundles.
 * It catches exceptions in the configuration files and sets default values instead.
 *
 * @author Sara Behn
 * @author Sydney Hochberg
 * @author Arilia Frederick
 */
package frontend;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import Exceptions.InvalidValueException;
import grid.Grid;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

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
    private static final String CONFIGURATION_FILE = "Fire";
    private static final String FILE_CONFIG_LABEL = "CSVFileName";
    private static final String NEIGHBORHOOD_CONFIG_LABEL = "NeighborhoodType";
    private static final String CELL_SHAPE_CONFIG_LABEL = "CellShape";
    private static final String EDGE_CONFIG_LABEL = "EdgePolicies";
    private static final int STEP_COUNT_START = 1;
    private static final int MAX_STATES = 3;
    private static final String COLOR_LABEL = "Color";

    private String[] myColors;
    private String myFileName;
    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;
    private Timeline myAnimation;
    private Clickable mySideBar;
    private StagnantLabels myLabels;
    private GridGraph myGridGraph;
    private DisplayObject[] myDisplayObjects;
    private ResourceBundle myConfiguration;
    private int myNumSteps;
    private Shape myShape;
    private CellDisplay myCellDisplay;


    protected Play() {
        try {
            readConfigFile();
        } catch (InvalidValueException e) {
            e.printStackTrace();
        }
        myRoot = new Group();
        myScene = setUpGame(WINDOW_SIZE, WINDOW_SIZE);
        myAnimation = new Timeline();
        mySideBar = new Clickable(myGrid, myAnimation);
        myLabels = new StagnantLabels();
        myGridGraph = new GridGraph(myGrid);
        myDisplayObjects = new DisplayObject[]{mySideBar, myLabels, myGridGraph};
        myNumSteps = STEP_COUNT_START;
        setConfigColors();
        myCellDisplay = makeCellDisplay(myShape);
        setButtons();
    }

    private void readConfigFile() throws InvalidValueException {
        if(CONFIGURATION_FILE.equals(null)) {
            throw new InvalidValueException("This Configuration File does not exist.");
        }
        myConfiguration = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + CONFIGURATION_FILE);
        myFileName = myConfiguration.getString(FILE_CONFIG_LABEL);
        Arrangement neighborhoodType = Arrangement.valueOf(myConfiguration.getString(NEIGHBORHOOD_CONFIG_LABEL).toUpperCase());
        myShape = Shape.valueOf(myConfiguration.getString(CELL_SHAPE_CONFIG_LABEL).toUpperCase());
        Edge edgePolicy = Edge.valueOf(myConfiguration.getString(EDGE_CONFIG_LABEL).toUpperCase());
        myGrid = new Grid(myFileName, neighborhoodType, myShape, edgePolicy);
    }


    private CellDisplay makeCellDisplay(Shape s) {
        if (s.equals(Shape.IMAGE)) {
            String[] images = new String[myGrid.getType().getNumStates()];
            for(int i = 0; i < images.length; i++){
                images[i] = myGrid.getType().toString().toUpperCase() + i;
            }
            return new ImageDisplay(SIM_SIZE, myGrid.getHeight(), myGrid.getWidth(), images);
        } else if (s.equals(Shape.TRIANGLE)) {
            return new TriangleDisplay(SIM_SIZE, myGrid.getHeight(), myGrid.getWidth(), myColors);
        } else if (s.equals(Shape.HEXAGON)) {
            return new HexagonDisplay(SIM_SIZE, myGrid.getHeight(), myGrid.getWidth(), myColors);
        } else {
            return new RectangleDisplay(SIM_SIZE, myGrid.getHeight(), myGrid.getWidth(), myColors);
        }
    }

    protected Scene getScene() {
        return myScene;
    }

    protected void startAnimation() {
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

    private void setButtons() {
        for(DisplayObject d: myDisplayObjects) {
            myRoot.getChildren().addAll(d.getObjects());
        }
        updateButtons();
    }

    private void updateButtons() {
        if (myGrid != mySideBar.getGrid()) {
            myGrid = mySideBar.getGrid();
            myRoot.getChildren().removeAll(myGridGraph.getObjects());
            myGridGraph = new GridGraph(myGrid);
            myRoot.getChildren().addAll(myGridGraph.getObjects());
        }
        myColors = mySideBar.getColors();
        myCellDisplay.removeFromScreen(myRoot);
        myCellDisplay = makeCellDisplay(mySideBar.getShape());
        myCellDisplay.changeColors(myColors);
        myNumSteps = STEP_COUNT_START;
        displayStates();

    }

    private void displayStates() {
        myCellDisplay.removeFromScreen(myRoot);
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myRoot.getChildren().add(setFunction(i, j));
            }
        }
    }

    private void setConfigColors() {
        myColors = new String[MAX_STATES];
        for (int i = 0; i < myColors.length; i++) {
            if (myConfiguration.containsKey(COLOR_LABEL + i)) {
                myColors[i] = myConfiguration.getString(COLOR_LABEL + i);
            }
        }
        mySideBar.setColors(myColors);
    }

    private Node setFunction(int i, int j) {
        Node n = myCellDisplay.setView(i, j, myGrid.getCell(i, j).getState());
        n.setOnMouseClicked(e -> changeCellState(i, j));
        return n;
    }

    private void changeCellState(int row, int col) {
        if (myAnimation.getStatus().equals(Animation.Status.PAUSED)) {
            int currState = myGrid.getCell(row, col).getState();
            int nextState;
            int lastState = myGrid.getType().getNumStates() - 1;
            if (currState == lastState) {
                nextState = 0;
            } else {
                nextState = currState + 1;
            }
            myGrid.updateStates();
            myGrid.getCell(row, col).setNextState(nextState);
            myCellDisplay.removeFromScreen(myRoot);
            displayStates();
        }
    }

    private void step(double elapsedTime) {
        myCellDisplay.removeFromScreen(myRoot);
        displayStates();
        myGrid.setNextStates();
        myGrid.updateStates();
        myGridGraph.updateGraph(myNumSteps);
        myNumSteps++;
    }


    private void handleKeyInput(KeyCode code) {
        if (code.equals(SPACE) && mySideBar.isStepThrough()) {
            step(0);
        }
    }

}


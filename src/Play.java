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

import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.SPACE;


public class Play {
    private static final String FILE_NAME = "rps-grid-1.csv";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final int SIM_SIZE = 500;
    private static final int WINDOW_WIDTH = SIM_SIZE + 300;

    private static final String DEFAULT_RESOURCE_PACKAGE = "Resources/";
    private static final String STYLESHEET = "default.css";
    private static final String IMAGES_RESOURCE = "Images";
    private static final String IMAGE_FOLDER = "images/";
    private static final String SETTINGS = "Test";


    private Scene myScene;
    private Group myRoot;
    private Grid myGrid;
    private Timeline myAnimation;
    private UserInteraction mySideBar;
    private Paint[] myColors;
    private boolean myImage;
    private int myCellHeight;
    private int myCellWidth;
    private ResourceBundle myImages;
    private ResourceBundle mySettings;


    public Play() {
        if (FILE_NAME.substring(0,3).equals("Seg")) {
            myGrid = new SegGrid(FILE_NAME);
        } else {
            myGrid = new Grid(FILE_NAME);
        }
        myRoot = new Group();
        myScene = setUpGame(WINDOW_WIDTH, SIM_SIZE);
        myAnimation = new Timeline();
        mySideBar = new UserInteraction(myGrid, myAnimation);
        myImages = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + IMAGES_RESOURCE);
        mySettings = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SETTINGS);
        setDefaultImages();
        displayStates();
    }

    public Scene getScene() {
        return myScene;
    }

    public void startAnimation() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private Scene setUpGame(int width, int height) {
        Scene scene = new Scene(myRoot, width, height);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_PACKAGE + STYLESHEET).toExternalForm());

        return scene;
    }

    private void displayStates() {
        myRoot.getChildren().clear();
        setButtons();
        for (int i = 0; i < myGrid.getHeight(); i++) {
            for (int j = 0; j < myGrid.getWidth(); j++) {
                myRoot.getChildren().add(setView(i, j));
            }
        }
    }

    private void setButtons() {
        myRoot.getChildren().addAll(mySideBar.getButtons());
        myGrid = mySideBar.getGrid();
        myImage = mySideBar.getImages();
    }

    private Node setView(int i, int j) {
        myCellHeight = SIM_SIZE / myGrid.getHeight();
        myCellWidth = SIM_SIZE / myGrid.getWidth();
        if (myImage) {
            return setImage(i, j);
        } else {
            myColors = mySideBar.getColors();
            return setRectangle(i, j);
        }
    }

    private void setDefaultImages() {
        myImage = !mySettings.containsKey("Color0");
        Paint[] userColors = new Paint[3];
        for(int i = 0; i < 3; i++){
            if(mySettings.containsKey("Color" + i)){
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
        String image_file = IMAGE_FOLDER + myImages.getString(myGrid.getType() + myGrid.getCell(i, j).getState());
        Image preImage = new Image(this.getClass().getClassLoader().getResourceAsStream(image_file));
        ImageView img = new ImageView(preImage);
        img.setX(myCellWidth * i);
        img.setY(myCellHeight * j);
        img.setFitWidth(myCellWidth);
        img.setFitHeight(myCellHeight);
        return img;
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


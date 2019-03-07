package frontend;

import Enums.SimType;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import java.util.List;
import java.util.ResourceBundle;

public class ImageDisplay extends CellDisplay {
    private ResourceBundle myImages;
    private static final String IMAGE_RESOURCE_PACKAGE = "/Resources/Images";
    private static final String IMAGE_FOLDER = "images/";
    private int myCellHeight;
    private int myCellWidth;

    public ImageDisplay(int size, int height, int width, SimType s, Paint[] colors){
        super(size, height, width, s, colors);
        myImages = ResourceBundle.getBundle(IMAGE_RESOURCE_PACKAGE);

    }

    protected void remove(Node n, List<Node> toRemove) {
        if (n instanceof ImageView) {
            toRemove.add(n);
        }
    }

    protected Node setView(int i, int j, int state, SimType s){
        String image_file = IMAGE_FOLDER + myImages.getString(s.toString() + state);
        Image preImage = new Image(this.getClass().getClassLoader().getResourceAsStream(image_file));
        ImageView img = new ImageView(preImage);
        img.setX(myCellWidth * i);
        img.setY(myCellHeight * j);
        img.setFitWidth(myCellWidth);
        img.setFitHeight(myCellHeight);
        return img;
    }


    protected void setSize(int size, int height, int width){
        myCellWidth = size / height;
        myCellHeight = size / width;
    }


}

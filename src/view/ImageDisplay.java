package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;
import java.util.ResourceBundle;

public class ImageDisplay extends CellDisplay{
    private ResourceBundle myImages;
    private static final String IMAGE_RESOURCE_PACKAGE = "/Resources/Images";
    private static final String IMAGE_FOLDER = "/Resources/image_gifs/";
    private int myCellHeight;
    private int myCellWidth;
    private String[] myImageLabels;

    public ImageDisplay(int size, int height, int width, String[] images,boolean gridOutline){
        super(size, height, width, images,gridOutline);
        myImageLabels = getColors();
        setSize(size, height, width);
        myImages = ResourceBundle.getBundle(IMAGE_RESOURCE_PACKAGE);
    }

    public void remove(Node n, List<Node> toRemove) {
        if (n instanceof ImageView) {
            toRemove.add(n);
        }
    }

    public Node setView(int i, int j, int state){
        String image_file = IMAGE_FOLDER + myImages.getString(myImageLabels[state]);
        Image preImage = new Image(getClass().getResourceAsStream(image_file));
        ImageView img = new ImageView(preImage);
        img.setX(myCellWidth * j);
        img.setY(myCellHeight * i);
        img.setFitWidth(myCellWidth);
        img.setFitHeight(myCellHeight);
        return img;
    }

    public void setSize(int size, int height, int width){
        myCellHeight = size / height;
        myCellWidth = size / width;
    }


}

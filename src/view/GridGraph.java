package view;

import model.grid.Grid;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

import java.util.*;


public class GridGraph implements DisplayObject {
    private static final String VALUE_LABEL = "SimValue";
    private LineChart<NumberAxis, NumberAxis> myLineChart;
    private static final String X_LABEL = "Time";
    private static final String Y_LABEL = "Cells";
    private static final int AXIS_START = 0;
    private static final int Y_AXIS_STEP = 5;
    private static final int MAX_STATES = 3;
    private static final int X_POS = 0;
    private static final int Y_POS = 500;
    private static final int HEIGHT = 200;
    private static final int WIDTH = 500;

    private ResourceBundle myResources;
    private Grid myGrid;
    private Map<Integer, XYChart.Series> myData;
    private List<Node> mySlider;


    public GridGraph(Grid g) {
        myResources = ResourceBundle.getBundle(RESOURCES);
        myGrid = g;
        setObjects();
        updateGraph(0);
    }

    public void setObjects() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(X_LABEL);
        NumberAxis yAxis = new NumberAxis(AXIS_START, myGrid.getHeight() * myGrid.getWidth(), Y_AXIS_STEP);
        yAxis.setLabel(Y_LABEL);

        myLineChart = new LineChart(xAxis, yAxis);

        myLineChart.setLayoutX(X_POS);
        myLineChart.setLayoutY(Y_POS);
        myLineChart.setMaxHeight(HEIGHT);
        myLineChart.setMaxWidth(WIDTH);

        myData = new HashMap<>();
        for (int i = 0; i < MAX_STATES; i++) {
            XYChart.Series series = new XYChart.Series();
            myData.put(i, series);
            myData.get(i).setName(myResources.getString(myGrid.getType().toString() + i));
            myLineChart.getData().add(myData.get(i));
        }
        mySlider = addSettings();
    }

    public void updateGraph(int step) {
        for (int i = 0; i < MAX_STATES; i++) {
            if (myGrid.getCellsInState(i) == null) {
                myData.get(i).getData().add(new XYChart.Data<>(step, 0));
            } else {
                myData.get(i).getData().add(new XYChart.Data<>(step, myGrid.getCellsInState(i).size()));
            }
        }
    }

    private List<Node> addSettings() {
        List<Node> sliderObjs = new ArrayList<>();
        if (myGrid.getType().hasSpecialValue()) {
            sliderObjs.add(new Text(COLUMN_POSITION[0], ROW_POSITION[27], myResources.getString(VALUE_LABEL + myGrid.getType().toString())));
            Slider slider = new Slider(myGrid.getType().getMinVal(), myGrid.getType().getMaxVal(), myGrid.getCell(0, 0).getSpecialValue());
            slider.setLayoutX(COLUMN_POSITION[0]);
            slider.setLayoutY(ROW_POSITION[28]);
            slider.setMajorTickUnit(10);
            slider.setShowTickLabels(true);
            slider.setOnMouseClicked(e -> myGrid.setVal(slider.getValue()));
            sliderObjs.add(slider);
        }
        return sliderObjs;
    }

    public List<Node> getObjects() {
        List<Node> l = mySlider;
        l.add(myLineChart);
        return l;
    }

}

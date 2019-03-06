package frontend;

import grid.Grid;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class GridGraph {
    private LineChart<NumberAxis, NumberAxis> myLineChart;
    private static final String DEFAULT_RESOURCE_PACKAGE = "/Resources/";
    private static final String CELL_TYPES = "SideBar";
    private static final String X_LABEL = "Time";
    private static final String Y_LABEL = "Cells";
    private static final int AXIS_START = 0;
    private static final int X_AXIS_END = 10;
    private static final int X_AXIS_STEP = 1;
    private static final int Y_AXIS_STEP = 5;
    private static final int MAX_STATES = 3;
    private static final int X_POS = 0;
    private static final int Y_POS = 500;
    private static final int HEIGHT = 200;
    private static final int WIDTH = 500;



    private ResourceBundle myResources;
    private Grid myGrid;
    private Map<Integer, XYChart.Series> myData;

    GridGraph(Grid g) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + CELL_TYPES);
        myGrid = g;
        setUpGraph();
        updateGraph(0);
    }

    private void setUpGraph() {
        NumberAxis xAxis = new NumberAxis(AXIS_START, X_AXIS_END, X_AXIS_STEP);
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
    }

    LineChart<NumberAxis, NumberAxis> getGraph() {
        return myLineChart;
    }

    void updateGraph(int step) {
        for (int i = 0; i < MAX_STATES; i++) {
            if (myGrid.getCellsInState(i) == null) {
                myData.get(i).getData().add(new XYChart.Data<>(step, 0));
            } else {
                myData.get(i).getData().add(new XYChart.Data<>(step, myGrid.getCellsInState(i).size()));
            }
        }
    }
}

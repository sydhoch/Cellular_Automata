package frontend;

import grid.Grid;
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
        NumberAxis xAxis = new NumberAxis(0, 10, 1);
        xAxis.setLabel("Time");
        NumberAxis yAxis = new NumberAxis(0, myGrid.getHeight() * myGrid.getWidth(), 5);
        yAxis.setLabel("Cells");

        myLineChart = new LineChart(xAxis, yAxis);

        myLineChart.setLayoutX(0);
        myLineChart.setLayoutY(500);
        myLineChart.setMaxHeight(200);
        myLineChart.setMaxWidth(500);

        myData = new HashMap<>();
        for (int i = 0; i < 3; i++) {
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
        for (int i = 0; i < 3; i++) {
            if (myGrid.getCellsInState(i) == null) {
                myData.get(i).getData().add(new XYChart.Data(step, 0));
            } else {
                myData.get(i).getData().add(new XYChart.Data(step, myGrid.getCellsInState(i).size()));
            }
        }
    }
}

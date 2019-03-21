package controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationDriver extends Application{
    private static final String TITLE = "Cell Simulation";
    private Play simulation = new Play(null);

    public void start(Stage stage){
        Scene myScene = simulation.getScene();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        simulation.startAnimation();
    }
    public void changeDefault(String file){
        simulation = new Play(file);
    }
}

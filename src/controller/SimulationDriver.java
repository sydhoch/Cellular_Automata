package controller;

import controller.Play;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationDriver extends Application{

    private static final String TITLE = "Cell Simulation";
    //private Play simulation = new Play(null);

    public void start(Stage stage){
        Play simulation = new Play(null);
        Scene myScene = simulation.getScene();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        simulation.startAnimation();
    }
    //public void changeDefault(String file){
       // simulation = new Play(file);
    //}
}

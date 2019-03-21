package frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationDriver extends Application{

    private static final String TITLE = "Cell Simulation";
    private Play simulation;

    public void start(Stage stage){
        //Play simulation = Play(myFile);
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

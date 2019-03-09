package frontend;

import Enums.SimType;
import javafx.scene.Node;

import java.util.List;

import static Enums.SimType.*;

public interface DisplayObject {
    int[] COLUMN_POSITION = {510, 610, 710, 740, 770};
    int[] ROW_POSITION = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260, 280, 300, 320, 340, 360, 380, 400, 420, 440, 460, 480, 500, 520, 540, 560, 580, 600};
    String RESOURCES = "/Resources/SideBar";
    SimType[] SIMULATION_TYPES = {FIRE, GOL, PERC, PP, RPS, SEG};


    void setObjects();

    List<Node> getObjects();
}

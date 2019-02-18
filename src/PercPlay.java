import cell.Cell;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class PercPlay extends Play{

    public Cell[][] createCellGrid(String file){
        int[][] intArray = super.readFile(file);
        //convert ints to cells based on type
        return null;
    }

    public boolean checkEnd(Cell[][] grid){
        return false;
    }


}

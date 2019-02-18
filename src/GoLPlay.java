import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GoLPlay extends Play{
    private boolean cellChanged;

    public void setAndUpdateStates(Grid grid){
        cellChanged = false;
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                int[] neighbors = setNeighbors(i, j, grid);
                grid.getCell(i, j).checkNeighborStatus(neighbors);
                grid.getCell(i, j).updateState();
                if(grid.getCell(i, j).currState == grid.getCell(i,j).getNextState){
                    cellChanged = true;
                }
            }
        }
    }

    public int [] setNeighbors(int row, int col, Grid grid){
        int[] neighbors = new int[8];
        if(row == 0){
            neighbors[0] = grid.getCell(grid.getHeight());
        }
    }

    public boolean checkEnd(Grid grid){
        return !cellChanged;
    }


}

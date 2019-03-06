import cell.Cell;
import cell.PPCell;

public class PPGrid extends Grid {
    public PPGrid(String file) {
        super(file);
    }

    @Override
    protected void setNextStates(){
        System.out.println("hi");
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                //Cell[] neighbors = setNeighborsToroidal(i, j);
                ((PPCell)getCell(i, j)).setNextWasSet(false);
            }
        }
    }
}

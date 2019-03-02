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
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                //Cell[] neighbors = setNeighborsToroidal(i, j);
                if(getCell(i,j).getState()==2){
                    //getCell(i, j).checkNeighborStatus(neighbors);
                }
            }
        }
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                //Cell[] neighbors = setNeighborsToroidal(i, j);
                if(getCell(i,j).getState()==1){
                    //getCell(i, j).checkNeighborStatus(neighbors);
                }
            }
        }
    }
}

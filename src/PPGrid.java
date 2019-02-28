import cell.Cell;

public class PPGrid extends Grid {
    public PPGrid(String file) {
        super(file);
    }

    @Override
    protected void setNextStates(){
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Cell[] neighbors = setNeighbors(i, j);
                if(getCell(i,j).getState()==2){
                    getCell(i, j).checkNeighborStatus(neighbors);
                }
            }
        }
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Cell[] neighbors = setNeighbors(i, j);
                if(getCell(i,j).getState()==1){
                    getCell(i, j).checkNeighborStatus(neighbors);
                }
            }
        }
    }
}

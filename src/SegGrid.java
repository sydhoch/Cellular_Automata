import cell.Cell;

import java.util.*;
import java.lang.Math;
import cell.SegCell;

public class SegGrid extends Grid{
    public SegGrid(String file){
        super(file);
    }

    @Override
    protected void setNextStates(){
        List<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if(getCell(i,j).getState()==0){
                    emptyCells.add((SegCell)getCell(i,j));
                }
                Cell[] neighbors = setNeighbors(i, j);
                getCell(i, j).checkNeighborStatus(neighbors);

            }
        }
        Random random = new Random();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                SegCell cell = (SegCell) getCell(i,j);
                if(!cell.isSatisfied()){
                    if(emptyCells.size()!=0){
                        Cell empty = (Cell)emptyCells.get(random.nextInt(emptyCells.size()));
                        empty.setNextState(cell.getState());
                        cell.setNextState(0);
                    }
                }
            }
        }
    }


}

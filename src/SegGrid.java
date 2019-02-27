import cell.Cell;

import java.util.LinkedList;
import java.util.Queue;
import cell.SegCell;

public class SegGrid extends Grid{
    private Cell[][] myGrid;
    private Queue<Cell> emptyCells;

    public SegGrid(String file){
        super(file);
    }

    @Override
    protected void setNextStates(){
        emptyCells = new LinkedList<>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Cell[] neighbors = setNeighbors(i, j);
                if(getCell(i,j).getState()!=0){
                    getCell(i, j).checkNeighborStatus(neighbors);
                }
                else{
                    emptyCells.add(getCell(i,j));
                }
            }
        }
    }

    @Override
    protected void updateStates(){
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if(getCell(i,j).)
                getCell(i, j).updateCell();
            }
        }
    }
}

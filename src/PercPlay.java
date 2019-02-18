import cell.Cell;
import cell.PercCell;


public class PercPlay extends Play{

    public Cell[][] createCellGrid(String file){ //duplicated code
        int[][] intArray = readFile(file);
        Cell[][] cellArray = new PercCell[intArray.length][intArray[0].length];
        for(int i = 0; i < intArray.length; i++){
            for(int j =0; j < intArray[0].length; j++){
                cellArray[i][j] = new PercCell(intArray[i][j], i, j);
            }
        }
        return cellArray;
    }

    public boolean checkEnd(Cell[][] grid){
        return false;
    }
}

import cell.Cell;
import cell.GoLCell;

public class GoLPlay extends Play{

    public Cell[][] createCellGrid(String file){
        int[][] intArray = readFile(file);
        Cell[][] cellArray = new GoLCell[intArray.length][intArray[0].length];
        for(int i = 0; i < intArray.length; i++){
            for(int j =0; j < intArray[0].length; j++){
                cellArray[i][j] = new GoLCell(intArray[i][j], i, j);
            }
        }
        return cellArray;
    }


    public boolean checkEnd(Cell[][] grid){
        return false;
    }


}

import cell.Cell;

public class GoLPlay extends Play{

    public Cell[][] createCellGrid(String file){
        int[][] intArray = super.readFile(file);
        //convert ints to cells based on type
        return null;
    }


    public boolean checkEnd(Cell[][] grid){
        return false;
    }

}

import cell.Cell;
import cell.GoLCell;
import cell.PercCell;

import java.util.Scanner;


public class Grid {
    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private String myType;

    public Grid(String file) {
        GridMaker maker = new GridMaker(file);
        myHeight = maker.getHeight();
        myWidth = maker.getWidth();
        myGrid = maker.getGrid();
        myType = maker.getGameType();
    }

    public String getType(){
        return myType;
    }

    protected void setNextStates() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                Cell[] neighbors = setNeighbors(i, j);
                getCell(i, j).checkNeighborStatus(neighbors);
            }
        }
    }

    protected void updateStates() {
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                getCell(i, j).updateCell();
            }
        }
    }

    protected Cell getCell(int row, int col) {
        return myGrid[row][col];
    }

    public Cell[] setNeighbors(int row, int col) { //break up
        Cell[] neighbors = new Cell[8];
        if (row == 0 && col == 0) {
            neighbors[0] = getCell(myHeight - 1, myWidth - 1);
        } else if (row == 0) {
            neighbors[0] = getCell(myHeight - 1, col - 1);
        } else if (col == 0) {
            neighbors[0] = getCell(row - 1, myWidth - 1);
        } else {
            neighbors[0] = getCell(row - 1, col - 1);
        }

        if (row == 0) {
            neighbors[1] = getCell(myHeight - 1, col);
        } else {
            neighbors[1] = getCell(row - 1, col);
        }

        if (row == 0 && col == myWidth - 1) {
            neighbors[2] = getCell(myHeight - 1, 0);
        } else if (row == 0) {
            neighbors[2] = getCell(myHeight - 1, col + 1);
        } else if (col == myWidth - 1) {
            neighbors[2] = getCell(row - 1, 0);
        } else {
            neighbors[2] = getCell(row - 1, col + 1);
        }

        if (col == 0) {
            neighbors[3] = getCell(row, myWidth - 1);
        } else {
            neighbors[3] = getCell(row, col - 1);
        }

        if (col == myWidth - 1) {
            neighbors[4] = getCell(row, 0);
        } else {
            neighbors[4] = getCell(row, col + 1);
        }

        if (row == myHeight - 1 && col == 0) {
            neighbors[5] = getCell(0, myWidth - 1);
        } else if (row == myHeight - 1) {
            neighbors[5] = getCell(0, col - 1);
        } else if (col == 0) {
            neighbors[5] = getCell(row + 1, myWidth - 1);
        } else {
            neighbors[5] = getCell(row + 1, col - 1);
        }

        if (row == myWidth - 1) {
            neighbors[6] = getCell(0, col);
        } else {
            neighbors[6] = getCell(row + 1, col);
        }


        if (row == myHeight - 1 && col == myWidth - 1) {
            neighbors[7] = getCell(0, 0);
        } else if (row == myHeight - 1) {
            neighbors[7] = getCell(0, col + 1);
        } else if (col == myWidth - 1) {
            neighbors[7] = getCell(row + 1, 0);
        } else {
            neighbors[7] = getCell(row + 1, col + 1);
        }
        return neighbors;
    }

    protected int getWidth() { return myWidth; }

    protected int getHeight() { return myHeight; }

    public Cell[][] getGrid() {
        return myGrid;
    }

}
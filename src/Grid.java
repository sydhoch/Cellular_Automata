import cell.Cell;
import cell.GoLCell;
import cell.PercCell;

import java.util.Scanner;


public class Grid {
    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;

    public Grid(String file) {
        myGrid = getGrid(file);
    }

    private Cell[][] getGrid(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        s.useDelimiter(",");
        var gameType = s.next();
        //System.out.println(gameType);
        s.nextLine();
        myWidth = s.nextInt();
        //System.out.println(myWidth);
        myHeight = s.nextInt();
        //System.out.println(myHeight);
        Cell[][] myGrid = new Cell[myWidth][myHeight];
        s.nextLine();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                if (gameType.equals("Perc")) {
                    myGrid[i][j] = new PercCell(s.nextInt());
                } else if (gameType.equals("GoL")) {
                    myGrid[i][j] = new GoLCell(s.nextInt());
                }
            }
            s.nextLine();
        }
        return myGrid;
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
    public int getWidth() {
        return myWidth;
    }

    public int getHeight() {
        return myHeight;
    }

    public Cell getCell(int row, int col) {
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

    public Cell[][] getGrid() {
        return myGrid;
    }


}
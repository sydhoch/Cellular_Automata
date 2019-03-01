import cell.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class GridMaker {
    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private String myGameType;

    public GridMaker(String gameFile) {
        myGrid = makeGrid(gameFile);
    }

    private Cell[][] makeGrid(String gameFile){
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        String csv = "";
        while(s.hasNext()){
            csv = csv + s.next();
        }
        String[] seperatedVals = csv.split(",");
//        for(String st : seperatedVals){
//            System.out.println(st);
//        }
        myGameType = seperatedVals[0];
        myWidth = Integer.valueOf(seperatedVals[4]);
        myHeight = Integer.valueOf(seperatedVals[5]);
        Cell[][] grid = new Cell[myHeight][myWidth];
        int cell = 8;
        for(int i = 0; i < myHeight; i++){
            for(int j = 0; j < myWidth; j++){
//                while(seperatedVals[cell].equals("")){
//                    cell++;
//                }
//                System.out.println(i + "    " + j);
//                System.out.println(seperatedVals[cell]);
                int state = Integer.valueOf(seperatedVals[cell]);
                if (myGameType.equals("Perc")) {
                    grid[i][j] = new PercCell(state);
                } else if (myGameType.equals("GoL")) {
                    grid[i][j] = new GoLCell(state);
                } else if (myGameType.equals("RPS")) {
                    grid[i][j] = new RPSCell(state);
                } else if (myGameType.equals("Seg")) {
                    grid[i][j] = new SegCell(state);
                } else if (myGameType.equals("Fire")) {
                    grid[i][j] = new FireCell(state);
                }
                cell++;
            }
        }
        return grid;
    }
//    private Cell[][] makeGrid(String gameFile) {
//        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
//        s.useDelimiter(",");
//        myGameType = s.next();
//        //System.out.println(gameType);
//        s.nextLine();
//        myWidth = s.nextInt();
//        //System.out.println(myWidth);
//        myHeight = s.nextInt();
//        //System.out.println(myHeight + "height");
//        Cell[][] myGrid = new Cell[myWidth][myHeight];
//        s.nextLine();
//        for (int i = 0; i < myHeight; i++) {
//            for (int j = 0; j < myWidth; j++) {
//                int temp = s.nextInt();
//                if (myGameType.equals("Perc")) {
//                    myGrid[i][j] = new PercCell(temp);
//                } else if (myGameType.equals("GoL")) {
//                    myGrid[i][j] = new GoLCell(temp);
//                } else if (myGameType.equals("RPS")) {
//                    myGrid[i][j] = new RPSCell(temp);
//                } else if (myGameType.equals("Seg")) {
//                    myGrid[i][j] = new SegCell(temp);
//                } else if (myGameType.equals("Fire")) {
//                    myGrid[i][j] = new FireCell(temp);
//                }
//            }
//            s.nextLine();
//        }
//        return myGrid;
//    }

    public int getWidth() {
        return myWidth;
    }

    public int getHeight() {
        return myHeight;
    }

    public Cell[][] getGrid() {
        return myGrid;
    }

    public String getGameType() {
        return myGameType;
    }

}

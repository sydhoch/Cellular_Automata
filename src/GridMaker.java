import cell.*;

import java.util.Scanner;

public class GridMaker {
    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private String myGameType;
    private boolean toroidal;

    public GridMaker(String gameFile) {
        myGrid = makeGrid(gameFile);
    }

    private Cell[][] makeGrid(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        String csv = "";
        while (s.hasNext()) {
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
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
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
                } else if (myGameType.equals("PP")) {
                    grid[i][j] = new PPCell(state);
                    toroidal = true;
                }
                cell++;
            }
        }
        return grid;
    }

    public boolean getToroidal() {
        return toroidal;
    }

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

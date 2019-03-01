import cell.*;

import java.util.Scanner;

public class GridMaker {
    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private String myGameType;

    public GridMaker(String gameFile) {
        myGrid = makeGrid(gameFile);
    }

//    private Cell[][] makeGrid(String gameFile) {
//        System.out.println(gameFile);
//        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
//        s.useDelimiter(",");
//        myGameType = s.next();
//        //System.out.println(gameType);
//        s.nextLine();
//        myWidth = s.nextInt();
//        //System.out.println(myWidth);
//        myHeight = s.nextInt();
//        //System.out.println(myHeight);
//        Cell[][] myGrid = new Cell[myWidth][myHeight];
//        s.nextLine();
//        for (int i = 0; i < myHeight; i++) {
//            for (int j = 0; j < myWidth; j++) {
//                if (myGameType.equals("Perc")) {
//                    myGrid[i][j] = new PercCell(s.nextInt());
//                } else if (myGameType.equals("GoL")) {
//                    myGrid[i][j] = new GoLCell(s.nextInt());
//                } else if (myGameType.equals("RPS")) {
//                    myGrid[i][j] = new RPSCell(s.nextInt());
//                } else if (myGameType.equals("Seg")) {
//                    myGrid[i][j] = new SegCell(s.nextInt());
//                } else if (myGameType.equals("Fire")) {
//                    myGrid[i][j] = new FireCell(s.nextInt());
//                }
//            }
//            s.nextLine();
//        }
//        return myGrid;
//    }

    private Cell[][] makeGrid(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        s.useDelimiter(",");
        myGameType = s.next();
        //System.out.println(gameType);
        s.nextLine();
        myWidth = s.nextInt();
        //System.out.println(myWidth);
        myHeight = s.nextInt();
        //System.out.println(myHeight + "height");
        Cell[][] myGrid = new Cell[myWidth][myHeight];
        s.nextLine();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                int temp = s.nextInt();
                if (myGameType.equals("Perc")) {
                    myGrid[i][j] = new PercCell(temp);
                } else if (myGameType.equals("GoL")) {
                    myGrid[i][j] = new GoLCell(temp);
                } else if (myGameType.equals("RPS")) {
                    myGrid[i][j] = new RPSCell(temp);
                } else if (myGameType.equals("Seg")) {
                    myGrid[i][j] = new SegCell(temp);
                } else if (myGameType.equals("Fire")) {
                    myGrid[i][j] = new FireCell(temp);
                }
            }
            s.nextLine();
        }
        return myGrid;
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

import cell.*;

import java.util.Scanner;

public class GridMaker {
    private int myWidth;
    private int myHeight;
    private Cell[][] myGrid;
    private String myGameType;
    private boolean toroidal;

    public GridMaker(String gameFile){
        myGrid = getGrid(gameFile);
    }

    private Cell[][] getGrid(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        s.useDelimiter(",");
        myGameType = s.next();
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
                if (myGameType.equals("Perc")) {
                    myGrid[i][j] = new PercCell(s.nextInt());
                    toroidal=true;
                } else if (myGameType.equals("GoL")) {
                    myGrid[i][j] = new GoLCell(s.nextInt());
                    toroidal=true;
                } else if(myGameType.equals("RPS")){
                    myGrid[i][j] = new RPSCell(s.nextInt());
                    toroidal=true;
                }else if(myGameType.equals("Seg")){
                    myGrid[i][j] = new SegCell(s.nextInt());
                }
                else if(myGameType.equals("Fire")){
                    myGrid[i][j] = new FireCell(s.nextInt());
                }
                else if(myGameType.equals("PP")){
                    myGrid[i][j] = new PPCell(s.nextInt());
                    toroidal=true;
                }
            }
            s.nextLine();
        }
        return myGrid;
    }

    public boolean getToroidal(){
        return toroidal;
    }

    public int getWidth() { return myWidth; }

    public int getHeight() { return myHeight; }

    public Cell[][] getGrid() {
        return myGrid;
    }

    public String getGameType(){
        return myGameType;
    }

}

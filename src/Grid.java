import java.util.Scanner;


public class Grid {
    private int myWidth;
    private int myHeight;
    private String myGameType;


    public int[][] getGrid(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        s.useDelimiter(",");
        var gameType = s.next();
        System.out.println(gameType);
        s.nextLine();
        myWidth = s.nextInt();
        System.out.println(myWidth);
        myHeight = s.nextInt();
        System.out.println(myHeight);
        int[][] myGrid = new int[myWidth][myHeight];
        s.nextLine();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myGrid[i][j] = s.nextInt();
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

    public String getGameType() {
        return myGameType;
    }

}
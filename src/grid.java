import java.util.*;
import java.io.*;


public class Grid {
    public static void main(String[] args) {
        readFile("gol-grid-1.csv");
    }

    public static String readFile(String gameFile) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(gameFile));
        s.useDelimiter(",");
        var gameType = s.next();
        System.out.println(gameType);
        s.nextLine();
        var width = s.nextInt();
        System.out.println(width);
        var height = s.nextInt();
        System.out.println(height);
        int[][] myGrid = new int[width][height];
        s.nextLine();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                myGrid[i][j] = s.nextInt();
            }
            s.nextLine();
        }
        return Arrays.deepToString(myGrid);
    }
    }




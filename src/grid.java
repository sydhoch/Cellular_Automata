import java.util.*;
import java.io.*;


public class Grid {
    private static String gameType;
    private static int width;
    private static int height;

    public static void main(String[] args) {
        readFile("gol-grid-1.csv");
    }

    public static String readFile(String fileName) {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream(fileName));
        s.useDelimiter(",");
        gameType = s.next();
        s.nextLine();
        width = s.nextInt();
        height = s.nextInt();
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

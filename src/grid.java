import java.util.*;
import java.io.*;


public class grid {
    public static void main(String[] args) {
        readFile();
    }
    //private int[][] intArray (with getter)
    //private Cell[][] cellArray (with getter)
    //private int height (with getter)
    //private in width (with getter)

    //constructor: Grid(String file)

    //method: getCell(row, col)

    //method: intArrayToCellArray

    private static void readFile() {
        Scanner s = new Scanner(Play.class.getClassLoader().getResourceAsStream("gol-Grid-1.csv"));
        s.useDelimiter(",");
        var gameType = s.next();
        System.out.println(gameType);
        s.nextLine();
        var width = s.nextInt() - 1;
        System.out.println(width);
        var height = s.nextInt() - 1;
        System.out.println(height);
        int[][] myGrid = new int[width][height];
        s.nextLine();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                myGrid[i][j] = s.nextInt();
            }
            s.nextLine();
        }
        System.out.println(Arrays.deepToString(myGrid));
//TODO: Read the last column correctly
    }












}

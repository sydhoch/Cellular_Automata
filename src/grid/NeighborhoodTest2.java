package grid;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import Enums.SimType;
import cell.Cell;
import cell.GoLCell;
import grid.Grid;
import grid.Neighborhood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodTest2 {
    private GoLCell g0;
    private GoLCell g1;
    private GoLCell g2;

    @BeforeEach
    void setup(){
        g0 = new GoLCell(0);
        g1 = new GoLCell(1);
        g2 = new GoLCell(2);

    }

    @Test
    void getNeighborsMiddleRectangleCompleteToroidal() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.COMPLETE, Shape.RECTANGLE, Edge.TOROIDAL, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(1,1,Shape.RECTANGLE,Arrangement.COMPLETE,Edge.TOROIDAL,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 5);
        boolean expectedOne = (onecount==3);
        assertEquals(true,expectedOne && expectedZero);
    }



    @Test
    void getNeighborsRectangleCardinalFINITERowColZero() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.CARDINAL, Shape.RECTANGLE, Edge.FINITE, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(0,0,Shape.RECTANGLE,Arrangement.CARDINAL,Edge.FINITE,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 2);
        boolean expectedOne = (onecount==0);
        assertEquals(true,expectedOne && expectedZero);
    }
    @Test
    void getNeighborsRectangleCompleteToroidalMax() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.COMPLETE, Shape.RECTANGLE, Edge.TOROIDAL, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(2,2,Shape.RECTANGLE,Arrangement.COMPLETE,Edge.TOROIDAL,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 4);
        boolean expectedOne = (onecount==4);
        assertEquals(true,expectedOne && expectedZero);
    }

    @Test
    void getNeighborsRectangleFINITECompleteMax() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.COMPLETE, Shape.RECTANGLE, Edge.FINITE, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(3,3,Shape.RECTANGLE,Arrangement.COMPLETE,Edge.FINITE,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 1);
        boolean expectedOne = (onecount==2);
        assertTrue(expectedOne && expectedZero);
    }

    @Test
    void getNeighborsHexFINITECompleteMax() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.COMPLETE, Shape.HEXAGON, Edge.FINITE, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(1,1,Shape.HEXAGON,Arrangement.COMPLETE,Edge.FINITE,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 4);
        boolean expectedOne = (onecount==2);
        assertTrue(expectedOne && expectedZero);
    }

    @Test
    void getNeighborsTriangleFINITECompleteMax() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.COMPLETE, Shape.TRIANGLE, Edge.FINITE, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(1,1,Shape.TRIANGLE,Arrangement.COMPLETE,Edge.FINITE,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 6);
        boolean expectedOne = (onecount==3);
        assertTrue(expectedOne && expectedZero);
    }

    @Test
    void getNeighborsTriangleTOROIDALCompleteMax() {
        Grid myGrid = new Grid("gol-grid-3.csv", Arrangement.COMPLETE, Shape.TRIANGLE, Edge.TOROIDAL, SimType.GOL);
        Neighborhood neighbors = new Neighborhood(1,1,Shape.TRIANGLE,Arrangement.COMPLETE,Edge.TOROIDAL,myGrid);
        Cell[] neigh = neighbors.getNeighbors();
        int onecount = 0;
        int zerocount=0;
        for(int i=0;i<neigh.length;i++){
            if(neigh[i].getState()==0){
                zerocount ++;
            }
            else{
                onecount++;
            }
        }
        boolean expectedZero = (zerocount == 8);
        boolean expectedOne = (onecount==4);
        assertTrue(expectedOne && expectedZero);
    }

//    @Test
//    void getNeighborsTriangleCompleteToroidalMax() {
//        Grid myGrid = new Grid("example-triangle-rps.csv", Arrangement.COMPLETE, Shape.TRIANGLE, Edge.TOROIDAL);
//        Neighborhood neighbors = new Neighborhood(1,3,Shape.TRIANGLE,Arrangement.COMPLETE,Edge.TOROIDAL,myGrid);
//        Cell[] neigh = neighbors.getNeighbors();
//        int twocount = 0;
//        int onecount = 0;
//        int zerocount=0;
//        for(int i=0;i<neigh.length;i++){
//            if(neigh[i].getState()==0){
//                zerocount ++;
//            }
//            if(neigh[i].getState()==2){
//                twocount ++;
//            }
//            else{
//                onecount++;
//            }
//        }
//        boolean expectedTwo = (twocount == 5);
//        boolean expectedZero = (zerocount == 3);
//        boolean expectedOne = (onecount==4);
//        assertEquals(true,expectedOne && expectedZero && expectedTwo);
//    }
}
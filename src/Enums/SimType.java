package Enums;

import model.cell.*;

public enum SimType {
    FIRE(3, true, 0, 100, new FireCell(0)),
    GOL(2, false, 0, 0, new GoLCell(0)),
    PERC(3, false, 0, 0, new PercCell(0)),
    PP(3, true, 0, 10, new PPCell(0)),
    RPS(3, true, 0, 10, new RPSCell(0)),
    SEG(3, true, 0, 100, new SegCell(0));

    private final int myNumStates;
    private final boolean mySpecialValue;
    private final double myMinVal;
    private final double myMaxVal;
    private final Cell myCell;

    SimType(int numState, boolean specialValue, double minVal, double maxVal, Cell cell){
        myNumStates = numState;
        mySpecialValue = specialValue;
        myMinVal = minVal;
        myMaxVal = maxVal;
        myCell = cell;
    }

    public Cell getNewCell(){return myCell;}

    public int getNumStates(){
        return myNumStates;
    }

    public boolean hasSpecialValue(){return mySpecialValue;}

    public double getMinVal(){return myMinVal;}

    public double getMaxVal(){return myMaxVal;}
}


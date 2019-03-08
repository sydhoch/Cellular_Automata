package Enums;

public enum SimType {
    FIRE(3, true, 0, 100),
    GOL(2, false, 0, 0),
    PERC(3, false, 0, 0),
    PP(3, true, 0, 10),
    RPS(3, true, 0, 10),
    SEG(3, true, 0, 100);

    private final int myNumStates;
    private final boolean mySpecialValue;
    private final double myMinVal;
    private final double myMaxVal;

    SimType(int numState, boolean specialValue, double minVal, double maxVal){
        myNumStates = numState;
        mySpecialValue = specialValue;
        myMinVal = minVal;
        myMaxVal = maxVal;
    }

    public int getNumStates(){
        return myNumStates;
    }

    public boolean hasSpecialValue(){return mySpecialValue;}

    public double getMinVal(){return myMinVal;}

    public double getMaxVal(){return myMaxVal;}
}

